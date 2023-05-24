package ru.vkokourov.vote_restaurants.to;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Value
@ToString
public class MenuTo {

    RestaurantTo restaurant;

    LocalDate localDate;

    List<DishTo> dishes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MenuTo menuTo = (MenuTo) o;

        if (!restaurant.equals(menuTo.restaurant)) return false;
        if (!localDate.equals(menuTo.localDate)) return false;
        return dishes.equals(menuTo.dishes);
    }

    @Override
    public int hashCode() {
        int result = restaurant.hashCode();
        result = 31 * result + localDate.hashCode();
        result = 31 * result + dishes.hashCode();
        return result;
    }
}
