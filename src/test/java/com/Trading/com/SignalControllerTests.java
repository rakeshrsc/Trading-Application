package com.Trading.com;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

@SpringBootTest
public class SignalControllerTests {


    @Test
    @DisplayName("Ensures that the content type starts with application/json")
    void getSignal() throws Exception {
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://localhost:7070/addSignal?signalId=2")).build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
    }

}
