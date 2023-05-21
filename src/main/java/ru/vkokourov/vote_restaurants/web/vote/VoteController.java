package ru.vkokourov.vote_restaurants.web.vote;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.vkokourov.vote_restaurants.model.Vote;
import ru.vkokourov.vote_restaurants.service.VoteService;
import ru.vkokourov.vote_restaurants.to.VoteTo;
import ru.vkokourov.vote_restaurants.util.validation.ValidationUtil;
import ru.vkokourov.vote_restaurants.web.AuthUser;
import ru.vkokourov.vote_restaurants.web.restaurant.AdminRestaurantController;

import java.net.URI;
import java.time.LocalTime;
import java.util.List;

import static ru.vkokourov.vote_restaurants.util.validation.ValidationUtil.*;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class VoteController {
    static final String REST_URL = "/api/profile/votes";
    private static final String END_VOTING_TIME = "11:00";

    private final VoteService service;

    @GetMapping
    public List<VoteTo> getAll(@AuthenticationPrincipal AuthUser authUser) {
        int userId = authUser.id();
        log.info("get all Votes for User {}", userId);
        return service.getAll(userId);
    }

    @GetMapping("/{id}")
    public VoteTo get(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        int userId = authUser.id();
        log.info("get Vote {} for User {}", id, userId);
        return service.getById(userId, id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> create(@AuthenticationPrincipal AuthUser authUser, @RequestBody Vote vote) {
        checkVotingTime(LocalTime.now(), LocalTime.parse(END_VOTING_TIME));
        var userId = authUser.id();
        log.info("create Vote {} for User {}", vote, userId);
        checkNew(vote);
        var created = service.save(userId, vote);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal AuthUser authUser, @RequestBody Vote vote, @PathVariable int id) {
        checkVotingTime(LocalTime.now(), LocalTime.parse(END_VOTING_TIME));
        var userId = authUser.id();
        log.info("update Vote {} for User {}", vote, userId);
        assureIdConsistent(vote, id);
        service.update(userId, vote, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        int userId = authUser.id();
        log.info("delete Vote {} for User {}", id, userId);
        service.delete(userId, id);
    }
}
