package ru.vkokourov.vote_restaurants.util;

import lombok.experimental.UtilityClass;
import ru.vkokourov.vote_restaurants.model.Vote;
import ru.vkokourov.vote_restaurants.to.VoteTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class VoteUtil {

    public static VoteTo createTo(Vote vote) {
        final var restaurant = vote.getRestaurant();
        return new VoteTo(
                vote.getId(),
                vote.getLocalDate(),
                restaurant == null ? null : restaurant.getId());
    }

    public static List<VoteTo> getTos(Collection<Vote> votes) {
        return votes.stream()
                .map(VoteUtil::createTo)
                .collect(Collectors.toList());
    }
}
