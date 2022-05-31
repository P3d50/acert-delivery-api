package com.delivery.api.repository;

import com.delivery.api.dto.request.PersonDTO;
import com.delivery.api.entity.Person;
import com.delivery.api.mapper.PersonMapper;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Repository
public class PersonRepositoty {

    private final PersonMapper personMapper = PersonMapper.INSTANCE.INSTANCE;

    public Person create(PersonDTO personDTO) {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("person").document();
        Person personCreated = personMapper.toModel(personDTO);
        personCreated.setId(documentReference.getId());
        ApiFuture<WriteResult> apiFuture = documentReference.set(personCreated);
        return personCreated;
    }

    public List<Person> getAll() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("person").get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        return list.stream()
                .map(doc -> doc.toObject(Person.class))
                .collect(Collectors.toList());
    }

    public String delete(String id) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference doc = firestore.collection("person").document(id);
        ApiFuture<WriteResult> apiFuture = doc.delete();
        return apiFuture.get().getUpdateTime().toDate().toString();
    }

    public String update(Person person) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference doc = firestore.collection("person").document(person.getId());
        ApiFuture<WriteResult> apiFuture = doc.set(person);
        return apiFuture.get().getUpdateTime().toDate().toString();
    }

    public List<Person> geListByUserName(String key) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("person")
                .whereGreaterThanOrEqualTo("username", key)
                .whereLessThan("username", key + "z").get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        return list.stream().map(doc -> doc.toObject(Person.class)).collect(Collectors.toList());
    }

    public Optional<Person> findById(String key) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("person")
                .whereEqualTo("id",key).get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        if(CollectionUtils.isEmpty(list))
            return Optional.empty();
        return list.stream().map(doc -> doc.toObject(Person.class)).collect(Collectors.toList()).stream().findFirst();
    }
}
