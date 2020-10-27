package com.example.work;

import com.microsoft.graph.models.extensions.Event;
import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.models.extensions.User;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.options.QueryOption;
import com.microsoft.graph.requests.extensions.GraphServiceClient;
import com.microsoft.graph.requests.extensions.IDirectoryObjectCollectionWithReferencesPage;
import com.microsoft.graph.requests.extensions.IEventCollectionPage;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Graph {

    private IGraphServiceClient getClient(String accessToken) {
        val authProvider = new SimpleAuthProvider(accessToken);
        return GraphServiceClient.builder()
                .authenticationProvider(authProvider)
                .buildClient();
    }

    public User getUser(String accessToken) {
        val graphClient = getClient(accessToken);
        return graphClient.me()
                .buildRequest()
                .get();
    }

    public List<Event> getEvents(String accessToken) {
        IGraphServiceClient graphClient = getClient(accessToken);
        val options = new LinkedList<Option>();
        options.add(new QueryOption("orderby", "createdDateTime DESC"));
        IEventCollectionPage eventPage = graphClient
                .me()
                .events()
                .buildRequest(options)
                .select("subject,organizer,start,end")
                .get();
        return eventPage.getCurrentPage();
    }

    public IDirectoryObjectCollectionWithReferencesPage listMembersInGroup(String accessToken) {
        IGraphServiceClient graphClient = getClient(accessToken);
        return graphClient.groups("{id}")
                .members()
                .buildRequest()
                .get();
    }
}
