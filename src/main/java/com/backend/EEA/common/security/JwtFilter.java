package com.backend.EEA.common.security;


import com.backend.EEA.model.dto.masterdata.UserDto;
import com.backend.EEA.model.dto.masterdata.UserSessionDto;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
//@Order(2)
@Order(Ordered.LOWEST_PRECEDENCE)
public class JwtFilter  implements Filter {
 //   private static final String jwtTokenCookieName = "JWT-TOKEN";
    private static final String keySignature = "ydhsskkkk";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String path = request.getRequestURI();
        System.out.println(path);

         if(request.getMethod().equals("POST") && request.getRequestURI().trim().equals("/EEA/portal-data/customer")){
             filterChain.doFilter(request, response);
             return;
         }

        if(token == null || token.isEmpty()){
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, content-type , username");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }
        if (token.startsWith("Bearer ")) {

            Claims jwtClaims = JwtUtil.getSubject(request, HttpHeaders.AUTHORIZATION, keySignature);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES.mappedFeature());
           // jwt = jwt.replace("=", ":");
        //    UserSessionDto dto  = objectMapper.readValue( jwt, UserSessionDto.class);
            setUpSpringAuthentication(jwtClaims);
            filterChain.doFilter(request, response);

        }
        else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            return;
        }

    }
    private void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List) claims.get("roles");
         LinkedHashMap<String,Object> authData = ((LinkedHashMap<String,Object>)claims.get("sub"));
         Integer dateMilliSecond = (Integer) claims.get("iat");
         //check expired
        //(java.util.Date.getMillisOf(new Date()) -  TimeUnit.MILLISECONDS.convert(20, TimeUnit.DAYS))
        authorities =(List)authData.get("roles");
        UserSessionDto userSessionDto = new UserSessionDto(String.valueOf(authData.get("userName")),"",Long.parseLong(String.valueOf(authData.get("id"))), authorities);
        userSessionDto.setEmail(String.valueOf(authData.get("email")));
        userSessionDto.setName(String.valueOf(authData.get("name")));
        String administrative = String.valueOf(authData.get("administrativeId"));
        if(administrative != null && !administrative.equals("null")) {
            userSessionDto.setAdministrativeId(Long.parseLong(administrative));
        }
        //authorities.stream().map(SimpleGrantedAuthority::new
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userSessionDto, null,authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);

    }
  //  @Bean
   /* public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.cors().and()
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/EEA/portal-data/customer").permitAll();
        return http.build();
    }*/
  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(Arrays.asList("*"));
      configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
      configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
      configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
  }
}