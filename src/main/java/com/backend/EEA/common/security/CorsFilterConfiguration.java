package com.backend.EEA.common.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


//https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
@Configuration
@EnableWebSecurity
@Order(1)
public class CorsFilterConfiguration  extends WebSecurityConfigurerAdapter {
    @Value("${services.auth}")
    private String authService;
  //  @Autowired
    //private CustomOncePerRequestFilter tokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
   // http.cors();
    //http.anonymous().and().antMatcher("/EEA**").cors().and().csrf().disable();
     //   http.authorizeRequests()
       //         .antMatchers(HttpMethod.GET, "/EEA**").permitAll()
         //       .anyRequest().permitAll()
           //     .and().httpBasic();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/**").permitAll()
                .antMatchers(HttpMethod.POST, "/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll().and().cors().and().csrf().disable();
            //    .anyRequest().authenticated().and().httpBasic().and().cors().and()
              //  .csrf().disable();*/
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/EEA/basic-data**", "/EEA/portal-data**");
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) ->  web.ignoring().antMatchers("/EEA/basic-data**", "/EEA/portal-data**");
    }

}

