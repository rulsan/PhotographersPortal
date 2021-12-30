package com.portal.dto;

import com.portal.util.Constants;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * ChangePassUserDTO
 *
 * @author SkillsUpPracticeTeam
 */
public class ChangePassUserDTO {


    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getCurrentPass() {
        return currentPass;
    }

    public void setCurrentPass(String currentPass) {
        this.currentPass = currentPass;
    }

    public String getConfirmNewPass() {
        return confirmNewPass;
    }

    public void setConfirmNewPass(String confirmNewPass) {
        this.confirmNewPass = confirmNewPass;
    }

    @NotEmpty(message = "введите текущий пароль")
    private String currentPass;

    @Size(min = Constants.MIN_LENGTH, max = Constants.MAX_LENGTH, message = "Пароль должен быть от 4 до 16 символов")
    private String newPass;
    private String confirmNewPass;
}
