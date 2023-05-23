package ru.vkokourov.vote_restaurants.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vkokourov.vote_restaurants.model.Vote;
import ru.vkokourov.vote_restaurants.repository.RestaurantRepository;
import ru.vkokourov.vote_restaurants.repository.UserRepository;
import ru.vkokourov.vote_restaurants.repository.VoteRepository;
import ru.vkokourov.vote_restaurants.to.VoteTo;

import java.time.LocalTime;
import java.util.List;

import static ru.vkokourov.vote_restaurants.util.VoteUtil.createTo;
import static ru.vkokourov.vote_restaurants.util.VoteUtil.getTos;
import static ru.vkokourov.vote_restaurants.util.validation.ValidationUtil.*;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Value("${voting.endVotingTime}")
    @Setter
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime endVotingTime;

    @Transactional
    public VoteTo create(int userId, VoteTo voteTo) {
        checkVotingTime(LocalTime.now(), endVotingTime);
        checkNew(voteTo);
        var restaurant = restaurantRepository.getExisted(voteTo.getRestaurantId());
        var user = userRepository.getExisted(userId);
        var created = new Vote(voteTo.getLocalDate(), restaurant, user);
        return createTo(voteRepository.save(created));
    }

    @Transactional
    public void update(int userId, VoteTo voteTo, int id) {
        checkVotingTime(LocalTime.now(), endVotingTime);
        assureIdConsistent(voteTo, id);
        var updated = voteRepository.getExistedOrBelonged(userId, id);
        var restaurant = restaurantRepository.getExisted(voteTo.getRestaurantId());
        updated.setRestaurant(restaurant);
        voteRepository.save(updated);
    }

    public void delete(int userId, int id) {
        Vote vote = voteRepository.getExistedOrBelonged(userId, id);
        voteRepository.delete(vote);
    }

    public List<VoteTo> getAll(int userId) {
        return getTos(voteRepository.getAll(userId));
    }

    public VoteTo getById(int userId, int id) {
        return createTo(voteRepository.getExistedOrBelonged(userId, id));
    }
}
