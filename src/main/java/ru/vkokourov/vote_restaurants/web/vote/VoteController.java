package ru.vkokourov.vote_restaurants.web.vote;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vkokourov.vote_restaurants.service.VoteService;
import ru.vkokourov.vote_restaurants.web.restaurant.AdminRestaurantController;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class VoteController {
    static final String REST_URL = "/api/votes";

    private final VoteService service;


}
