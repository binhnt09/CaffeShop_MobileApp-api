package com.example.ceffeshop_mobileappdev.security;

import com.example.ceffeshop_mobileappdev.entity.User;
import com.example.ceffeshop_mobileappdev.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String login) {
        LOG.debug("Authenticating {}", login);

        return userRepository.findOneByEmailIgnoreCase(login)
            .map(user -> createSpringSecurityUser(login, user))
            .orElseThrow(() -> new UsernameNotFoundException("User with email " + login + " was not found in the database"));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLogin, User user) {
        if (!"Active".equalsIgnoreCase(user.getStatus())) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " is not activated");
        }
        
        String roleName = user.getRoleID() != null ? user.getRoleID().getRoleName() : "ROLE_USER";
        if (!roleName.startsWith("ROLE_")) {
            roleName = "ROLE_" + roleName.toUpperCase();
        }
        
        List<GrantedAuthority> grantedAuthorities = Collections.singletonList(
            new SimpleGrantedAuthority(roleName)
        );

        return new UserWithId(
            String.valueOf(user.getId()),
            user.getEmail(),
            user.getPasswordHash(),
            grantedAuthorities
        );
    }

    public static class UserWithId extends org.springframework.security.core.userdetails.User {
        private final String id;

        public UserWithId(String id, String username, String password, java.util.Collection<? extends GrantedAuthority> authorities) {
            super(username, password, authorities);
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }
}
