package ru.vkokourov.vote_restaurants.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@Data
@ToString
public class MenuTo {

    RestaurantTo restaurant;

    LocalDate localDate;

    List<DishTo> dishes;

}
