package com.securityexamples.services;

import com.securityexamples.entities.User;
import com.securityexamples.exceptions.UsernameAlreadyExistsException;
import com.securityexamples.model.security.SecurityUser;
import com.securityexamples.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    // this method try to find username at database
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
        return new SecurityUser(user);
    }

    @Transactional
    public void createUser(User user){
        var u = userRepository.findByUsername(user.getUsername());

        if(u.isPresent()){
            throw new UsernameAlreadyExistsException();
        }
        var userDb = new User();
        userDb.setUsername(user.getUsername());
        // encoding password with passwordEncoder
        userDb.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userDb);
    }
}
