package ru.vkokourov.vote_restaurants.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.vkokourov.vote_restaurants.model.Restaurant;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

}
