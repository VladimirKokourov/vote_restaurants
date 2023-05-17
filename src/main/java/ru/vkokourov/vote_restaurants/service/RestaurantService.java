package ru.vkokourov.vote_restaurants.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vkokourov.vote_restaurants.model.Restaurant;
import ru.vkokourov.vote_restaurants.repository.DishRepository;
import ru.vkokourov.vote_restaurants.repository.RestaurantRepository;
import ru.vkokourov.vote_restaurants.repository.UserRepository;
import ru.vkokourov.vote_restaurants.to.RestaurantTo;
import ru.vkokourov.vote_restaurants.util.RestaurantUtil;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;
    private final UserRepository userRepository;

    public RestaurantTo getById(int id) {
        return RestaurantUtil.createTo(restaurantRepository.getExisted(id));
    }

    public List<RestaurantTo> getAll() {
        return RestaurantUtil.getTos(restaurantRepository.findAll());
    }

    @Transactional
    public void delete(int id) {
        var restaurant = restaurantRepository.getExisted(id);
        restaurantRepository.delete(restaurant);
    }

    public void update(RestaurantTo restaurantTo) {
        var updated = restaurantRepository.getExisted(restaurantTo.getId());
        updated.setName(restaurantTo.getName());
        updated.setDescription(restaurantTo.getDescription());
        updated.setAddress(restaurantTo.getAddress());
        restaurantRepository.save(updated);
    }

    public RestaurantTo create(Restaurant restaurant) {
        return RestaurantUtil.createTo(restaurantRepository.save(restaurant));
    }

}
