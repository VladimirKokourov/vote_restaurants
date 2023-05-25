package ru.vkokourov.vote_restaurants.web.vote;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.vkokourov.vote_restaurants.repository.VoteRepository;
import ru.vkokourov.vote_restaurants.service.VoteService;
import ru.vkokourov.vote_restaurants.to.VoteTo;
import ru.vkokourov.vote_restaurants.util.JsonUtil;
import ru.vkokourov.vote_restaurants.web.AbstractControllerTest;

import java.time.LocalTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.vkokourov.vote_restaurants.web.restaurant.RestaurantTestData.RESTAURANT1_ID;
import static ru.vkokourov.vote_restaurants.web.restaurant.RestaurantTestData.RESTAURANT2_ID;
import static ru.vkokourov.vote_restaurants.web.user.UserTestData.*;
import static ru.vkokourov.vote_restaurants.web.vote.VoteController.REST_URL;
import static ru.vkokourov.vote_restaurants.web.vote.VoteTestData.*;

public class VoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL_SLASH = REST_URL + '/';

    @Autowired
    private VoteRepository repository;

    @Autowired
    private VoteService service;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + VOTE1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(voteTo1));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_TO_MATCHER.contentJson(votes));
    }

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + VOTE1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getNotFound() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + GUEST_VOTE_ID))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + VOTE2_ID))
                .andExpect(status().isNoContent());
        Assertions.assertFalse(repository.get(USER_ID, VOTE2_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void deleteDataConflict() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + GUEST_VOTE_ID))
                .andExpect(status().isConflict());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void deleteAfterEndVotingTime() throws Exception {
        service.setEndVotingTime(LocalTime.MIN);
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + VOTE2_ID))
                .andExpect(status().isForbidden());

        Assertions.assertTrue(repository.get(USER_ID, VOTE2_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void deleteNotTodaysDate() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL_SLASH + VOTE1_ID))
                .andExpect(status().isForbidden());

        Assertions.assertTrue(repository.get(USER_ID, VOTE1_ID).isPresent());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void update() throws Exception {
        service.setEndVotingTime(LocalTime.MAX);
        final var updatedTo = getUpdatedTo();
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + VOTE2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedTo)))
                .andExpect(status().isNoContent());

        VOTE_TO_MATCHER.assertMatch(service.getById(USER_ID, VOTE2_ID), updatedTo);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateAfterEndVotingTime() throws Exception {
        service.setEndVotingTime(LocalTime.MIN);
        final var updatedTo = getUpdatedTo();
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + VOTE2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedTo)))
                .andExpect(status().isForbidden());

        VOTE_TO_MATCHER.assertMatch(service.getById(USER_ID, VOTE2_ID), voteTo2);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateNotTodaysDate() throws Exception {
        final var updatedTo = getUpdatedNotToday();
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + VOTE1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updatedTo)))
                .andExpect(status().isForbidden());

        VOTE_TO_MATCHER.assertMatch(service.getById(USER_ID, VOTE1_ID), voteTo1);
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateInvalid() throws Exception {
        final var invalid = new VoteTo(VOTE2_ID, null, null);
        service.setEndVotingTime(LocalTime.MAX);
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + VOTE2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateDuplicate() throws Exception {
        final var invalid = new VoteTo(VOTE2_ID, voteTo2.getLocalDate(), RESTAURANT2_ID);
        service.setEndVotingTime(LocalTime.MAX);
        perform(MockMvcRequestBuilders.put(REST_URL_SLASH + VOTE2_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createWithLocation() throws Exception {
        final var newVoteTo = getNewVoteTo();
        service.setEndVotingTime(LocalTime.MAX);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVoteTo)));

        final var created = VOTE_TO_MATCHER.readFromJson(action);
        final var newId = created.getId();
        newVoteTo.setId(newId);
        VOTE_TO_MATCHER.assertMatch(created, newVoteTo);
        VOTE_TO_MATCHER.assertMatch(service.getById(ADMIN_ID, newId), newVoteTo);
    }

    @Test
    @WithUserDetails(value = GUEST_MAIL)
    void createInvalid() throws Exception {
        final var invalid = new VoteTo(null, null, RESTAURANT1_ID);
        service.setEndVotingTime(LocalTime.MAX);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createDuplicate() throws Exception {
        final var invalid = new VoteTo(null, voteTo2.getLocalDate(), RESTAURANT2_ID);
        service.setEndVotingTime(LocalTime.MAX);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}
