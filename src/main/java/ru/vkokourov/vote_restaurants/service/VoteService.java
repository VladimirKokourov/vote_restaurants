package ru.vkokourov.vote_restaurants.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vkokourov.vote_restaurants.model.Vote;
import ru.vkokourov.vote_restaurants.repository.RestaurantRepository;
import ru.vkokourov.vote_restaurants.repository.UserRepository;
import ru.vkokourov.vote_restaurants.repository.VoteRepository;
import ru.vkokourov.vote_restaurants.to.VoteTo;
import ru.vkokourov.vote_restaurants.util.VoteUtil;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Transactional
    public VoteTo save(int userId, VoteTo voteTo) {
        var restaurant = restaurantRepository.getExisted(voteTo.getRestaurantId());
        var user = userRepository.getExisted(userId);
        var created = new Vote(voteTo.getLocalDate(), restaurant, user);
        return VoteUtil.createTo(voteRepository.save(created));
    }

    public void update(int userId, VoteTo voteTo, int id) {
        voteRepository.getExistedOrBelonged(userId, id);
        save(userId, voteTo);
    }

    public void delete(int userId, int id) {
        Vote vote = voteRepository.getExistedOrBelonged(userId, id);
        voteRepository.delete(vote);
    }

    public List<VoteTo> getAll(int userId) {
        return VoteUtil.getTos(voteRepository.getAll(userId));
    }

    public VoteTo getById(int userId, int id) {
        return VoteUtil.createTo(voteRepository.getExistedOrBelonged(userId, id));
    }
}
