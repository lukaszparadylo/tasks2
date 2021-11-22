package com.crud2.tasks.controller;

import com.crud2.tasks.domain.CreatedTrelloCard;
import com.crud2.tasks.domain.TrelloBoardDto;
import com.crud2.tasks.domain.TrelloCardDto;
import com.crud2.tasks.service.TrelloService;
import com.crud2.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController{

    private final TrelloService trelloService;
    private final TrelloClient trelloClient;

    @GetMapping("getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloService.fetchTrelloBoards();
        //return trelloClient.getTrelloBoards();
    }

    @PostMapping("createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloService.createTrelloCard(trelloCardDto);
        //return trelloClient.createNewCard(trelloCardDto);
    }
}