package com.csgolegends.api.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "username")
    @NotBlank(message = "Username é obrigatório")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "Password é obrigatório")
    private String senha;

    @Column(name = "email")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @Column(name = "cpf")
    @NotBlank(message = "Cpf é obrigatório")
    private String cpf;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    private Date dataCadastro;

    @Column(name = "ultimo_login")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = DateSerializer.class)
    @JsonDeserialize(using = DateDeserializers.DateDeserializer.class)
    private Date lastLogin;

    @Column(name="permissao")
    private String permissao;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="perfils")
    private List<Perfil> perfis = new ArrayList<>();

    public Usuario() {
        super();
    }

    public Usuario(String username, String senha) {
        this.username = username;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }



    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfis;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataCadastro() {
        return this.dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getPermissão() {
        return permissao;
    }

    public void setPermissão(String permissao) {
        this.permissao = permissao;
    }

    public void adicionarPerfil(Perfil perfil){
        perfis.add(perfil);
    }
    public void removerPerfil(Perfil perfil){
        perfis.stream().filter(p -> p.getNome() != perfil.getNome()).collect(Collectors.toList());
    }
}
