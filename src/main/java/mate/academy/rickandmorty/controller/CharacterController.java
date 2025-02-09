package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Character controller", description = "Rick and Morty character controller")
@RestController
@RequestMapping("/api/character")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterService characterService;

    @Operation(
            summary = "Get characters by name",
            description = "Get list of characters filtered by name"
    )
    @GetMapping("/search")
    public List<Character> getCharactersByName(@RequestParam String name) {
        return characterService.getCharactersByName(name);
    }

    @Operation(
            summary = "Get random character",
            description = "Randomly generates one character"
    )
    @GetMapping("/random")
    public Character getRandomCharacter() {
        return characterService.getRandomCharacter();
    }
}
