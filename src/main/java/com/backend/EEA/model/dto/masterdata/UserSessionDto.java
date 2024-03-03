package com.backend.EEA.model.dto.masterdata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

//@Component
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserSessionDto extends User implements UserDetails {
  //  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "username field can't be blank")
    private String userName;

    @Email
    private String email;

    @NotBlank(message = "name field can't be blank")
    private String name;

    //private Boolean active;

    private DepartmentDto department;

    private Long administrativeId;

    @JsonIgnoreProperties(value = {"userAssigned", "role"})
    private List<UserRoleDto> roles;

    public UserSessionDto(String username, String password,Long userId,
                         List authorities) {
        super(username, password, (Collection<? extends GrantedAuthority>) authorities.stream().map(role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toList()));
        this.userName = username;
        this.id = userId;
    }
    public UserSessionDto(){
      super("none",null, null);
    }

}
