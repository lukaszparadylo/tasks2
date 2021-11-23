package com.crud2.tasks.sheduler;

import com.crud2.tasks.domain.Mail;
import com.crud2.tasks.repository.TaskRepository;
import com.crud2.tasks.service.SimpleEmailService;
import com.crud2.tasks.trello.client.config.AdminConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
//jannowak1407897
//Jannowak,12344321
@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: Once a day email";
    private final SimpleEmailService simpleEmailService;
    private final TaskRepository taskRepository;
    private final AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        simpleEmailService.send(
                new Mail(
                        adminConfig.getAdminMail(),
                        SUBJECT,
                        "Currently in database you got: " + size + " tasks"
                )
        );
    }
}
