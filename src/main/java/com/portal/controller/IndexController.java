package com.portal.controller;

import com.portal.util.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller which get main page
 *
 * @author SkillsUpPracticeTeam
 */
@Controller
public class IndexController {

    /**
     * Getting default main page
     *
     * @param model index model
     * @return index view
     */
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String printWelcome(Model model) {
        model.addAttribute(Constants.Models.INDEX_WELCOME_MESSAGE_KEY, Constants.Models.INDEX_WELCOME_MESSAGE);
        model.addAttribute(Constants.Models.INDEX_MAINTEXT_MESSAGE_KEY, Constants.Models.INDEX_MAINTEXT_MESSAGE);
        return Constants.Views.INDEX;
    }
}
