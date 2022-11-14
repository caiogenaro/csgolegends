package com.csgolegends.api.repositoryimpl;


import com.csgolegends.api.model.Usuario;
import com.csgolegends.api.util.BaseEntityResource;

import javax.persistence.Query;

public class UsuarioRepositoryImpl extends BaseEntityResource implements UsuarioRepositoryCustom  {
    @Override
    public boolean isUsernameCadastrado(Usuario usuario) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT count(u.user_id) ");
        sb.append("FROM usuarios u ");
        sb.append("WHERE u.username = :usuario");
        Query q = em.createNativeQuery(sb.toString());
        q.setParameter("usuario", usuario.getUsername());
        Integer resultado = Integer.valueOf(q.getSingleResult().toString());
        return resultado > 0;
    }

    @Override
    public boolean isEmailCadastrado(Usuario usuario) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT count(u) ");
        sb.append("FROM usuarios u ");
        sb.append("WHERE u.email = :email");
        Query q = em.createNativeQuery(sb.toString());
        q.setParameter("email", usuario.getEmail());
        Integer resultado = Integer.valueOf(q.getSingleResult().toString());
        return resultado > 0;
    }

    @Override
    public boolean isCpfCadastrado(Usuario usuario) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT count(u) ");
        sb.append("FROM usuarios u ");
        sb.append("WHERE u.cpf = :cpf");
        Query q = em.createNativeQuery(sb.toString());
        q.setParameter("cpf", usuario.getCpf());
        Integer resultado = Integer.valueOf(q.getSingleResult().toString());
        return resultado > 0;
    }

    @Override
    public Usuario procurarUsuarioPorUsername(String username) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT u.username, u.password ");
        sb.append("FROM usuarios u ");
        sb.append("WHERE u.username = :username");
        Query q = em.createNativeQuery(sb.toString());
        q.setParameter("username", username);
        Object[] resultado = (Object[]) q.getSingleResult();
        Usuario usuario = new Usuario(resultado[0].toString(), resultado[1].toString());
        return usuario;
    }
}
