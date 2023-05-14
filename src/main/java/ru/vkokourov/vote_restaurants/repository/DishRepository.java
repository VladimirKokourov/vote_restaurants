package ru.vkokourov.vote_restaurants.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.vkokourov.vote_restaurants.model.Dish;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {

}
