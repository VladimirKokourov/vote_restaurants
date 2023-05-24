package ru.vkokourov.vote_restaurants.web;

import ru.vkokourov.vote_restaurants.model.Restaurant;
import ru.vkokourov.vote_restaurants.to.RestaurantTo;
import ru.vkokourov.vote_restaurants.to.VoteTo;

import java.time.LocalDate;
import java.util.List;

public class TestData {
    public static MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingEqualsComparator(VoteTo.class);
    public static MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingEqualsComparator(RestaurantTo.class);

    public static final int VOTE1_ID = 1;
    public static final int VOTE2_ID = 2;
    public static final int RESTAURANT1_ID = 1;
    public static final int RESTAURANT2_ID = 2;
    public static final int NOT_EXIST_RESTAURANT_ID = Integer.MAX_VALUE;
    public static final int GUEST_VOTE_ID = 8;
    public static final LocalDate LOCAL_DATE = LocalDate.of(2023, 5, 15);

    public static final VoteTo voteTo1 = new VoteTo(VOTE1_ID, LOCAL_DATE, RESTAURANT1_ID);
    public static final VoteTo voteTo2 = new VoteTo(VOTE2_ID, LocalDate.now(), RESTAURANT2_ID);
    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT1_ID, "Burger King", "Fast Food", "Lenina st. 5");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT2_ID, "Burger King", "Fast Food", "Naberezhnaya st. 8");

    public static final List<VoteTo> votes = List.of(voteTo2, voteTo1);

    public static VoteTo getNewVoteTo() {
        return new VoteTo(null, LocalDate.now(), RESTAURANT1_ID);
    }

    public static VoteTo getUpdatedTo() {
        return new VoteTo(VOTE2_ID, LocalDate.now(), RESTAURANT1_ID);
    }

    public static VoteTo getUpdatedNotToday() {
        return new VoteTo(VOTE1_ID, LOCAL_DATE, RESTAURANT2_ID);
    }

    public static RestaurantTo getNewRestaurantTo() {
        return new RestaurantTo(null, "New Restaurant", "New", "New");
    }

    public static RestaurantTo getUpdatedRestaurantTo() {
        return new RestaurantTo(RESTAURANT1_ID, "Burger Queen", "Lounge Cafe", "Lenina st. 1");
    }
}
