package com.api.brms.dto;

import java.util.Set;

/**
 * Payload sent *from* the client *to* the API.
 */
public class UserRequest {

    private String name;
    private String email;
    private String password;
    private Set<Long> roleIds;   // ids of roles to attach (can be null / empty)

    // getters & setters
    public String getName()                 { return name; }
    public void setName(String name)        { this.name = name; }

    public String getEmail()                { return email; }
    public void setEmail(String email)      { this.email = email; }

    public String getPassword()             { return password; }
    public void setPassword(String password){ this.password = password; }

    public Set<Long> getRoleIds()           { return roleIds; }
    public void setRoleIds(Set<Long> roleIds){ this.roleIds = roleIds; }
}
