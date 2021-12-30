package com.portal.dto;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Size;
import com.portal.util.Constants;

/**
 * RegisterUserDTO
 *
 * @author SkillsUpPracticeTeam
 */
public class RegisterUserDTO {

    @Size(min = Constants.MIN_LENGTH, max = Constants.MAX_LENGTH, message = "Имя должно быть от 4 до 16 символов")
    private String login;

    @NotEmpty(message = "Введите E-mail")
    @Pattern(regexp = "^(?:[a-zA-Z0-9_'^&/+-])+(?:\\.(?:[a-zA-Z0-9_'^&/+-])+)" +
            "*@(?:(?:\\[?(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\\.)" +
            "{3}(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\]?)|(?:[a-zA-Z0-9-]+\\.)" +
            "+(?:[a-zA-Z]){2,}\\.?)$",
            message = "E-mail введен не корректно")
    private String email;

    @Size(min = Constants.MIN_LENGTH, max = Constants.MAX_LENGTH, message = "Пароль должен быть от 4 до 16 символов")
    private String pass;
    private String confirmPass;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

}
