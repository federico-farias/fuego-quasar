package com.starwars.quasar.domain.services;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.starwars.quasar.domain.exceptions.MessageException;

@Service
public class MessageDecryptorImpl implements MessageDecryptor {

	@Override
	public String getMessage(String[]... messages) {

		int maxWords = findMaxLenght(messages);

		String wordsFound[] = new String[maxWords];

		for (int wordPosition = 0; wordPosition < maxWords; wordPosition++) {

			for (int messagePosition = 0; messagePosition < messages.length; messagePosition++) {

				String message[] = messages[messagePosition];

				if (!containMoreWords(message, wordPosition)) {
					continue;
				}

				if (!wordIsValid(message[wordPosition])) {
					continue;
				}

				String word = message[wordPosition].trim().toLowerCase();

				if (wordsFound[wordPosition] != null) {
					if (wordsFound[wordPosition].equals(word)) {
						continue;
					} else {
						throw new MessageException("No fue posible obtener el mensaje.");
					}
				}

				wordsFound[wordPosition] = word;
			}
		}
		
		String message = Stream.of(wordsFound).collect(Collectors.joining(" "));
		
		if (message.contains("null")) {
			throw new MessageException("No hay suficiente información para descifrar el mensaje.");
		}

		return message;
	}

	private boolean wordIsValid(String word) {
		if (word == null) {
			return false;
		}
		return !word.trim().isEmpty();
	}

	private boolean containMoreWords(String[] message, int wordPosition) {
		return wordPosition < message.length;
	}

	private int findMaxLenght(String[][] messages) {
		int maxLenght = 0;
		for (int i = 0; i < messages.length; i++) {
			if (messages[i].length > maxLenght) {
				maxLenght = messages[i].length;
			}
		}
		return maxLenght;
	}

}
