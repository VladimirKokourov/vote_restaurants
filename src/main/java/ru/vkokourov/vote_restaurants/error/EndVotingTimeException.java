package ru.vkokourov.vote_restaurants.error;

public class EndVotingTimeException extends AppException {
    public EndVotingTimeException(String message) {
        super(message);
    }
}
