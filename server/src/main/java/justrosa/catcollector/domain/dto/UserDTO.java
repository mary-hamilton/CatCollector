package justrosa.catcollector.domain.dto;

import justrosa.catcollector.domain.Cat;
import justrosa.catcollector.domain.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {

    private Integer id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String roles;

    private List<CatDTO> cats = new ArrayList<>();


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

    public List<CatDTO> getCats() {
        return cats;
    }

    public void setCats(List<CatDTO> cats) {
        this.cats = cats;
    }
}
