package com.waxes27.africrestuser.user.services;

import com.waxes27.africrestuser.user.models.User;
import com.waxes27.africrestuser.user.repository.UserRepository;
import com.waxes27.africrestuser.user.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public User createNewUser(User user) throws IllegalAccessException {
        user.setPassword(new PasswordEncoder().bCryptPasswordEncoder().encode(user.getPassword()));
        if (userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new IllegalAccessException("User already Registered");
        };
        return userRepository.save(user);
    }

    public User getCurrentUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    public boolean removeUser(String username) {
        userRepository.findByUsername(username).ifPresent(userRepository::delete);

        return userRepository.findByUsername(username).isPresent();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
