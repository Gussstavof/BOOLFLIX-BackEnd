package com.challenge.alura.AluraFlix.services;

import com.challenge.alura.AluraFlix.entities.profiles.Profile;
import com.challenge.alura.AluraFlix.entities.users.User;
import com.challenge.alura.AluraFlix.dtos.users.UserSignupRequest;
import com.challenge.alura.AluraFlix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("Invalid credentials")
        );
    }

    public User createUser(UserSignupRequest request) {
        if (repository.existsByEmail(request.getEmail())){
            throw new BadCredentialsException("user already exists");
        }
        Profile profile = new Profile("2", "ROLE_USER");
        if (request.getEmail().contains("adm")){
            profile = new Profile("1", "ROLE_ADM");
        }
        return repository.save(User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .profiles(profile)
                .build());
    }
}
