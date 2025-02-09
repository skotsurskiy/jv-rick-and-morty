package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {
    List<Character> getCharactersByName(String name);

    Character getRandomCharacter();

    List<Character> saveAllCharacters();
}
