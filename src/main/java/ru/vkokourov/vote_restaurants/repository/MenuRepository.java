package ru.vkokourov.vote_restaurants.repository;

import org.springframework.transaction.annotation.Transactional;
import ru.vkokourov.vote_restaurants.model.Menu;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<Menu> {
}
