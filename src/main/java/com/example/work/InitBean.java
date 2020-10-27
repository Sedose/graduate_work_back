package com.example.work;

import com.microsoft.graph.models.extensions.DateTimeTimeZone;
import com.microsoft.graph.models.extensions.Event;
import com.microsoft.graph.models.extensions.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitBean implements InitializingBean {

    private final Authentication authentication;
    private final Graph graph;

    @Override
    public void afterPropertiesSet() throws Exception {
        val accessToken = authentication.getUserAccessToken();
        User user = graph.getUser(accessToken.get());
        System.out.println("Welcome " + user.displayName);
//        listCalendarEvents(accessToken.get());
        graph.listMembersInGroup(accessToken.get()).getCurrentPage().forEach(System.out::println);
    }

    private void listCalendarEvents(String accessToken) {
        // Get the user's events
        List<Event> events = graph.getEvents(accessToken);

        System.out.println("Events:");

        for (Event event : events) {
            System.out.println("Subject: " + event.subject);
            System.out.println("  Organizer: " + event.organizer.emailAddress.name);
            System.out.println("  Start: " + formatDateTimeTimeZone(event.start));
            System.out.println("  End: " + formatDateTimeTimeZone(event.end));
        }

        System.out.println();
    }

    private static String formatDateTimeTimeZone(DateTimeTimeZone date) {
        LocalDateTime dateTime = LocalDateTime.parse(date.dateTime);

        return dateTime.format(
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)) +
                " (" + date.timeZone + ")";
    }
}
