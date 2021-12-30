package com.portal.dto;

/**
 * User list item dto for displaying
 *
 * Date: 23.12.13
 */
public class UserListItemDTO {

    private Integer id;

    private String login;

    private String displayedEmail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDisplayedEmail() {
        return displayedEmail;
    }

    public void setDisplayedEmail(String displayedEmail) {
        this.displayedEmail = displayedEmail;
    }
}
