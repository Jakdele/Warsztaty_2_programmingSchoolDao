package pl.coderslab.programmingSchoolDAO.entity;


import org.mindrot.jbcrypt.BCrypt;
import pl.coderslab.programmingSchool.model.DBManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private Integer user_group_id;

    public User(Integer id, String username, String email, String password, Integer user_group_id) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.user_group_id = user_group_id;
    }

    public User(String username, String email, String password, Integer user_group_id) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.user_group_id = user_group_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUser_group_id() {
        return user_group_id;
    }

    public void setUser_group_id(Integer user_group_id) {
        this.user_group_id = user_group_id;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", user_group_id=" + user_group_id +
                '}';
    }
}