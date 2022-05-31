package com.delivery.api.controller;

import com.delivery.api.dto.request.PersonDTO;
import com.delivery.api.exceptions.ClientNotFoundException;
import com.delivery.api.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
@CrossOrigin(origins = "*")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientController {

    private ClientService clientService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonDTO create(@RequestBody @Valid PersonDTO personDTO) {
        return clientService.create(personDTO);
    }

    @GetMapping
    public List<PersonDTO> getAllClients() {
        return clientService.getAll();
    }

    @GetMapping("/search")
    public List<PersonDTO> getListByUserName(@RequestParam String username) {
        return clientService.getClientListByUserName(username);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable String id, @RequestBody @Valid PersonDTO personDTO) {
        clientService.findById(id)
                .map(person -> clientService.update(person))
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam String id) {
        clientService.findById(id)
                .map(personDTO -> clientService.delete(id))
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

    @GetMapping("/{id}")
    public PersonDTO getByID(@PathVariable String id) {
        return clientService.findById(id)
                .orElseThrow(() -> new ClientNotFoundException(id));
    }

}

