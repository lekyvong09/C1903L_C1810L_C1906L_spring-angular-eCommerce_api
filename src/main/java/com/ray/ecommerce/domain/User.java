package com.ray.ecommerce.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "last_login_date")
    private Date lastLoginDate;

    @Column(name = "last_login_date_display")
    private Date lastLoginDateDisplay;

    @Column(name = "join_date")
    private Date joinDate;

    @Column(name = "roles")
    private String[] roles; // Role_User {read}, ROLE_ADMIN {read, update, delete}

    @Column(name = "authorities")
    private String[] authorities; // delete, insert, update, delete

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "is_not_locked")
    private boolean isNotLocked;

    public User(Long id, String userId, String firstName, String lastName, String username, String password, String email, String profileImageUrl, Date lastLoginDate, Date lastLoginDateDisplay, Date joinDate, String[] roles, String[] authorities, boolean isActive, boolean isNotLocked) {
        this.id = id;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.profileImageUrl = profileImageUrl;
        this.lastLoginDate = lastLoginDate;
        this.lastLoginDateDisplay = lastLoginDateDisplay;
        this.joinDate = joinDate;
        this.roles = roles;
        this.authorities = authorities;
        this.isActive = isActive;
        this.isNotLocked = isNotLocked;
    }
}
