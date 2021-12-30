package com.portal.controller;

import com.portal.domain.User;
import com.portal.dto.ChangePassUserDTO;
import com.portal.dto.ProfileUserDTO;
import com.portal.dto.UserListItemDTO;
import com.portal.service.AvatarService;
import com.portal.service.UserService;
import com.portal.util.Constants;
import com.portal.util.UserUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Profile Controller
 *
 * @author alex
 *         Date: 02.12.13
 */
@Controller
@RequestMapping("/profile")
public class ProfileController{

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("AvatarService")
    private AvatarService avatarService;

    /**
     * Getting current logged in user and filling the profile form
     *
     * @param model profile model
     * @return profile view
     */

    @RequestMapping
    public String fillOwnProfile(Model model) {
        final User user = userService.getCurrentUser();
        UserUtils.profileFilling(model, user);
        String avatarUrl = avatarService.getPictureUrl(user.getId(), user.getAvatarId());
        model.addAttribute(Constants.Models.PROFILE_AVATAR_KEY, avatarUrl);
        return Constants.Views.PROFILE;
    }

    /**
     * Getting user information from DB and filling the profile form
     *
     * @param model  profile model
     * @param userId id of user
     * @return profile view
     */

    @RequestMapping(value = {"/{userId}"}, method = RequestMethod.GET)
    public String fillProfile(Model model, @PathVariable("userId") int userId) {
        final User user = userService.findById(userId);
        if (user != null) {
            UserUtils.profileFilling(model, user);
            String avatarUrl = avatarService.getPictureUrl(user.getId(), user.getAvatarId());
            model.addAttribute(Constants.Models.PROFILE_AVATAR_KEY, avatarUrl);
        } else {
            model.addAttribute(Constants.Models.PROFILE_USERNAME_KEY, Constants.Models.PROFILE_USERNOTFOUND_MESSAGE);
        }
        return Constants.Views.PROFILE;
    }

    /**
     * Show List of Users
     *
     * @param map profile ModelMap
     * @return profile list
     */
    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String showProfileList(ModelMap map) {
        List<User> listOfUsers = userService.getUsers(0);
        List<UserListItemDTO> updateList = UserUtils.getDisplayedList(listOfUsers);
        map.put(Constants.Models.PROFILE_LIST_KEY, updateList);
        return Constants.Views.LIST;
    }

    /**
     * getJson from profile jsp
     *
     * @return list of avatars url
     */
    @RequestMapping("/getAvatarsUrl")
    @ResponseBody
    public List<String> getAvatarsUrl() {
        final int userId = userService.getCurrentUser().getId();
        return avatarService.getExistingPictures(userId);
    }

