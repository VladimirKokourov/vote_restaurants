package ru.vkokourov.vote_restaurants.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vkokourov.vote_restaurants.model.Vote;
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
    private final UserRepository userRepository;

    @Transactional
    public Vote save(int userId, Vote vote) {
        vote.setUser(userRepository.getExisted(userId));
        return voteRepository.save(vote);
    }

    public void update(int userId, Vote vote, int id) {
        voteRepository.getExistedOrBelonged(userId, id);
        save(userId, vote);
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
