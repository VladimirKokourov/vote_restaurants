package ru.vkokourov.vote_restaurants.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.vkokourov.vote_restaurants.error.DataConflictException;
import ru.vkokourov.vote_restaurants.model.Dish;
import ru.vkokourov.vote_restaurants.model.Restaurant;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {

    @Query("SELECT d FROM Dish d WHERE d.localDate=:localDate AND d.restaurant.id=:restaurantId")
    List<Dish> getDishesByLocalDateAndRestaurantId(LocalDate localDate, int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    Optional<Dish> get(int restaurantId, int id);

    default Dish getExistedAndBelonged(int restaurantId, int id) {
        return get(restaurantId, id).orElseThrow(
                () -> new DataConflictException("Dish id=" + id
                        + " is not exist or doesn't belong to Restaurant id=" + restaurantId));
    }
}
