package mate.academy.rickandmorty.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private static int characters_size;
    private final CharacterMapper characterMapper;
    private final CharacterRepository characterRepository;
    private final RickAndMortyClient rickAndMortyClient;

    @Override
    public List<Character> getCharactersByName(String name) {
        return characterRepository.findAllByName(name);
    }

    @Override
    public Character getRandomCharacter() {
        if (characters_size == 0) {
            throw new RuntimeException("There is no character in db");
        }
        Random random = new Random();
        int randomId = random.nextInt(characters_size);
        return characterRepository.findById((long) randomId)
                .orElseThrow(()
                        -> new NoSuchElementException("Can't find character with id: " + randomId));
    }

    @Override
    public List<Character> saveAllCharacters() {
        List<Character> characters = rickAndMortyClient.getAllCharacters().stream()
                .map(characterMapper::toCharacter)
                .toList();
        characters_size = characters.size();
        return characterRepository.saveAll(characters);
    }
}
