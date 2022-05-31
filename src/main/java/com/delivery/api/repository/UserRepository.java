package com.delivery.api.repository;

import com.delivery.api.entity.AppUser;
import com.delivery.api.mapper.AppUserMapper;
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
public class UserRepository {

    private final AppUserMapper userMapper = AppUserMapper.INSTANCE.INSTANCE;


    public AppUser create(AppUser appUser) {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = firestore.collection("user").document();
        AppUser userCreated = AppUser
                .builder()
                .password(appUser.getPassword())
                .id(documentReference.getId())
                .username(appUser.getUsername())
                .build();
        ApiFuture<WriteResult> apiFuture = documentReference.set(userCreated);
        return userCreated;
    }

    public List<AppUser> getAll() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("user").get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        return list.stream()
                .map(doc -> doc.toObject(AppUser.class))
                .collect(Collectors.toList());
    }

    public String delete(String id) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference doc = firestore.collection("user").document(id);
        ApiFuture<WriteResult> apiFuture = doc.delete();
        return apiFuture.get().getUpdateTime().toDate().toString();
    }

    public String update(AppUser user) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference doc = firestore.collection("user").document(user.getId());
        ApiFuture<WriteResult> apiFuture = doc.set(user);
        return apiFuture.get().getUpdateTime().toDate().toString();
    }

    public List<AppUser> geListByUserName(String key) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("user")
                .whereGreaterThanOrEqualTo("login", key)
                .whereLessThan("login", key + "z").get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        return list.stream().map(doc -> doc.toObject(AppUser.class)).collect(Collectors.toList());
    }

    public Optional<AppUser> findById(String key) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("user")
                .whereEqualTo("id", key).get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        if (CollectionUtils.isEmpty(list))
            return Optional.empty();
        return list.stream().map(doc -> doc.toObject(AppUser.class)).collect(Collectors.toList()).stream().findFirst();
    }

    public Optional<AppUser> findByUserName(String key) throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<QuerySnapshot> apiFuture = firestore.collection("user")
                .whereEqualTo("username", key).get();
        List<QueryDocumentSnapshot> list = apiFuture.get().getDocuments();
        if (CollectionUtils.isEmpty(list))
            return Optional.empty();
        return list.stream().map(doc -> doc.toObject(AppUser.class)).collect(Collectors.toList()).stream().findFirst();
    }
}
