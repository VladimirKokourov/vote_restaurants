package ru.vkokourov.vote_restaurants.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vkokourov.vote_restaurants.repository.VoteRepository;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;


}
