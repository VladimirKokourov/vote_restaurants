package ru.vkokourov.vote_restaurants.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class DishTo extends NamedTo {

    Long price;

    public DishTo(Integer id, String name, Long price) {
        super(id, name);
        this.price = price;
    }
}
