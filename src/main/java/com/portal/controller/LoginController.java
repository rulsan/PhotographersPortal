package com.portal.controller;

import com.portal.util.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller which get login page
 *
 * @author SkillsUpPracticeTeam
 */
@Controller
public class LoginController {

    /**
     * Getting login page
     *
     * @param model login model
     * @return login view
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String signin(Model model) {
        return Constants.Views.LOGIN;
    }

    /**
     * Getting loginfailed message
     *
     * @param model loginfailed model
     * @return login view
     */
    @RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
    public String fail(Model model) {
        model.addAttribute(Constants.Models.LOGIN_FAIL_MESSAGE_KEY, Constants.Models.LOGIN_FAIL_MESSAGE);
        return Constants.Views.LOGIN;
    }
}
