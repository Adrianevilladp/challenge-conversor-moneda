package com.alurachallenge.conversor.calculo;

import com.alurachallenge.conversor.constants.MessageConstants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversion {

    public void realizaConversion(String monedaActual, String monedaDeCambio, double cantidad){
        URI direccion = URI
                .create(String.format(
                        "https://v6.exchangerate-api.com/v6/339d325e66222c838cfbdb10/pair/%s/%s/%s",
                        monedaActual, monedaDeCambio, cantidad));
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonResponse = new Gson().fromJson(response.body(), JsonObject.class);
            double conversionResult = jsonResponse.get("conversion_result").getAsDouble();

            String message = String.format("Cantidad convertida: %.2f", conversionResult);

            JOptionPane.showMessageDialog(
                    null,
                    message,
                    MessageConstants.MENSAJE_RESUTADO_CONVERSION,
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error durante la conversion: " + e.getMessage(),
                    "***** ERROR ******",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


}
