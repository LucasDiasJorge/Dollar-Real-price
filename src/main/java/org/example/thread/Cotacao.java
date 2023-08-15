package org.example.thread;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class Cotacao extends Thread{

    private HttpClient client;
    private HttpRequest request;

    public Cotacao(HttpClient client, HttpRequest request) {

        this.client = client;
        this.request = request;

        System.out.println("\t\tCotação instance ...");
    }

    public void run() {

        while(true) {
            System.out.println("\t Running inside while loop ...");

            try {

                System.out.println("\n\n\n");

                HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                System.out.println(request.method() + " " + response.request().uri());
                System.out.println("status: " + response.statusCode());
                System.out.println("Response Body: " + response.body());

                JSONObject json = new JSONObject(response.body().toString());

                System.out.println(Double.parseDouble(String.valueOf(json.getJSONObject("USDBRL").get("bid"))));

                System.out.println("\n");

            } catch (Exception e) {
                System.out.println(e);
            }

            try {
                sleep(15000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}