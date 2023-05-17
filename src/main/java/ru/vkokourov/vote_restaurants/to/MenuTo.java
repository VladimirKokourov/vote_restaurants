package ru.vkokourov.vote_restaurants.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Data
@ToString
public class MenuTo {

    RestaurantTo restaurant;

    LocalDate localDate;

    List<DishTo> dishes;

}
