package com.example.eatandgo.security;

import com.example.eatandgo.model.Customer;
import com.example.eatandgo.model.UserBaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class AppUserDetails implements UserDetails {
    private String email;
    private String fullName;
    private String password;
    private Boolean isEnabled;
    private List<GrantedAuthority> authorities;

    public AppUserDetails(Customer customer) {
        this.email = customer.getEmail();
        this.fullName = customer.getFullName();
        this.password = customer.getPassword();
        this.isEnabled = customer.getIsVerified();
        this.authorities = Stream.of( new SimpleGrantedAuthority(customer.getRole().name()))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return isEnabled;
    }

    public String getFullName() {
        return fullName;
    }
}
