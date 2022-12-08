package org.disz.demo.service;

import org.disz.demo.entity.Person;
import org.disz.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Person person = personRepository.getByEmail(username);
        if (person == null) {
            throw new UsernameNotFoundException("User not found");
        }

        final Collection<String> roles;
        if (person.isAdmin()) {
            roles = List.of("ROLE_ADMIN", "ROLE_USER");
        } else {
            roles = List.of("ROLE_USER");
        }

        return new User(person.getEmail(), person.getPassword(), toAuthorities(roles));
    }

    private Collection<? extends GrantedAuthority> toAuthorities(final Collection<String> roles) {
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
