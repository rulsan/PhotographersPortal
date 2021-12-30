package com.portal.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Object User
 *
 * @author SkillsUpPracticeTeam
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "login")
    private String login;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "registration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    @Column(name = "last_activity_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastActivityDate;

    @Column(name = "visible_email")
    private Boolean visibleEmail;

    @Column(name = "avatar_id")
    private String avatarId;

    @Column(name = "camera")
    private String camera;

    @Column(name = "contacts")
    private String contacts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * registration Date getter
     *
     * @return Date or null
     */
    public Date getRegistrationDate() {
        if (registrationDate != null) {
            return new Date(registrationDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * registration Date setter
     *
     */
    public void setRegistrationDate(Date registrationDate) {
       this.registrationDate = registrationDate;
    }

    @PrePersist
    public void onCreateUser(){
        setRegistrationDate(new Date());
    }
    
    /**
     * Last Activity Date getter
     *
     * @return Date or null
     */
    public Date getLastActivityDate() {
        if (lastActivityDate != null) {
            return new Date(lastActivityDate.getTime());
        } else {
            return null;
        }
    }

    public void setLastActivityDate(Date lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }
    
    @PreUpdate
    public void onUpdateUser(){
        setLastActivityDate(new Date());
    }

    public Boolean getVisibleEmail() {
        return visibleEmail;
    }

    public void setVisibleEmail(Boolean visibleEmail) {
        this.visibleEmail = visibleEmail;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    public String getCamera() {
        return camera;
    }

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }


    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final User user = (User) obj;
        return id != null && id.equals(user.getId());
    }
}