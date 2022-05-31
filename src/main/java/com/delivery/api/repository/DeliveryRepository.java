package com.delivery.api.repository;

import com.delivery.api.entity.Delivery;
import com.delivery.api.enums.DeliveryStatus;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Repository
public class DeliveryRepository {

    private OrderRepository orderRepository;

    public DeliveryRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    public Delivery create(Delivery delivery) {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("delivery").document();
        Delivery deliveryToSave = Delivery.builder()
                .id(documentReference.getId())
                .address(delivery.getAddress())
                .orderId(delivery.getOrderId())
                .status(DeliveryStatus.WAITING)
                .build();
        ApiFuture<WriteResult> apiFuture = documentReference.set(deliveryToSave);
        return deliveryToSave;
    }

    public List<Delivery> getAll() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("delivery").get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        return list.stream()
                .map(doc -> doc.toObject(Delivery.class))
                .collect(Collectors.toList());
    }

    public String delete(String id) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference doc = firestore.collection("delivery").document(id);
        ApiFuture<WriteResult> apiFuture = doc.delete();
        return apiFuture.get().getUpdateTime().toDate().toString();
    }

    public String update(Delivery delivery) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference doc = firestore.collection("delivery").document(delivery.getId());
        ApiFuture<WriteResult> apiFuture = doc.set(delivery);
        return apiFuture.get().getUpdateTime().toDate().toString();
    }

    public Optional<Delivery> findById(String key) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("delivery")
                .whereEqualTo("id", key).get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        if (CollectionUtils.isEmpty(list))
            return Optional.empty();
        return list.stream().map(doc -> doc.toObject(Delivery.class)).collect(Collectors.toList()).stream().findFirst();
    }

    public List<Delivery> findByOrderId(String key) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore
                .collection("delivery")
                .whereEqualTo("orderId", key).get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        if (CollectionUtils.isEmpty(list))
            return emptyList();
        return list.stream().map(doc -> doc.toObject(Delivery.class))
                .collect(Collectors.toList());
    }
}
