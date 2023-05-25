package ru.vkokourov.vote_restaurants.to;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(of = {"localDate", "restaurant"})
@AllArgsConstructor
@Value
@ToString
public class MenuTo {

    RestaurantTo restaurant;

    LocalDate localDate;

    List<DishTo> dishes;
}
