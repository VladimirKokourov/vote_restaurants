package ru.vkokourov.vote_restaurants.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
public class MenuTo extends BaseTo {

    LocalDate localDate;

    int restaurantId;

    List<DishTo> dishTos;

    public MenuTo(Integer id, LocalDate localDate, int restaurantId, List<DishTo> dishTos) {
        super(id);
        this.localDate = localDate;
        this.restaurantId = restaurantId;
        this.dishTos = dishTos;
    }
}
