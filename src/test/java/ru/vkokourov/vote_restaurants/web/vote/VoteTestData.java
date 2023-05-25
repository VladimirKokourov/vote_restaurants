package ru.vkokourov.vote_restaurants.web.vote;

import ru.vkokourov.vote_restaurants.to.VoteTo;
import ru.vkokourov.vote_restaurants.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

import static ru.vkokourov.vote_restaurants.web.TestData.LOCAL_DATE;
import static ru.vkokourov.vote_restaurants.web.restaurant.RestaurantTestData.RESTAURANT1_ID;
import static ru.vkokourov.vote_restaurants.web.restaurant.RestaurantTestData.RESTAURANT2_ID;

public class VoteTestData {

    public static MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingEqualsComparator(VoteTo.class);

    public static final int VOTE1_ID = 1;
    public static final int VOTE2_ID = VOTE1_ID + 1;
    public static final int GUEST_VOTE_ID = 8;

    public static final VoteTo voteTo1 = new VoteTo(VOTE1_ID, LOCAL_DATE, RESTAURANT1_ID);
    public static final VoteTo voteTo2 = new VoteTo(VOTE2_ID, LocalDate.now(), RESTAURANT2_ID);

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
}
