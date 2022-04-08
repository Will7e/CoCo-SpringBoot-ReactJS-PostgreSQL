package com.example.coco.models;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private long userId;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String occupation;
    @ManyToOne
    private Location location;
    private String Presentation;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private Boolean enabled = false;
    private Boolean locked = false;
    @ManyToMany
    private List<Interest> interests;
    @ManyToMany
    private List<Skill> skills;
    @ManyToMany
    private List<SearchType> openForSearchType;
    @ManyToMany
    private List<Search> searches;
    @ManyToMany
    private List<User> contacts;

    public User(String firstName,String lastName, String password, String email, UserRole userRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(userRole.name());
        return Collections.singleton(authority);
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
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true ;
    }
    @Override
    public boolean isEnabled(){
        return enabled;
    }

}
