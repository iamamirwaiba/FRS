package com.example.demo.appuser;

import com.example.demo.appuser.AppUserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table
@ToString
public class AppUser implements UserDetails {


    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name="user_id")
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String phoneNumber;
    private String email;
    @Column(nullable = false)
    private String password;
    private String DOB;
    @Column(name="user_image")
    private String userImage;
    private String longitude;
    private String latitude;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked;
    private Boolean enabled = true;
    @Column(name="bookcancledtimes",nullable = true)
    private int bookcancled;

    public AppUser(String firstName,
                   String lastName,
                   String email,
                   String phoneNumber,
                   String DOB,
                   String password,
                   AppUserRole appUserRole,Boolean locked,int bookcancled) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber=phoneNumber;
        this.DOB=DOB;
        this.password = password;
        this.appUserRole = appUserRole;
        this.locked=locked;
        this.bookcancled=bookcancled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
