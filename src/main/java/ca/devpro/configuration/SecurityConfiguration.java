package ca.devpro.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.addFilterAfter(cookieAuthenticationFilter(), RequestHeaderAuthenticationFilter.class)
      .authorizeRequests()
      .antMatchers(
        "/api/authentication/login",
        "/api/registrations").permitAll()
      .anyRequest().authenticated()
      .and()
      .csrf().disable();
  }

  @Bean
  public Sessions sessions() {
    return new Sessions();
  }

  @Bean
  public CookieAuthenticationFilter cookieAuthenticationFilter() {
    return new CookieAuthenticationFilter(sessions(), authenticationManager());
  }

  @Bean
  public AuthenticationManager authenticationManager() {
    return new ProviderManager(Collections.singletonList(preauthAuthProvider()));
  }

  @Bean(name = "preAuthProvider")
  public PreAuthenticatedAuthenticationProvider preauthAuthProvider() {
    PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
    provider.setPreAuthenticatedUserDetailsService(new CustomAuthenticationUserDetailsService());
    return provider;
  }



}
