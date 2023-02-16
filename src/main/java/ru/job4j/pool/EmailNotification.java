package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        String template = String.format("subject = Notification %1$s to email %2$s.\n body = Add a new event "
                + "to %1$s", user.getUsername(), user.getEmail());
        String[] subjectAndEMail = template.split("\\n");
        pool.submit(() -> send(subjectAndEMail[0], subjectAndEMail[1], user.getEmail()));
        close();
    }

    public void close() {
        pool.shutdown();
    }

    public void send(String subject, String body, String email) {
    }

}
