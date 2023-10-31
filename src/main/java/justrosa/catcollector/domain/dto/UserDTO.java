package justrosa.catcollector.domain.dto;

import justrosa.catcollector.domain.Cat;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private Integer id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String roles;

    private Set<Integer> cats = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Set<Integer> getCats() {
        return cats;
    }

    public void setCats(Set<Integer> cats) {
        this.cats = cats;
    }
}