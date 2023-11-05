package justrosa.catcollector.domain;

import jakarta.persistence.*;
import justrosa.catcollector.domain.dto.UserDTO;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// The annotations in here are Hibernate annotations
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String roles;

    @OneToMany(mappedBy = "collector")
    private Set<Cat> cats = new HashSet<>();

    public User(String username, String password, String firstName, String lastName, String roles) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }

    public User() {
    }

    public UserDTO dto() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(this.id);
        userDTO.setUsername(this.username);
        userDTO.setFirstName(this.firstName);
        userDTO.setLastName(this.lastName);
        return userDTO;
    }

    public UserDTO dtoWithCats() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(this.id);
        userDTO.setUsername(this.username);
        userDTO.setFirstName(this.firstName);
        userDTO.setLastName(this.lastName);
        userDTO.setCats(this.cats.stream().map(Cat::dto).collect(Collectors.toList()));
        return userDTO;
    }

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

    public Set<Cat> getCats() {
        return cats;
    }

    public void setCats(Set<Cat> cats) {
        this.cats = cats;
    }
}
