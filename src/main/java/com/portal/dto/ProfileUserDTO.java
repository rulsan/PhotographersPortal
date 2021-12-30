package com.portal.dto;

import javax.validation.constraints.Size;

import com.portal.util.Constants;

/**
 * ProfileUserDTO
 *
 * @author SkillsUpPracticeTeam
 */
public class ProfileUserDTO {

    @Size(min = Constants.MIN_LENGTH, max = Constants.MAX_LENGTH, message = "Имя должно быть от 4 до 16 символов")
    private String login;

    private Boolean visibleEmail;

    private String camera;

    private String contacts;

    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Boolean getVisibleEmail() {
        return visibleEmail;
    }

    public void setVisibleEmail(Boolean visibleEmail) {
        this.visibleEmail = visibleEmail;
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
}
