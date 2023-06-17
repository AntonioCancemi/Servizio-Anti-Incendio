package com.gestionale.model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import lombok.NoArgsConstructor;

@NoArgsConstructor

public class ServerListener {
	@Autowired
	private RestTemplate restTemplate; // Dipendenza per effettuare chiamate HTTP

	private String url;

	public void onProbeAlert(Probe probe) throws JsonProcessingException {
		// logica di invio dati
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String jsonBody = ow.writeValueAsString(probe);
		try {
			// Crea l'oggetto URL della destinazione
			URL endpointUrl = new URL(this.url);

			// Apri una connessione HTTP con l'URL
			HttpURLConnection connection = (HttpURLConnection) endpointUrl.openConnection();

			// Imposta il metodo di richiesta su POST
			connection.setRequestMethod("POST");

			// in formato JSON
			connection.setRequestProperty("Content-Type", "application/json");

			// Abilita l'invio dei dati nel corpo della richiesta
			connection.setDoOutput(true);

			// Crea lo stream di output per inviare i dati JSON nel corpo della richiesta
			DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
			outputStream.writeBytes(jsonBody);
			outputStream.flush();
			outputStream.close();

			// Leggi la risposta dal server
			int responseCode = connection.getResponseCode();
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			StringBuilder response = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				response.append(line);
			}

			reader.close();

			// Stampa la risposta del server
			System.out.println("Codice di risposta: " + responseCode);
			System.out.println("Risposta: " + response.toString());

			// Chiudi la connessione
			connection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
