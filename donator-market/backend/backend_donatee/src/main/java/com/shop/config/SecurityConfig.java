package com.shop.config;

import com.shop.security.JwtAuthenticationFilter;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.HeaderWriterFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.DelegatingFilterProxy;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .httpBasic().disable()
                        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .mvcMatchers("/css/**", "/js/**", "/img/**", "/offer/**", "/offers/**", "/queue/**", "/logistics/**").permitAll()
                .mvcMatchers("/main/**", "/members/**", "/images/**", "/products/**", "/main/**", "/itemDtl/**", "/cart/**", "/item/**", "/carts/**").permitAll()
                .mvcMatchers("/admin/**", "/offers/**", "/item/**", "/offer/**").hasAnyAuthority("ROLE_SELLER", "ROLE_ADMIN")
                .mvcMatchers( "/orders/**", "/item/**", "/order/**").hasAnyAuthority("ROLE_BUYER", "ROLE_ADMIN")
                .anyRequest().authenticated()
        ;

        http.addFilterBefore(
                jwtAuthenticationFilter,
                HeaderWriterFilter.class
        );
        return http.build();
    }

//    동시 세션 지원 활성화
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserLoginFailHandler userLoginFailHandler(){
        return new UserLoginFailHandler();
    }
}