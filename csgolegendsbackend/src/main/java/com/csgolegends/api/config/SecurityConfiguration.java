package com.csgolegends.api.config;



import com.csgolegends.api.service.AuthService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private AuthService authService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    //Autorização
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests() // require authorization
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // for the CORS preflight check
                .antMatchers("/user", "/user/**").permitAll()  // the open API endpoints and resources
                .antMatchers("/proplayer", "/proplayer/**").permitAll()
                .and().csrf().disable();

    }


    //Recursos estaticos
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
