package com.delivery.api.controller;

import com.delivery.api.dto.request.DeliveryDTO;
import com.delivery.api.dto.request.DeliveryStatusDTO;
import com.delivery.api.exceptions.DeliveryNotFoundException;
import com.delivery.api.service.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/delivery")
@CrossOrigin(origins = "*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DeliveryController {

    private DeliveryService deliveryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryDTO create(@RequestBody @Valid DeliveryDTO deliveryDTO) {
        return deliveryService.create(deliveryDTO);
    }

    @GetMapping
    public List<DeliveryDTO> getAll() {
        return deliveryService.getAll();
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id, @RequestBody @Valid DeliveryDTO deliveryDTO) {
        deliveryService.findById(id)
                .map(delivery -> deliveryService.update(delivery))
                .orElseThrow(() -> new DeliveryNotFoundException(id));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam String id) {
        deliveryService.findById(id)
                .map(deliveryDTO -> deliveryService.delete(deliveryDTO.getId()))
                .orElseThrow(() -> new DeliveryNotFoundException(id));
    }

    @GetMapping("/{id}")
    public DeliveryDTO findById(@PathVariable String id) {
        return deliveryService.findById(id)
                .orElseThrow(() -> new DeliveryNotFoundException(id));
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@RequestBody @Valid DeliveryStatusDTO deliveryStatus, @PathVariable String id) {
        deliveryService.updateStatus(deliveryStatus.getStatus(), id);
    }

}

