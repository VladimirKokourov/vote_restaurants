package ru.vkokourov.vote_restaurants.util;

import lombok.experimental.UtilityClass;
import ru.vkokourov.vote_restaurants.model.Restaurant;
import ru.vkokourov.vote_restaurants.to.RestaurantTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RestaurantUtil {

    public static RestaurantTo createTo(Restaurant r) {
        return new RestaurantTo(r.getId(), r.getName(),
                r.getDescription(), r.getAddress());
    }

    public static List<RestaurantTo> getTos(Collection<Restaurant> restaurants) {
        return restaurants.stream()
                .map(RestaurantUtil::createTo)
                .collect(Collectors.toList());
    }
}
