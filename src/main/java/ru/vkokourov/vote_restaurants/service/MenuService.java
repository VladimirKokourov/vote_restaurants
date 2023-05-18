package ru.vkokourov.vote_restaurants.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vkokourov.vote_restaurants.model.Restaurant;
import ru.vkokourov.vote_restaurants.repository.DishRepository;
import ru.vkokourov.vote_restaurants.repository.RestaurantRepository;
import ru.vkokourov.vote_restaurants.to.MenuTo;
import ru.vkokourov.vote_restaurants.util.DishUtil;
import ru.vkokourov.vote_restaurants.util.RestaurantUtil;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class MenuService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuTo getMenuForToday(int restaurantId) {
        var restaurant = restaurantRepository.getExisted(restaurantId);
        var dishes = dishRepository.getDishesByLocalDateAndRestaurantId(LocalDate.now(), restaurantId);
        return new MenuTo(RestaurantUtil.createTo(restaurant), LocalDate.now(), DishUtil.getTos(dishes));
    }

    public MenuTo getMenuForLocalDate(int restaurantId, LocalDate localDate) {
        var restaurant = restaurantRepository.getExisted(restaurantId);
        var dishes = dishRepository.getDishesByLocalDateAndRestaurantId(localDate, restaurantId);
        return new MenuTo(RestaurantUtil.createTo(restaurant), localDate, DishUtil.getTos(dishes));
    }
}
