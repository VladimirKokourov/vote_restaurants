package ru.vkokourov.vote_restaurants.to;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class VoteTo extends BaseTo {

    LocalDate localDate;

    int restaurantId;

    public VoteTo(Integer id, LocalDate localDate, int restaurantId) {
        super(id);
        this.localDate = localDate;
        this.restaurantId = restaurantId;
    }
}