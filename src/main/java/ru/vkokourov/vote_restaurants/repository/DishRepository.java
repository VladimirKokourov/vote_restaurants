package ru.vkokourov.vote_restaurants.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.vkokourov.vote_restaurants.model.Dish;
import ru.vkokourov.vote_restaurants.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {

    @Query("SELECT d FROM Dish d WHERE d.localDate=:localDate AND d.restaurant.id=:restaurantId")
    List<Dish> getDishesByLocalDateAndRestaurantId(LocalDate localDate, int restaurantId);
}
