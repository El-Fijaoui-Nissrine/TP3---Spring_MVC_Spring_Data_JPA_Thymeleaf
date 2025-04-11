package com.example.hopital.security.service;

import com.example.hopital.security.entities.AppRole;
import com.example.hopital.security.entities.AppUser;
import com.example.hopital.security.repo.AppRoleRepository;
import com.example.hopital.security.repo.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class AccountServiceImpl implements AccountService{
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public AppUser addNewUser(String username, String password, String email, String confirmPas) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser!=null) throw  new RuntimeException("this user already exist");
        if(!password.equals(confirmPas)) throw new RuntimeException("Password not match");
        appUser=AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .email(email)
                .build();
        AppUser savedAppUser=appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole addNewRole(String role) {
        AppRole appRole=appRoleRepository.findById(role).orElse(null);
        if (appRole!=null) throw new RuntimeException("this role already exist");
        appRole=AppRole.builder()
                .role(role)
                .build();
        return appRoleRepository.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
      AppRole appRole=appRoleRepository.findById(role).get();
      AppUser appUser=appUserRepository.findByUsername(username);
      appUser.getRoles().add(appRole);

    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppRole appRole=appRoleRepository.findById(role).get();
        AppUser appUser=appUserRepository.findByUsername(username);
        appUser.getRoles().remove(appRole);

    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
