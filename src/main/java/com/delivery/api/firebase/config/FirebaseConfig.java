package com.delivery.api.firebase.config;

import com.delivery.api.DeliveryApplication;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

@Service
public class FirebaseConfig {

    @PostConstruct
    public void configureFirebaseConnection() throws IOException {

        ClassLoader classLoader = DeliveryApplication.class.getClassLoader();
        File file = new File("/app/src/main/resources/serviceAccountKey.jsons");
        FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }
}
