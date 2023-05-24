package ru.vkokourov.vote_restaurants.to;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DishTo extends NamedTo {

    Long price;

    public DishTo(Integer id, String name, Long price) {
        super(id, name);
        this.price = price;
    }
}
