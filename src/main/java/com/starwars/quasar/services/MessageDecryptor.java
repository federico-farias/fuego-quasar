package com.starwars.quasar.services;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.starwars.quasar.exceptions.MessageException;

@Service
public class MessageDecryptor {

	public String decipher(String[]... messages) {

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

		return Stream.of(wordsFound).collect(Collectors.joining(" "));
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
