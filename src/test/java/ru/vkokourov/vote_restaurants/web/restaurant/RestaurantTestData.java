package ru.vkokourov.vote_restaurants.web.restaurant;

import ru.vkokourov.vote_restaurants.to.RestaurantTo;
import ru.vkokourov.vote_restaurants.web.MatcherFactory;

import java.util.List;

public class RestaurantTestData {

    public static MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantTo.class);

    public static final int RESTAURANT1_ID = 1;
    public static final int RESTAURANT2_ID = RESTAURANT1_ID + 1;

    public static final RestaurantTo restaurantTo1 = new RestaurantTo(RESTAURANT1_ID, "Burger King", "Fast food", "Lenina st. 5");
    public static final RestaurantTo restaurantTo2 = new RestaurantTo(RESTAURANT2_ID, "Burger King", "Fast food", "Naberezhnaya st. 8");
    public static final RestaurantTo restaurantTo3 = new RestaurantTo(RESTAURANT2_ID + 1, "KFC", "Fast food", "Lenina st. 8");
    public static final RestaurantTo restaurantTo4 = new RestaurantTo(RESTAURANT2_ID + 2, "Fish and Rice", "Sushi", "Lenina st. 7");
    public static final RestaurantTo restaurantTo5 = new RestaurantTo(RESTAURANT2_ID + 3, "Borshch", "Homemade food", "Lenina st. 1");

    public static final List<RestaurantTo> restaurants = List.of(restaurantTo1, restaurantTo2, restaurantTo3, restaurantTo4, restaurantTo5);

    public static RestaurantTo getNewRestaurantTo() {
        return new RestaurantTo(null, "New Restaurant", "New", "New");
    }

    public static RestaurantTo getUpdatedRestaurantTo() {
        return new RestaurantTo(RESTAURANT1_ID, "Burger Queen", "Lounge Cafe", "Lenina st. 1");
    }
}
