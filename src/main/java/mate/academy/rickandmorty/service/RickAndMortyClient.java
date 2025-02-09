package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.CharacterDto;
import mate.academy.rickandmorty.dto.CharactersResponseDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RickAndMortyClient {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character?page=";
    private final ObjectMapper objectMapper;

    public List<CharacterDto> getAllCharacters() {
        HttpClient httpClient = HttpClient.newHttpClient();
        List<CharacterDto> characters = new ArrayList<>();
        return readAllPages(httpClient, characters);
    }

    private List<CharacterDto> readAllPages(HttpClient httpClient, List<CharacterDto> info) {
        int page = 1;
        int pages;
        do {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .GET()
                    .uri(URI.create(BASE_URL + page))
                    .build();
            try {
                HttpResponse<String> httpResponse
                        = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
                CharactersResponseDto charactersResponseDto
                        = objectMapper.readValue(httpResponse.body(), CharactersResponseDto.class);
                info.addAll(charactersResponseDto.results());
                pages = charactersResponseDto.info().pages();
                page++;
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        } while (page <= pages);

        return info;
    }
}
