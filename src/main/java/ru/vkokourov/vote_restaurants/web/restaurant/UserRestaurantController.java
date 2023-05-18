package ru.vkokourov.vote_restaurants.web.restaurant;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vkokourov.vote_restaurants.service.RestaurantService;
import ru.vkokourov.vote_restaurants.to.RestaurantTo;

import java.util.List;

@RestController
@RequestMapping(value = UserRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class UserRestaurantController {
    static final String REST_URL = "/api/restaurant";

    private final RestaurantService service;

    @GetMapping("/{id}")
    public RestaurantTo get(@PathVariable int id) {
        log.info("get restaurant {}", id);
        return service.getById(id);
    }

    @GetMapping
    public List<RestaurantTo> getAll() {
        log.info("getAll restaurants");
        return service.getAll();
    }
}
