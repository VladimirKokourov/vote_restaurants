package ru.vkokourov.vote_restaurants.web.vote;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.vkokourov.vote_restaurants.service.VoteService;
import ru.vkokourov.vote_restaurants.to.VoteTo;
import ru.vkokourov.vote_restaurants.web.AuthUser;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class VoteController {
    static final String REST_URL = "/api/profile/votes";

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
    public ResponseEntity<VoteTo> create(@AuthenticationPrincipal AuthUser authUser, @RequestBody @Valid VoteTo voteTo) {
        var userId = authUser.id();
        log.info("create Vote {} for User {}", voteTo, userId);
        var created = service.create(userId, voteTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@AuthenticationPrincipal AuthUser authUser, @RequestBody @Valid VoteTo voteTo, @PathVariable int id) {
        var userId = authUser.id();
        log.info("update {} for User {}", voteTo, userId);
        service.update(userId, voteTo, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        int userId = authUser.id();
        log.info("delete Vote {} for User {}", id, userId);
        service.delete(userId, id);
    }
}
