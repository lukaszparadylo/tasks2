package com.crud2.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class TrelloCardDto {

    private String name;
    private String description;
    private String pos;
    private String listId;


}