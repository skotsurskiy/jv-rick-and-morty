package mate.academy.rickandmorty.repository;

import java.util.List;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    @Query(value = "SELECT * FROM characters c WHERE c.name LIKE CONCAT('%', :name, '%')",
            nativeQuery = true)
    List<Character> findAllByName(@Param("name") String name);
}
