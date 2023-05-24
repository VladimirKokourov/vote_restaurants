package ru.vkokourov.vote_restaurants.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Value;
import ru.vkokourov.vote_restaurants.util.validation.NoHtml;

@Value
@EqualsAndHashCode(callSuper = true)
public class RestaurantTo extends NamedTo {

    @NotBlank
    @Size(min = 2, max = 128)
    @NoHtml
    String description;

    @NotBlank
    @Size(min = 2, max = 128)
    @NoHtml
    String address;

    public RestaurantTo(Integer id, String name, String description, String address) {
        super(id, name);
        this.description = description;
        this.address = address;
    }
}
