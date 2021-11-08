package com.crud2.tasks.trello.client;

import com.crud2.tasks.domain.TrelloBoardDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TrelloClient {

    private final RestTemplate restTemplate;

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.app.name}")
    private String trelloName;

    public List<TrelloBoardDto> getTrelloBoards() {
        return buildURL();
    }
    private List<TrelloBoardDto>buildURL(){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/johndoe41135315/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", trelloName+",id")
                .build()
                .encode()
                .toUri();
        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
        return   Arrays.stream(boardsResponse)
                .filter(n->!n.getName().isEmpty() && !n.getId().isEmpty())
                .filter(n->n.getName().contains("Kodilla"))
                .collect(Collectors.toList());
    }
}
/*
        return Optional.of(boardsResponse)
                .map(Arrays::asList).stream()
                .flatMap(o -> o.stream().map(n->n.name))
                .orElse(Collections.emptyList());

                return Optional.of(boardsResponse)
                .map(Arrays::asList).stream()
                .flatMap(n->n.stream())
                .filter(m->!m.getId().isEmpty() && !m.getName().isEmpty())
                .filter(p->p.getName().contains("Kodilla"))
                .collect(Collections.emptyList())*/
