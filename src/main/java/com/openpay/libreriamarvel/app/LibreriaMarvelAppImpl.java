package com.openpay.libreriamarvel.app;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Component;

@Component
public class LibreriaMarvelAppImpl implements LibreriaMarvelApp {
	String urlBase="http://gateway.marvel.com/v1/public/comics?";
	@Override
	public String getCharacters(String publicKey, String privateKey) {
		String url= generarUrl(publicKey,privateKey);
		WebClient.Builder builder = WebClient.builder();
		String JSON = builder.build().get().uri(url).retrieve().bodyToMono(String.class).block();
		
		return JSON;
	}
	
	
	
	private String generarUrl(String publicKey, String privateKey) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		String hash = generarHash(timeStamp+privateKey+publicKey);
		
		return urlBase+"ts="+timeStamp+"&apikey="+publicKey+"&hash="+hash;
	}

	private String generarHash(String mensaje) {
		byte[] digest = null;
		StringBuilder hexString = new StringBuilder();
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			digest = messageDigest.digest(mensaje.getBytes());
						
            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return hexString.toString();
	}

}

