package com.revature.rms.search.entites.employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AppUser implements Serializable {

    private int id;
    private String email;
    private String username;
    private List<String> role;

    public AppUser() {
        super();
    }

    public AppUser(String email, String username, List<String> role) {
        this.email = email;
        this.username = username;
        this.role = role;
    }

    public AppUser(int id, String email, String username, List<String> role) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id == appUser.id &&
                Objects.equals(email, appUser.email) &&
                Objects.equals(username, appUser.username) &&
                role == appUser.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, role);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }

}
