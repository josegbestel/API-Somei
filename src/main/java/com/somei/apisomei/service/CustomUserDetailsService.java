package com.somei.apisomei.service;

import com.somei.apisomei.model.Pessoa;
import com.somei.apisomei.model.enums.AuthType;
import com.somei.apisomei.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final PessoaRepository pessoaRepository;

    @Autowired
    public CustomUserDetailsService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Pessoa pessoa = pessoaRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não identificado"));

        List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("USER", "ADMIN");
        List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("USER");

        return new User(pessoa.getEmail(),
                pessoa.getSenha(),
                pessoa.getAuthType().equals(AuthType.ADMIN) ? authorityListAdmin : authorityListUser);
    }
}
