package com.portal.util;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Velichkovskyi Andrey on 30.12.13.
 *
 * Public static constants
 */
public final class Constants {
    private Constants(){
    }

    public static final String EMAIL = "email";

    public static final int PICKS_USERS = 25;
    public static final int MIN_LENGTH = 4;
    public static final int MAX_LENGTH = 16;
    public static final int PHOTO_MIN_HEIGHT = 150;

    public static final Set<String> VALID_AVATAR_EXTENSIONS = new HashSet<String>();

    static {
        Constants.VALID_AVATAR_EXTENSIONS.add("png");
        Constants.VALID_AVATAR_EXTENSIONS.add("jpg");
        Constants.VALID_AVATAR_EXTENSIONS.add("gif");
        Constants.VALID_AVATAR_EXTENSIONS.add("jpeg");
    }


    public final class Models {
        private Models(){
        }

        public static final String CHANGE_PASS_REDIRECT = "successChangePassRedirect";
        public static final String CHANGE_PASS_SUCCES = "successChangePass";
        public static final String CHANGE_PASS_UNCORRECT_CURRENT_PASS = "введенный пароль не совпадает с текущим паролем";

        public static final String INDEX_WELCOME_MESSAGE_KEY = "welcome";
        public static final String INDEX_WELCOME_MESSAGE = "Добро пожаловать на Фотопортал!";
        public static final String INDEX_MAINTEXT_MESSAGE_KEY = "maintext";
        public static final String INDEX_MAINTEXT_MESSAGE = "Присоединяйтесь к нам, чтобы Ваши фотографии увидел мир.";
        public static final String LOGIN_FAIL_MESSAGE_KEY = "fail";
        public static final String LOGIN_FAIL_MESSAGE = "Неправильный емайл и/или пароль!";
        public static final String PROFILE_AVATAR_KEY = "avatarURL";
        public static final String REGISTER_SIGNUPERROR_MESSAGE = "Пароли не совпадают!";
        public static final String PROFILE_USERNAME_KEY = "username";
        public static final String PROFILE_MEMBERSINCE_KEY = "memberSince";
        public static final String PROFILE_USERDTO_KEY = "profileUserDTO";
        public static final String PROFILE_EMAIL_KEY = EMAIL;
        public static final String PROFILE_LASTDATE_KEY = "lastDate";
        public static final String PROFILE_EMAILCHECK_KEY = "emailCheck";
        public static final String PROFILE_DEVICES_KEY = "devices";
        public static final String PROFILE_CONTACTS_KEY = "contacts";
        public static final String PROFILE_USERNOTFOUND_MESSAGE = "Пользователь не найден";
        public static final String PROFILE_HIDDEN_MESSAGE = "Скрыто";
        public static final String PROFILE_LIST_KEY = "profileList";
        public static final String PROFILE_UPLOADERROR_KEY = "uploadError";
        public static final String PROFILE_UPLOADERROR_OVERSIZE_MESSAGE = "Недопустимый размер файла.";
        public static final String PROFILE_UPLOADERROR_EXTENSION_MESSAGE = "Недопустимое расширение файла.";
        public static final String PHOTO_UPLOADERROR_KEY = PROFILE_UPLOADERROR_KEY;
        public static final String PHOTO_UPLOADERROR_OVERSIZE_MESSAGE = "Недопустимый размер файла (до 10МБ)";
        public static final String PHOTO_UPLOADERROR_EXTENSION_MESSAGE = "Недопустимое расширение фотофайла.";
        public static final String LIST_OF_PHOTOS = "photoList";
    }

    public final class Views {
        private Views(){
        }

        public static final String INDEX = "index";
        public static final String LOGIN = "login";
        public static final String PROFILE = "profile";
        public static final String MODIFY_PROFILE = "update-user";
        public static final String REGISTER = "registration";
        public static final String REGISTRATION_SUCCESS = "registration-success";
        public static final String LIST = "list";
        public static final String MYPHOTO = "myPhoto";
        public static final String CHANGE_PASS= "changePass";
        public static final String REDIRECT = "redirect:/";
    }

    public final class Pathes {
        private Pathes(){
        }

        public static final String AVATARS = "/avatars/";
        public static final String PHOTOS = "/photos/";
        public static final String MINIPHOTOS = "/miniphotos/";
    }

    public final class Queryes {
        private Queryes(){
        }

        public static final String GETUSERS = "from com.portal.domain.User";
    }
}


