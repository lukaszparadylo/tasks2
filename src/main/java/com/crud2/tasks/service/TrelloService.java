package com.crud2.tasks.service;

import com.crud2.tasks.domain.CreatedTrelloCardDto;
import com.crud2.tasks.domain.Mail;
import com.crud2.tasks.domain.TrelloBoardDto;
import com.crud2.tasks.domain.TrelloCardDto;
import com.crud2.tasks.trello.client.TrelloClient;
import com.crud2.tasks.trello.client.config.AdminConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrelloService {
    private static final String SUBJECT = "Tasks: New Trello Card";
    private final TrelloClient trelloClient;
    private final SimpleEmailService emailService;
    private final AdminConfig adminConfig;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }
    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
        System.out.println("2");
        emailService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                "New card: " + trelloCardDto.getName() + " has been created on your Trello account"
        ));
        System.out.println("3");
        if(newCard==null) return new CreatedTrelloCardDto();
        else return newCard;
    }

}
