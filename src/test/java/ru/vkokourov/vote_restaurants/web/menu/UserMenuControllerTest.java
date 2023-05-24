package ru.vkokourov.vote_restaurants.web.menu;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.vkokourov.vote_restaurants.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.vkokourov.vote_restaurants.web.TestData.*;
import static ru.vkokourov.vote_restaurants.web.user.UserTestData.USER_MAIL;

public class UserMenuControllerTest extends AbstractControllerTest {

    public static final String REST_URL = "/api/restaurants/" + RESTAURANT1_ID + "/menus";
    public static final String REST_URL_SLASH = REST_URL + '/';
    public static final String REST_URL_SLASH_TODAY = REST_URL_SLASH + "today";
    public static final String REST_URL_NOT_EXIST_RESTAURANT = "/api/restaurants/" + NOT_EXIST_ENTITY_ID + "/menus/today";


    @Test
    @WithUserDetails(value = USER_MAIL)
    void getToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH_TODAY))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_TO_MATCHER.contentJson(menuTo1));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getByLocalDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + LOCAL_DATE))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_TO_MATCHER.contentJson(menuTo2));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getEmptyMenu() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + LOCAL_DATE_EMPTY))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_TO_MATCHER.contentJson(menuEmpty));
    }

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH_TODAY))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_NOT_EXIST_RESTAURANT))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
