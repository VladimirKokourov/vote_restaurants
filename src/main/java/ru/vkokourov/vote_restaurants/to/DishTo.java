package ru.vkokourov.vote_restaurants.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class DishTo extends NamedTo {

    LocalDate localDate;

    Long price;

    public DishTo(Integer id, String name, LocalDate localDate, Long price) {
        super(id, name);
        this.localDate = localDate;
        this.price = price;
    }
}
