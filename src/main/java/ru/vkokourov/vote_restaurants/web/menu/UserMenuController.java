package ru.vkokourov.vote_restaurants.web.menu;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vkokourov.vote_restaurants.service.MenuService;
import ru.vkokourov.vote_restaurants.to.MenuTo;

import java.time.LocalDate;

@RestController
@RequestMapping(value = UserMenuController.REST_URL,
        produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class UserMenuController {
    static final String REST_URL = "/api/restaurants/{restaurantId}/menus";

    private final MenuService service;

    @GetMapping("/today")
    public ResponseEntity<MenuTo> getMenuForToday(@PathVariable int restaurantId) {
        log.info("get menu of restaurant {} for today", restaurantId);
        return ResponseEntity.of(service.getMenuForLocalDate(restaurantId, LocalDate.now()));
    }

    @GetMapping("/{localDate}")
    public ResponseEntity<MenuTo> getMenuForToday(@PathVariable int restaurantId,
                                                  @PathVariable LocalDate localDate) {
        log.info("get menu of restaurant {} for {}", restaurantId, localDate);
        return ResponseEntity.of(service.getMenuForLocalDate(restaurantId, localDate));
    }
}
