package com.csgolegends.api.service;


import com.csgolegends.api.enums.Permissao;
import com.csgolegends.api.model.Usuario;
import com.csgolegends.api.repository.UsuarioRepository;
import com.csgolegends.api.repositoryimpl.UsuarioRepositoryCustom;
import com.csgolegends.api.util.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private UsuarioRepositoryCustom usuarioRepositoryCustom;


    public Usuario cadastrar(Usuario user) {

        Usuario usuario = validarCadastro(user);
        usuario = userRepository.save(user);
        return usuario;

    }

    public Usuario validarCadastro(Usuario user){
        boolean usernameTrigger = usuarioRepositoryCustom.isUsernameCadastrado(user);
        boolean emailTrigger = usuarioRepositoryCustom.isEmailCadastrado(user);
        boolean cpfTrigger = usuarioRepositoryCustom.isCpfCadastrado(user);

        if (usernameTrigger) {
            throw new NegocioException("Usuario já Cadastrado");
        }

        if(emailTrigger){
            throw new NegocioException("Email já Cadastrado");
        }

        if(cpfTrigger){
            throw new NegocioException("CPF já Cadastrado");
        }
        user.setPermissão(Permissao.USER.toString());

        return user;

    }
}
