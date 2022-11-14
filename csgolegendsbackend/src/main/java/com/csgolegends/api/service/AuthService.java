package com.csgolegends.api.service;


import com.csgolegends.api.repositoryimpl.UsuarioRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {


    @Autowired
    UsuarioRepositoryCustom usuarioRepositoryCustom;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepositoryCustom.procurarUsuarioPorUsername(username);
    }
}
