package com.delivery.api.repository;

import com.delivery.api.entity.Order;
import com.delivery.api.dto.request.OrderDTO;
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
public class OrderRepository {

    public Order create(Order order) {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("order").document();
        order.setId(documentReference.getId());
        ApiFuture<WriteResult> apiFuture = documentReference.set(order);
        return order;
    }

    public List<Order> getAll() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("order").get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        return list.stream()
                .map(doc -> doc.toObject(Order.class))
                .collect(Collectors.toList());
    }

    public String delete(String id) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference doc = firestore.collection("order").document(id);
        ApiFuture<WriteResult> apiFuture = doc.delete();
        return apiFuture.get().getUpdateTime().toDate().toString();
    }

    public String update(Order order) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference doc = firestore.collection("order").document(order.getId());
        ApiFuture<WriteResult> apiFuture = doc.set(order);
        return apiFuture.get().getUpdateTime().toDate().toString();
    }

    public Optional<Order> findById(String key) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("order")
                .whereEqualTo("id",key).get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        if(CollectionUtils.isEmpty(list))
            return Optional.empty();
        return list.stream().map(doc -> doc.toObject(Order.class)).collect(Collectors.toList()).stream().findFirst();
    }
}
