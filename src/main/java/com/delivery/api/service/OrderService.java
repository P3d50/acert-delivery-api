package com.delivery.api.service;

import com.delivery.api.dto.request.DeliveryDTO;
import com.delivery.api.dto.request.OrderDTO;
import com.delivery.api.dto.request.OrderItemDTO;
import com.delivery.api.dto.request.PersonDTO;
import com.delivery.api.dto.response.OrderInformationDTO;
import com.delivery.api.dto.response.OrderInformationItemDTO;
import com.delivery.api.entity.Order;
import com.delivery.api.entity.OrderItem;
import com.delivery.api.enums.OrderStatus;
import com.delivery.api.exceptions.ClientNotFoundException;
import com.delivery.api.exceptions.OrderIdNotFoundException;
import com.delivery.api.exceptions.OrderItemsEmptyException;
import com.delivery.api.exceptions.OrderNotFoundException;
import com.delivery.api.mapper.OrderMapper;
import com.delivery.api.mapper.PersonMapper;
import com.delivery.api.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Service
public class OrderService {

    private final OrderMapper orderMapper = OrderMapper.INSTANCE.INSTANCE;
    private final PersonMapper personMapper = PersonMapper.INSTANCE.INSTANCE;
    private final OrderRepository orderRepository;

    private final DeliveryService deliveryService;

    private final ClientService clientService;

    public OrderService(OrderRepository orderRepository, DeliveryService deliveryService, ClientService clientService) {
        this.orderRepository = orderRepository;
        this.deliveryService = deliveryService;
        this.clientService = clientService;
    }

    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {

        Order order = Order.builder()
                .date("date")//TODO
                .clientId(orderDTO.getClientId())
                .itemList(converterItems(orderDTO.getItemList()))
                .status(OrderStatus.RECEIVED)
                .build();

        return orderMapper.toDTO(orderRepository.create(order));
    }

    public List<OrderDTO> getAll() {
        try {
            return orderRepository.getAll()
                    .stream()
                    .map(orderMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return emptyList();
    }

    public Optional<OrderDTO> findById(String id) {
        try {
            return orderRepository.findById(id)
                    .map(orderMapper::toDTO);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return Optional.empty();
    }

    @Transactional
    public String update(OrderDTO orderDTO) {
        try {
            OrderDTO order = orderRepository.findById(orderDTO.getId())
                    .map(orderMapper::toDTO)
                    .orElseThrow(() -> new OrderIdNotFoundException(orderDTO.getId()));
            return orderRepository.update(orderMapper.toModel(order));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    @Transactional
    public String delete(String id) {
        try {
            if (orderRepository.findById(id).isPresent())
                return orderRepository.delete(id);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    public Optional<OrderInformationDTO> getCompleteOrder(String id) {

        OrderDTO order = findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        PersonDTO client = clientService.findById(order.getClientId())
                .orElseThrow(() -> new ClientNotFoundException(id));

        List<OrderInformationItemDTO> itens = converterItemsDTO(order);

        BigDecimal total = itens.stream()
                .map(OrderInformationItemDTO::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<DeliveryDTO> deliveryDTOS = deliveryService.findByOrderId(id);

        return Optional.of(OrderInformationDTO.builder()
                .clientName(client.getFirstName())
                .CPF(client.getCPF())
                .codigo(order.getId())
                .items(itens)
                .total(total)
                .status(order.getStatus())
                .deliverys(deliveryDTOS)
                .build()
        );
    }

    private List<OrderInformationItemDTO> converterItemsDTO(OrderDTO order) {
        return converterItems(order.getItemList()).stream()
                .map(orderItem ->
                        OrderInformationItemDTO.builder()
                                .description(orderItem.getDescription())
                                .price(orderItem.getPrice())
                                .quantity(orderItem.getQuantity())
                                .total(
                                        new BigDecimal(orderItem.getQuantity())
                                                .multiply(orderItem.getPrice())
                                )
                                .build()
                ).collect(Collectors.toList());

    }

    private List<OrderItem> converterItems(List<OrderItemDTO> items) {
        if (items.isEmpty()) {
            throw new OrderItemsEmptyException();
        }
        return items.stream()
                .map(dto ->
                        OrderItem.builder()
                                .price(dto.getPrice())
                                .quantity(dto.getQuantity())
                                .description(dto.getDescription())
                                .build()
                ).collect(Collectors.toList());
    }

    @Transactional
    public void updateStatus(OrderStatus orderStatus, String id) {

        try {
            OrderDTO order = orderRepository.findById(id)
                    .map(orderMapper::toDTO)
                    .orElseThrow(() -> new OrderIdNotFoundException(id));
            order.setStatus(orderStatus);
            orderRepository.update(orderMapper.toModel(order));

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
