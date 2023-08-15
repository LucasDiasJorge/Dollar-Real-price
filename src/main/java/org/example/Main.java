package org.example;

import org.example.thread.Cotacao;

import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class Main {
    public static void main(String[] args) {

        System.out.println("\t Running ...");

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://economia.awesomeapi.com.br/json/last/usd-brl"))
                .build();

        new Cotacao(client, request).run();
    }
}