package ru.vkokourov.vote_restaurants.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class DishTo extends NamedTo {

    String description;

    Long price;

    public DishTo(Integer id, String name, String description, Long price) {
        super(id, name);
        this.description = description;
        this.price = price;
    }
}
