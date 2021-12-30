package com.portal.util;

import com.portal.domain.User;
import com.portal.dto.UserListItemDTO;
import org.apache.commons.io.FilenameUtils;
import org.springframework.ui.Model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Date: 21.11.13
 */
public final class UserUtils {
    private UserUtils() {
    }

    /**
     * md5 algorithm
     *
     * @param password to encode
     * @return hex encoded password
     */
    public static String md5(String password) {
        final int mask1 = 0xFF;
        final int mask2 = 0x100;
        final int toBase = 16;
        StringBuilder builder = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(password.getBytes());
            byte[] byteData = md.digest();

            //convert byte to hex format
            for (byte aByteData : byteData) {
                builder.append(Integer.toString((aByteData & mask1) + mask2, toBase).substring(1));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
        return builder.toString();
    }

    /**
     * Fill user profile
     *
     * @param model to fill
     * @param user  to fill profile
     * @return model
     */

    public static Model profileFilling(Model model, User user) {
        model.addAttribute(Constants.Models.PROFILE_USERNAME_KEY, user.getLogin());
        model.addAttribute(Constants.Models.PROFILE_LASTDATE_KEY, user.getLastActivityDate());
        model.addAttribute(Constants.Models.PROFILE_MEMBERSINCE_KEY, user.getRegistrationDate());
        model.addAttribute(Constants.Models.PROFILE_EMAILCHECK_KEY, user.getEmail());
        if (user.getVisibleEmail()) {
            model.addAttribute(Constants.EMAIL, user.getEmail());
        } else {
            model.addAttribute(Constants.EMAIL, Constants.Models.PROFILE_HIDDEN_MESSAGE);
        }
        model.addAttribute(Constants.Models.PROFILE_DEVICES_KEY, user.getCamera());
        model.addAttribute(Constants.Models.PROFILE_CONTACTS_KEY, user.getContacts());
        return model;
    }

    /**
     * generates an ID name, saves the original extension
     *
     * @param fileName
     * @return photoName
     */
    public static String generatePhotoName(String fileName) {
        String photoId = UUID.randomUUID().toString();
        String fileExtension = FilenameUtils.getExtension(fileName).toLowerCase();
        return photoId + "." + fileExtension;
    }

    /**
     * Getting list of users to display
     *
     * @param listOfUsers list of users
     * @return list of users to display
     */
    public static List<UserListItemDTO> getDisplayedList(List<User> listOfUsers) {
        List<UserListItemDTO> updateList = new ArrayList<UserListItemDTO>();
        for (User user : listOfUsers) {
            UserListItemDTO listItem = new UserListItemDTO();
            listItem.setId(user.getId());
            listItem.setLogin(user.getLogin());
            if (user.getVisibleEmail()) {
                listItem.setDisplayedEmail(user.getEmail());
            } else {
                listItem.setDisplayedEmail(Constants.Models.PROFILE_HIDDEN_MESSAGE);
            }
            updateList.add(listItem);
        }
        return updateList;
    }

    /**
     * Resize image according to params
     *
     * @param image to resize
     * @param height new image height
     * @return resize image
     */
    public static BufferedImage resizeImage(final BufferedImage image, final int height){
        if(image != null && height > 0){
            double currentHeight = (double)image.getHeight();
            double currentWidth = (double)image.getWidth();
            double k = currentHeight/height;
            int width = (int)(currentWidth/k);
            final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            final Graphics2D graphics2D = bufferedImage.createGraphics();
            graphics2D.drawImage(image, 0, 0, width, height, null);
            graphics2D.dispose();
            return bufferedImage;
        }
        return null;
    }
}
