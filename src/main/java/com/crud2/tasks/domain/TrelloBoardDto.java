package com.crud2.tasks.domain;

import lombok.Data;

@Data
public class TrelloBoardDto {
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}