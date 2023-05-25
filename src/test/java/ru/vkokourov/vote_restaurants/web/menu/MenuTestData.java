package ru.vkokourov.vote_restaurants.web.menu;

import ru.vkokourov.vote_restaurants.model.Dish;
import ru.vkokourov.vote_restaurants.to.DishTo;
import ru.vkokourov.vote_restaurants.to.MenuTo;
import ru.vkokourov.vote_restaurants.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

import static ru.vkokourov.vote_restaurants.web.TestData.LOCAL_DATE;
import static ru.vkokourov.vote_restaurants.web.TestData.LOCAL_DATE_EMPTY;
import static ru.vkokourov.vote_restaurants.web.restaurant.RestaurantTestData.restaurantTo1;

public class MenuTestData {

    public static MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingEqualsComparator(Dish.class);
    public static MatcherFactory.Matcher<MenuTo> MENU_TO_MATCHER = MatcherFactory.usingEqualsComparator(MenuTo.class);

    public static final int DISH1_ID = 1;

    public static final DishTo dishTo1 = new DishTo(DISH1_ID, "Burger", 599L);
    public static final DishTo dishTo2 = new DishTo(DISH1_ID + 1, "Cheeseburger", 699L);
    public static final DishTo dishTo3 = new DishTo(DISH1_ID + 11, "Burger", 599L);
    public static final DishTo dishTo4 = new DishTo(DISH1_ID + 12, "Cheeseburger", 699L);
    public static final DishTo dishTo5 = new DishTo(DISH1_ID + 13, "Hamburger", 799L);

    public static final List<DishTo> dishes1 = List.of(dishTo3, dishTo4, dishTo5);
    public static final List<DishTo> dishes2 = List.of(dishTo1, dishTo2);

    public static final MenuTo menuTo1 = new MenuTo(restaurantTo1, LocalDate.now(), dishes1);
    public static final MenuTo menuTo2 = new MenuTo(restaurantTo1, LOCAL_DATE, dishes2);
    public static final MenuTo menuEmpty = new MenuTo(restaurantTo1, LOCAL_DATE_EMPTY, List.of());

    public static Dish getNewDish() {
        return new Dish(null, "NewDish", LocalDate.now(), 100L);
    }

    public static Dish getUpdatedDish() {
        return new Dish(DISH1_ID, "Updated", LocalDate.now(), 111L);
    }
}
