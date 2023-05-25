package ru.vkokourov.vote_restaurants.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vkokourov.vote_restaurants.model.Dish;
import ru.vkokourov.vote_restaurants.repository.DishRepository;
import ru.vkokourov.vote_restaurants.repository.RestaurantRepository;
import ru.vkokourov.vote_restaurants.to.MenuTo;
import ru.vkokourov.vote_restaurants.util.DishUtil;
import ru.vkokourov.vote_restaurants.util.RestaurantUtil;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MenuService {

    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Optional<MenuTo> getMenuForLocalDate(int restaurantId, LocalDate localDate) {
        var dishes = dishRepository.getDishesByLocalDateAndRestaurantId(localDate, restaurantId);
        var restaurantTo = RestaurantUtil.createTo(restaurantRepository.getExisted(restaurantId));
        MenuTo menuTo = new MenuTo(restaurantTo, localDate, DishUtil.getTos(dishes));
        return Optional.of(menuTo);
    }

    @Transactional
    public void deleteDishFromMenu(int restaurantId, int id) {
        Dish dish = dishRepository.getExistedAndBelonged(restaurantId, id);
        dishRepository.delete(dish);
    }

    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        dish.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        return dishRepository.save(dish);
    }
}
