package com.api.brms.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Application user that can hold one-or-many roles.
 */
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns        = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    // ---------- constructors ----------
    public User() { }

    public User(String name, String email, String password) {
        this.name     = name;
        this.email    = email;
        this.password = password;
    }

    // ---------- getters & setters ----------
    public Long getId()                     { return id; }
    public void setId(Long id)              { this.id = id; }

    public String getName()                 { return name; }
    public void setName(String name)        { this.name = name; }

    public String getEmail()                { return email; }
    public void setEmail(String email)      { this.email = email; }

    public String getPassword()             { return password; }
    public void setPassword(String p)       { this.password = p; }

    public Set<Role> getRoles()             { return roles; }
    public void setRoles(Set<Role> roles)   { this.roles = roles; }
}
