package com.delivery.api.service.impl;

import com.delivery.api.dto.request.AppUserDTO;
import com.delivery.api.entity.AppUser;
import com.delivery.api.exceptions.InvalidPassWordException;
import com.delivery.api.repository.UserRepository;
import com.delivery.api.security.auth.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ExecutionException;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    Encoder encoder;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public AppUser create(AppUserDTO appUser) {
        AppUser userToCreate = AppUser
                .builder()
                .password(appUser.getPassword())
                .username(appUser.getUsername())
                .build();
        return userRepository.create(userToCreate);
    }

    public UserDetails authenticate(AppUserDTO appUserDTO) {
        UserDetails user = loadUserByUsername(appUserDTO.getUsername());

        boolean passwordMatch = encoder
                .passwordEncoder()
                .matches(appUserDTO.getPassword(), user.getPassword());

        if (passwordMatch) {
            return user;
        }
        throw new InvalidPassWordException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            AppUser user = userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(username));
            return User
                    .builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles("USER")
                    .build();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return null;
    }
}
