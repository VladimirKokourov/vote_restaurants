package ru.vkokourov.vote_restaurants.web.menu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.vkokourov.vote_restaurants.model.Dish;
import ru.vkokourov.vote_restaurants.service.MenuService;

import java.net.URI;

import static ru.vkokourov.vote_restaurants.util.validation.ValidationUtil.assureIdConsistent;
import static ru.vkokourov.vote_restaurants.util.validation.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminMenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class AdminMenuController {
    static final String REST_URL = "/api/admin/restaurants/{restaurantId}/menus";

    private final MenuService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> addDishToMenu(@RequestBody Dish dish, @PathVariable int restaurantId) {
        log.info("create Dish for Restaurant {}", restaurantId);
        checkNew(dish);
        var created = service.save(dish, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(UserMenuController.REST_URL + "/" + created.getLocalDate())
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish, @PathVariable int restaurantId, @PathVariable int id) {
        log.info("update Dish {} for Restaurant {}", id, restaurantId);
        assureIdConsistent(dish, id);
        service.save(dish, restaurantId);
    }

    @DeleteMapping("/{dishId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDishFromMenu(@PathVariable int restaurantId, @PathVariable int dishId) {
        log.info("delete Dish {} from Restaurant {}", dishId, restaurantId);
        service.deleteDishFromMenu(restaurantId, dishId);
    }


}
