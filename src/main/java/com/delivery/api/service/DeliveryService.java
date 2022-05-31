package com.delivery.api.service;

import com.delivery.api.dto.request.DeliveryDTO;
import com.delivery.api.entity.Delivery;
import com.delivery.api.enums.DeliveryStatus;
import com.delivery.api.exceptions.OrderIdNotFoundException;
import com.delivery.api.mapper.DeliveryMapper;
import com.delivery.api.repository.DeliveryRepository;
import com.delivery.api.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Service
public class DeliveryService {

    private final DeliveryMapper deliveryMapper = DeliveryMapper.INSTANCE.INSTANCE;

    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;

    public DeliveryService(DeliveryRepository deliveryRepository, OrderRepository orderRepository) {
        this.deliveryRepository = deliveryRepository;
        this.orderRepository = orderRepository;
    }


    @Transactional
    public DeliveryDTO create(DeliveryDTO deliveryDTO) {
        return deliveryMapper.toDTO(deliveryRepository.create(deliveryMapper.toModel(deliveryDTO)));
    }

    public List<DeliveryDTO> getAll() {
        try {
            return deliveryRepository.getAll().stream()
                    .map(deliveryMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return emptyList();
    }

    public Optional<DeliveryDTO> findById(String id) {
        try {
            return deliveryRepository.findById(id)
                    .map(deliveryMapper::toDTO);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return Optional.empty();
    }

    public List<DeliveryDTO> findByOrderId(String id) {
        try {
            return deliveryRepository.findByOrderId(id)
                    .stream()
                    .map(deliveryMapper::toDTO)
                    .collect(Collectors.toList());

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return emptyList();
    }

    @Transactional
    public String update(DeliveryDTO deliveryDTO) {
        try {
            if (deliveryRepository.findById(deliveryDTO.getId()).isPresent())
                return deliveryRepository.update(deliveryMapper.toModel(deliveryDTO));
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
            if (deliveryRepository.findById(id).isPresent())
                return deliveryRepository.delete(id);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

    @Transactional
    public void updateStatus(DeliveryStatus deliveryStatus, String id) {
        try {
            Delivery delivery = deliveryRepository.findById(id).orElseThrow(() -> new OrderIdNotFoundException(id));
            delivery.setStatus(deliveryStatus);
            deliveryRepository.update(delivery);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
