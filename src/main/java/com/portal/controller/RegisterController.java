package com.portal.controller;

import com.portal.domain.User;
import com.portal.dto.RegisterUserDTO;
import com.portal.service.UserService;
import com.portal.util.Constants;
import com.portal.util.exception.EmailAlreadyOccupiedException;
import com.portal.util.exception.LoginAlreadyOccupiedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Date;

/**
 * Controller that registers users and passes user information to DB
 *
 * @author alex, ruslan
 */
@Controller
@RequestMapping("/registration")
public class RegisterController {

    @Autowired
    private UserService userService;

    /**
     * @param model register model
     * @return registration view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String signup(ModelMap model) {
        RegisterUserDTO registerUserDTO = new RegisterUserDTO();
        model.put("registerUserDTO", registerUserDTO);
        return Constants.Views.REGISTER;
    }

    /**
     * @param registerUserDTO consists required data fields for registration
     * @param result consists errors
     * @return registration-success view if no errors
     */
    @RequestMapping(method = RequestMethod.POST)
    public String processSignup(@Valid final RegisterUserDTO registerUserDTO, final BindingResult result) {
        if (!result.hasErrors() && !(registerUserDTO.getPass()).equals(registerUserDTO.getConfirmPass())) {
            result.addError(new FieldError(Constants.Views.REGISTER, "confirmPass", Constants.Models.REGISTER_SIGNUPERROR_MESSAGE));
        }
        if (!result.hasErrors()) {
            try {
                User user = new User();
                user.setLogin(registerUserDTO.getLogin());
                user.setEmail(registerUserDTO.getEmail());
                user.setPassword(registerUserDTO.getPass());
                user.setRegistrationDate(new Date());
                user.setVisibleEmail(true);
                userService.register(user);
                return Constants.Views.REGISTRATION_SUCCESS;
            } catch (EmailAlreadyOccupiedException e) {
                result.addError(new FieldError(Constants.Views.REGISTER, "email", e.getMessage()));
            } catch (LoginAlreadyOccupiedException e) {
                result.addError(new FieldError(Constants.Views.REGISTER, "login", e.getMessage()));
            }
        }
        return Constants.Views.REGISTER;
    }
}
