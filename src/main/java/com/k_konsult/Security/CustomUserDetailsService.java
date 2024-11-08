package com.k_konsult.Security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.k_konsult.Entity.User;
import com.k_konsult.Repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }
        User entityUser = user.get();

        return new org.springframework.security.core.userdetails.User(
                entityUser.getUsername(), 
                entityUser.getPassword(), 
                getAuthorities(entityUser.getAccess())
        );
    }

    private List<GrantedAuthority> getAuthorities(User.Access access) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(access.name()));
        return authorities;
    }

    
}