package ru.vkokourov.vote_restaurants.error;

import java.time.LocalTime;

public class EndVotingTimeException extends AppException {
    public EndVotingTimeException(String message) {
        super(message);
    }
}
