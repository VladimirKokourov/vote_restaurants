package ru.vkokourov.vote_restaurants.util.validation;

import lombok.experimental.UtilityClass;
import ru.vkokourov.vote_restaurants.HasId;
import ru.vkokourov.vote_restaurants.error.EndVotingTimeException;
import ru.vkokourov.vote_restaurants.error.IllegalRequestDataException;
import ru.vkokourov.vote_restaurants.error.NotTodaysDateException;

import java.time.LocalDate;
import java.time.LocalTime;

@UtilityClass
public class ValidationUtil {

    public static void checkNew(HasId bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(
                    bean.getClass().getSimpleName() + " must be new (id=null)");
        }
    }

    //  Conservative when you reply, but accept liberally
    //  (http://stackoverflow.com/a/32728226/548473)
    public static void assureIdConsistent(HasId bean, int id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.id() != id) {
            throw new IllegalRequestDataException(
                    bean.getClass().getSimpleName() + " must has id=" + id);
        }
    }

    public static void checkVotingTime(LocalTime currentTime, LocalTime endVotingTime) {
        if (currentTime.isAfter(endVotingTime)) {
            throw new EndVotingTimeException(
                    "Voting is over. You can't vote after " + endVotingTime);
        }
    }

    public static void checkTodaysDate(LocalDate localDate) {
        if (localDate == null) {
            throw new IllegalArgumentException("LocalDate is null.");
        }
        if (!localDate.equals(LocalDate.now())) {
            throw new NotTodaysDateException("Changing previous votes is not allowed.");
        }
    }
}