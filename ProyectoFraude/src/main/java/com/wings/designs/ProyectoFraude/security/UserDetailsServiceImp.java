/*
 * Copyright (c) 2021. Wings Design.
 */

package com.wings.designs.ProyectoFraude.security;

import com.wings.designs.ProyectoFraude.user.User;
import com.wings.designs.ProyectoFraude.user.UserRepository;
import com.wings.designs.ProyectoFraude.user.privilege.Privilege;
import com.wings.designs.ProyectoFraude.user.role.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String rut) throws UsernameNotFoundException {
        try {
            final Optional<User> user = userRepository.findUsersByRut(rut);
            if (!user.isPresent()) {
                throw new UsernameNotFoundException("No user found with rut: " + rut);
            }
            return new org.springframework.security.core.userdetails.User(user.get().getRut(),user.get().getPassword(),
                    true,true,true,true,
                    getAuthorities(user.get().getRole())
                    );
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }
    private Collection<? extends GrantedAuthority> getAuthorities(final Role role) {
        return getGrantedAuthorities(getPrivileges(role));
    }
    private List<String> getPrivileges(Role role) {
        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>(role.getPrivileges());
        for (Privilege item : collection) {
            privileges.add(item.getName().name());
        }
        return privileges;
    }
    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

}
