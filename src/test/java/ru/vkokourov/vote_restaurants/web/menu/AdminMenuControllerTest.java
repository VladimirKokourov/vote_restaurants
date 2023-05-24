package ru.vkokourov.vote_restaurants.web.menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.vkokourov.vote_restaurants.model.Dish;
import ru.vkokourov.vote_restaurants.repository.DishRepository;
import ru.vkokourov.vote_restaurants.service.MenuService;
import ru.vkokourov.vote_restaurants.util.JsonUtil;
import ru.vkokourov.vote_restaurants.web.AbstractControllerTest;
import ru.vkokourov.vote_restaurants.web.user.UserTestData;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.vkokourov.vote_restaurants.web.TestData.*;
import static ru.vkokourov.vote_restaurants.web.user.UserTestData.ADMIN_MAIL;
import static ru.vkokourov.vote_restaurants.web.user.UserTestData.USER_MAIL;

public class AdminMenuControllerTest extends AbstractControllerTest {

    public static final String REST_URL = "/api/admin/restaurants/" + RESTAURANT1_ID + "/menus";
    public static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private MenuService service;

    @Autowired
    private DishRepository dishRepository;

    @Test
    @WithUserDetails(value = UserTestData.ADMIN_MAIL)
    void addDishToMenu() throws Exception {
        final var newDish = getNewDish();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)))
                .andExpect(status().isCreated());

        final var created = DISH_MATCHER.readFromJson(action);
        final var newId = created.getId();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(created, newDish);
        DISH_MATCHER.assertMatch(dishRepository.getExisted(newId), newDish);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createInvalid() throws Exception {
        final var invalid = new Dish(null, null, null, null);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createForUserRole() throws Exception {
        final var newDish = getNewDish();
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void update() throws Exception {
        final var updated = getUpdatedDish();
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + DISH1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        DISH_MATCHER.assertMatch(dishRepository.getExisted(DISH1_ID), updated);
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateInvalid() throws Exception {
        final var invalid = new Dish(DISH1_ID, null, null, null);
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + DISH1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateForUserRole() throws Exception {
        final var updated = getUpdatedDish();
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + DISH1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + DISH1_ID))
                .andExpect(status().isNoContent());
        Assertions.assertFalse(dishRepository.get(RESTAURANT1_ID, DISH1_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void deleteForUserRole() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + DISH1_ID))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void deleteNotExistDish() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + NOT_EXIST_ENTITY_ID))
                .andExpect(status().isConflict());
    }
}
