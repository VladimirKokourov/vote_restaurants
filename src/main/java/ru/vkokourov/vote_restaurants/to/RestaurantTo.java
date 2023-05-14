package ru.vkokourov.vote_restaurants.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo {

    String description;

    String address;

    public RestaurantTo(Integer id, String name, String description, String address) {
        super(id, name);
        this.description = description;
        this.address = address;
    }
}
