package com.delivery.api.controller;

import com.delivery.api.dto.request.OrderDTO;
import com.delivery.api.dto.request.OrderItemDTO;
import com.delivery.api.dto.request.OrderStatusDTO;
import com.delivery.api.dto.response.OrderInformationDTO;
import com.delivery.api.exceptions.OrderNotFoundException;
import com.delivery.api.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@CrossOrigin(origins = "*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDTO create(@RequestBody @Valid OrderDTO orderDTO) {
        return orderService.create(orderDTO);
    }

    @GetMapping
    public List<OrderDTO> getAll() {
        return orderService.getAll();
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id, @RequestBody @Valid OrderDTO orderDTO) {
        orderService.findById(id)
                .map(order -> orderService.update(order))
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam String id) {
        orderService.findById(id)
                .map(orderDTO -> orderService.delete(id))
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @GetMapping("/{id}")
    public OrderInformationDTO findById(@PathVariable String id) {
        return orderService.getCompleteOrder(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@RequestBody @Valid OrderStatusDTO orderStatus, @PathVariable String id) {
        orderService.updateStatus(orderStatus.getStatus(), id);
    }

}

