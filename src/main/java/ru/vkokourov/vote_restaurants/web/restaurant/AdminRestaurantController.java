package ru.vkokourov.vote_restaurants.web.restaurant;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.vkokourov.vote_restaurants.model.Restaurant;
import ru.vkokourov.vote_restaurants.service.RestaurantService;
import ru.vkokourov.vote_restaurants.to.RestaurantTo;
import ru.vkokourov.vote_restaurants.util.validation.ValidationUtil;

import java.net.URI;

@RestController
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class AdminRestaurantController {
    static final String REST_URL = "/api/admin/restaurants";

    private final RestaurantService service;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete Restaurant {}", id);
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody RestaurantTo restaurantTo, @PathVariable int id) {
        log.info("update Restaurant {}", id);
        ValidationUtil.assureIdConsistent(restaurantTo, id);
        service.update(restaurantTo);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestaurantTo> create(@RequestBody Restaurant restaurant) {
        log.info("create Restaurant {}", restaurant);
        ValidationUtil.checkNew(restaurant);
        final var created = service.create(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
