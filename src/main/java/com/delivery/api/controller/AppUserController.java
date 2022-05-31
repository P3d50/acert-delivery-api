package com.delivery.api.controller;

import com.delivery.api.dto.request.AppUserDTO;
import com.delivery.api.dto.request.CredentialDTO;
import com.delivery.api.dto.response.TokenDTO;
import com.delivery.api.entity.AppUser;
import com.delivery.api.exceptions.InvalidPassWordException;
import com.delivery.api.exceptions.UserNotFoundException;
import com.delivery.api.mapper.AppUserMapper;
import com.delivery.api.security.jwt.JwtService;
import com.delivery.api.service.impl.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserMapper appUserMapper = AppUserMapper.INSTANCE.INSTANCE;

    private final UserDetailServiceImpl userDetailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppUser create(@RequestBody @Valid AppUserDTO user) {
        String cryptPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(cryptPassword);
        return userDetailService.create(user);
    }

    @PostMapping("/auth")
    public TokenDTO authenticate(@RequestBody @Valid CredentialDTO credentialDTO) {
        try {
            AppUserDTO appUserDTO = AppUserDTO
                    .builder()
                    .username(credentialDTO.getUsername())
                    .password(credentialDTO.getPassword())
                    .build();
            UserDetails authenticatedUser = userDetailService.authenticate(appUserDTO);
            String token = jwtService.gerarToken(appUserMapper.toModel(appUserDTO));
            return TokenDTO
                    .builder()
                    .username(appUserDTO.getUsername())
                    .token(token)
                    .build();

        } catch (UserNotFoundException | InvalidPassWordException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
