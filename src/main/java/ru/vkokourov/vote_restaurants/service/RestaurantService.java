package ru.vkokourov.vote_restaurants.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vkokourov.vote_restaurants.model.Restaurant;
import ru.vkokourov.vote_restaurants.repository.RestaurantRepository;
import ru.vkokourov.vote_restaurants.to.RestaurantTo;

import java.util.List;

import static ru.vkokourov.vote_restaurants.util.RestaurantUtil.createTo;
import static ru.vkokourov.vote_restaurants.util.RestaurantUtil.getTos;
import static ru.vkokourov.vote_restaurants.util.validation.ValidationUtil.assureIdConsistent;
import static ru.vkokourov.vote_restaurants.util.validation.ValidationUtil.checkNew;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantTo getById(int id) {
        return createTo(restaurantRepository.getExisted(id));
    }

    public List<RestaurantTo> getAll() {
        return getTos(restaurantRepository.findAll());
    }

    @Transactional
    public void delete(int id) {
        var restaurant = restaurantRepository.getExisted(id);
        restaurantRepository.delete(restaurant);
    }

    @Transactional
    public void update(RestaurantTo restaurantTo, int id) {
        assureIdConsistent(restaurantTo, id);
        var updated = restaurantRepository.getExisted(restaurantTo.getId());
        updated.setName(restaurantTo.getName());
        updated.setDescription(restaurantTo.getDescription());
        updated.setAddress(restaurantTo.getAddress());
        restaurantRepository.save(updated);
    }

    public RestaurantTo create(Restaurant restaurant) {
        checkNew(restaurant);
        return createTo(restaurantRepository.save(restaurant));
    }

}
