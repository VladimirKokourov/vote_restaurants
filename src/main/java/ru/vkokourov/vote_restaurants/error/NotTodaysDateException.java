package ru.vkokourov.vote_restaurants.error;

public class NotTodaysDateException extends AppException {
    public NotTodaysDateException(String message) {
        super(message);
    }
}
