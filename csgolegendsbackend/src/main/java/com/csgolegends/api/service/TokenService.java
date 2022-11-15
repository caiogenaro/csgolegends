package com.csgolegends.api.service;


import com.csgolegends.api.dto.LoginDTO;
import com.csgolegends.api.model.Usuario;
import com.csgolegends.api.repositoryimpl.UsuarioRepositoryCustom;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Autowired
    private UsuarioRepositoryCustom usuarioRepositoryCustom;

    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;


    public String gerarToken(Authentication authentication){
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));
        Usuario usuarioCompleto = usuarioRepositoryCustom.procurarUsuarioPorUsername(usuario.getUsername());
        usuarioRepositoryCustom.atualizarUltimoLogin(usuarioCompleto.getId());
        return Jwts.builder()
                .setIssuer("Backenn CsGoLegends")
                .setSubject(usuarioCompleto.getId().toString())
                .setIssuedAt(hoje)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }



}
