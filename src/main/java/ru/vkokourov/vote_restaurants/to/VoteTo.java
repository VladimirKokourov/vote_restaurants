package ru.vkokourov.vote_restaurants.to;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = true)
public class VoteTo extends BaseTo {

    @NotNull
    LocalDate localDate;

    @NotNull
    Integer restaurantId;

    public VoteTo(Integer id, LocalDate localDate, Integer restaurantId) {
        super(id);
        this.localDate = localDate;
        this.restaurantId = restaurantId;
    }
}
