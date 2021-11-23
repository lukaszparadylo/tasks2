package com.crud2.tasks.trello.client.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AdminConfig {
    @Value("jannowak1407897@gmail.com")
    private String adminMail;
}