package com.delivery.api.service;

import com.delivery.api.dto.request.PersonDTO;
import com.delivery.api.mapper.PersonMapper;
import com.delivery.api.repository.PersonRepositoty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Service
public class ClientService {

    private final PersonMapper personMapper = PersonMapper.INSTANCE.INSTANCE;

    private final PersonRepositoty personRepositoty;

    public ClientService(PersonRepositoty personRepositoty) {
        this.personRepositoty = personRepositoty;
    }

    @Transactional
    public PersonDTO create(PersonDTO personDTO) {
        return personMapper.toDTO(personRepositoty.create(personDTO));
    }

    public List<PersonDTO> getAll() {

        try {
            return personRepositoty.getAll()
                    .stream()
                    .map(personMapper::toDTO)
                    .collect(Collectors.toList());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return emptyList();
    }

    public List<PersonDTO> getClientListByUserName(String key) {
        try {
            return personRepositoty.geListByUserName(key)
                    .stream()
                    .map(person -> personMapper.toDTO(person))
                    .collect(Collectors.toList());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return emptyList();
    }


    public Optional<PersonDTO> findById(String key) {

        try {
            return personRepositoty.findById(key)
                    .map(personMapper::toDTO);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return Optional.empty();
    }

    @Transactional
    public String update(PersonDTO personDTO) {
        try {
            if(personRepositoty.findById(personDTO.getId()).isPresent())
                return personRepositoty.update(personMapper.toModel(personDTO));
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
            if(personRepositoty.findById(id).isPresent())
                return personRepositoty.delete(id);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }

}