    /**
     * ajax post avatar url as json string
     *
     * @param url chosen avatar url
     */
    @RequestMapping(value = "/updateAvatarUrl", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAvatarUrl(@RequestParam("url") String url) {
        String[] temp = url.split("/");
        final User user = userService.getCurrentUser();
        userService.updateAvatarId(user, temp[temp.length - 1]);
    }

    /**
     * Search users which consist login
     *
     * @param login to search
     * @return list of searched users
     */
    @RequestMapping(value = "/searchUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<UserListItemDTO> searchUsers(@RequestParam("searchLogin") String login, @RequestParam("page") int page) {
        List<User> listOfUsers = new ArrayList<User>();
        if ("".equals(login)) {
            listOfUsers = userService.getUsers(page);
        } else {
            listOfUsers = userService.searchUsersByLogin(login, page);
        }
        return UserUtils.getDisplayedList(listOfUsers);
    }

    /**
     * Upload, validate and set new avatar
     *
     * @param newAvatar new image to upload
     * @param model     update model
     * @return update view
     */
    @RequestMapping(value = "/saveAvatar", method = RequestMethod.POST)
    public String saveAvatar(@RequestParam("newAvatar") MultipartFile newAvatar, Model model) {

        final User user = userService.getCurrentUser();
        final String avatarExtension = FilenameUtils.getExtension(newAvatar.getOriginalFilename());
        if (newAvatar.isEmpty() || newAvatar.getSize() > 1000000) {
            model.addAttribute(Constants.Models.PROFILE_UPLOADERROR_KEY, Constants.Models.PROFILE_UPLOADERROR_OVERSIZE_MESSAGE);
            return Constants.Views.MODIFY_PROFILE;
        } else if (!Constants.VALID_AVATAR_EXTENSIONS.contains(avatarExtension.toLowerCase())) {
            model.addAttribute(Constants.Models.PROFILE_UPLOADERROR_KEY, Constants.Models.PROFILE_UPLOADERROR_EXTENSION_MESSAGE);
            return Constants.Views.MODIFY_PROFILE;
        } else {
            String newAvatarId = avatarService.savePicture(user.getId(), newAvatar);
            userService.updateAvatarId(user, newAvatarId);
        }
        return Constants.Views.REDIRECT + Constants.Views.PROFILE;
    }

    /**
     * Showing profile editing page for the current user
     *
     * @param model is the model to populate
     * @return page template or redirect in case of error
     */
    @RequestMapping(value = {"/update-user"}, method = RequestMethod.GET)
    public String editProfile(Model model) {
        final User user = userService.getCurrentUser();
        if (user != null) {
            model.addAttribute(Constants.Models.PROFILE_USERNAME_KEY, user.getLogin());
            model.addAttribute(Constants.Models.PROFILE_MEMBERSINCE_KEY, user.getRegistrationDate());
            model.addAttribute(Constants.Models.PROFILE_EMAIL_KEY, user.getEmail());
            String avatarUrl = avatarService.getPictureUrl(user.getId(), user.getAvatarId());
            model.addAttribute(Constants.Models.PROFILE_AVATAR_KEY, avatarUrl);
            ProfileUserDTO profileUserDTO = new ProfileUserDTO();
            profileUserDTO.setVisibleEmail(user.getVisibleEmail());
            profileUserDTO.setCamera(user.getCamera());
            profileUserDTO.setContacts(user.getContacts());
            model.addAttribute(Constants.Models.PROFILE_USERDTO_KEY, profileUserDTO);
        } else {
            model.addAttribute(Constants.Models.PROFILE_USERNAME_KEY, Constants.Models.PROFILE_USERNOTFOUND_MESSAGE);
            return Constants.Views.REDIRECT + Constants.Views.PROFILE;
        }
        return Constants.Views.MODIFY_PROFILE;
    }

    /**
     * Performing validation and updating of current user with new information if valid
     *
     * @param profileUserDTO is an object containing fields to edit
     * @param model          is the model to populate
     * @return page template or redirect in case of error
     */
    @RequestMapping(value = {"/action/update-user"}, method = RequestMethod.POST)
    public String editProfileAction(final ProfileUserDTO profileUserDTO, Model model) {
        final User user = userService.getCurrentUser();
        if (user != null) {
            user.setVisibleEmail(profileUserDTO.getVisibleEmail());
            user.setCamera(profileUserDTO.getCamera());
            user.setContacts(profileUserDTO.getContacts());
            userService.updateUser(user);
        } else {
            model.addAttribute(Constants.Models.PROFILE_USERNAME_KEY, Constants.Models.PROFILE_USERNOTFOUND_MESSAGE);
            return Constants.Views.MODIFY_PROFILE;
        }
        return Constants.Views.REDIRECT + Constants.Views.PROFILE;
    }

    /**
     * @param model changePass model
     * @return changePass view
     */
    @RequestMapping(value = {"/changePass"}, method = RequestMethod.GET)
    public String changePass(ModelMap model) {
        ChangePassUserDTO changePassUserDTO = new ChangePassUserDTO();
        model.put("changePassUserDTO", changePassUserDTO);
        return Constants.Views.CHANGE_PASS;
    }

    /**
     * @param changePassUserDTO consists required data fields for change pass
     * @param result consists errors
     * @return changePass view
     */
    @RequestMapping(value = {"/changePass"}, method = RequestMethod.POST)
    public String actionToChangePass(@Valid final ChangePassUserDTO changePassUserDTO, final BindingResult result, Model model) {
        final User user = userService.getCurrentUser();
        if (!result.hasErrors() && !(user.getPassword().equals(UserUtils.md5(changePassUserDTO.getCurrentPass())))){
            result.addError(new FieldError(Constants.Views.CHANGE_PASS,"currentPass",Constants.Models.CHANGE_PASS_UNCORRECT_CURRENT_PASS));
        }
        if (!result.hasErrors() && !(changePassUserDTO.getNewPass()).equals(changePassUserDTO.getConfirmNewPass())) {
            result.addError(new FieldError(Constants.Views.CHANGE_PASS, "confirmNewPass", Constants.Models.REGISTER_SIGNUPERROR_MESSAGE));
        }
        if (!result.hasErrors()){
            user.setPassword(UserUtils.md5(changePassUserDTO.getConfirmNewPass()));
            userService.updateUser(user);
            model.addAttribute(Constants.Models.CHANGE_PASS_SUCCES, "пароль успешно изменен");
            model.addAttribute(Constants.Models.CHANGE_PASS_REDIRECT, "вернутся к редактированию профиля");

        }
        return Constants.Views.CHANGE_PASS;
    }
}
