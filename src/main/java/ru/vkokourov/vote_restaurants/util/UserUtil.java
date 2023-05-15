package ru.vkokourov.vote_restaurants.util;

import lombok.experimental.UtilityClass;
import ru.vkokourov.vote_restaurants.model.Role;
import ru.vkokourov.vote_restaurants.model.User;
import ru.vkokourov.vote_restaurants.to.UserTo;

@UtilityClass
public class UserUtil {

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }
}