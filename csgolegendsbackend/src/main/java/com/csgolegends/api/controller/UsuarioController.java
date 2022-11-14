package com.csgolegends.api.controller;

import com.csgolegends.api.model.Usuario;
import com.csgolegends.api.repository.UsuarioRepository;
import com.csgolegends.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UsuarioController {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private UsuarioService userService;


    @GetMapping(value = "/listarTodos")
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(userRepository.findAll());
    }


    @PostMapping(value = "/cadastrar")
    public ResponseEntity<Usuario> cadastrar (@Valid @RequestBody Usuario user ) {
        return ResponseEntity.ok(userService.cadastrar(user));
    }






}
