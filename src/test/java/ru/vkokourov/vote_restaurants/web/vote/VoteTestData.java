package ru.vkokourov.vote_restaurants.web.vote;

import ru.vkokourov.vote_restaurants.model.Restaurant;
import ru.vkokourov.vote_restaurants.model.Vote;
import ru.vkokourov.vote_restaurants.to.VoteTo;
import ru.vkokourov.vote_restaurants.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

public class VoteTestData {
    public static MatcherFactory.Matcher<VoteTo> VOTE_TO_MATCHER = MatcherFactory.usingEqualsComparator(VoteTo.class);

    public static final int VOTE1_ID = 1;
    public static final int VOTE2_ID = 2;
    public static final int RESTAURANT1_ID = 1;
    public static final int RESTAURANT2_ID = 2;
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

    public static Vote getUpdated() {
        return new Vote(VOTE1_ID, LOCAL_DATE, restaurant2);
    }
}
