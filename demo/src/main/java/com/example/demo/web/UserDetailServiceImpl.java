package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;

/**
 * This class is used by spring security to authenticate and authorize users
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    // UserRepository for accessing user data
    private final UserRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Overrides the loadUserByUsername method to load user details by username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieves the user with the specified username from the repository
        User currentUser = userRepository.findByUsername(username);

        // Creates a UserDetails object with the user's details and authorities
        UserDetails user = new org.springframework.security.core.userdetails.User(
                username,
                currentUser.getPassword(),
                AuthorityUtils.createAuthorityList(currentUser.getRole())
        );

        return user;
    }
}
