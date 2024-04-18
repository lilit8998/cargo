package com.example.cargo.configuration;

import com.example.cargo.security.UserDetailService;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailService userDetailService;

//    public SecurityConfig(UserDetailService userDetailService, PasswordEncoder passwordEncoder) {
//        this.userDetailService = userDetailService;
//        this.passwordEncoder = passwordEncoder;
//    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry
                        -> authorizationManagerRequestMatcherRegistry
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/user/registration").permitAll()
                        .requestMatchers("/loginPage").permitAll()
                        .requestMatchers("/gallery").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/calculate").permitAll()
                        .requestMatchers("/contact").permitAll()
                        .anyRequest().permitAll())
                .formLogin(customizer -> customizer
                        .loginPage("/user/loginPage")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/user/account"))
                .logout(customizer -> customizer
                        .logoutSuccessUrl("/"));
        return httpSecurity.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(userDetailService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder);
//        return authenticationProvider;
//    }
}
