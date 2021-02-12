package com.zjl.spring_boot_security.config;

import com.zjl.spring_boot_security.service.impl.SelfUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
/**
 * @name: SecurityConfigration
 * @description: 安全配置
 * @author: zhou
 * @create: 2021-01-17 21:28
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String ORIGIN = "http://www.docway.net";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults());
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/v3/**",
                        "/user/sessionExpire/**"
                ).permitAll()
                .antMatchers(HttpMethod.POST,
                        "/user/login/**")
                .permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.logout(logout -> logout
                .logoutUrl("/user/logout/**")
                .logoutSuccessHandler(selfLogoutSuccessHandler())
                .deleteCookies("selfCookie")
        );
        http.exceptionHandling().authenticationEntryPoint(selfAuthenticationHandler())
                .accessDeniedHandler(selfAccessDecisionHandler());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(selfUserDetailService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public SelfUserDetailService selfUserDetailService() {
        return new SelfUserDetailService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SelfAuthenticationHandler selfAuthenticationHandler() {
        return new SelfAuthenticationHandler();
    }

    @Bean
    public SelfAccessDecisionHandler selfAccessDecisionHandler() {
        return new SelfAccessDecisionHandler();
    }

    @Bean
    public SelfLogoutSuccessHandler selfLogoutSuccessHandler(){
        return new SelfLogoutSuccessHandler();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(ORIGIN));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTION"));
        configuration.setAllowedHeaders(Arrays.asList("Origin", "X-Requested-With", "Content-Type", "Accept","Authorization","If-Modified-Since"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
