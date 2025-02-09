package mate.academy.rickandmorty.dto;

import java.util.List;

public record CharactersResponseDto(CharactersInfoDto info, List<CharacterDto> results) {
}
