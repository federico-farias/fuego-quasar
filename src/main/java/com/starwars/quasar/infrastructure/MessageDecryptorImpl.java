package com.starwars.quasar.infrastructure;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.starwars.quasar.domain.exceptions.MessageException;
import com.starwars.quasar.domain.services.MessageDecryptor;

/**
 * Clase que implementa el contrato con la firma sugerida para 
 * descifrar el mensaje de la nave.
 * @author Federico Farias Sánchez
 *
 */
@Service
public class MessageDecryptorImpl implements MessageDecryptor {

	/**
	 * Implementación de la firma sugerida para descifrar el mensaje de la nave.
	 * 
	 * @param messages Arreglo de mensajes a descifrar.
	 * @return Mensaje descifrado.
	 */
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

	/**
	 * Método para validar si la palabra es valida.
	 * 
	 * @param word Palabra a validar.
	 * @return Indica si la palabra es válida.
	 */
	private boolean wordIsValid(String word) {
		if (word == null) {
			return false;
		}
		return !word.trim().isEmpty();
	}

	/**
	 * Método que identifica si el mensaje cuenta con más palabras.
	 * 
	 * @param message Mensaje a validar.
	 * @param wordPosition Posición de la palabra dentro del mensaje. 
	 * @return Indica si cuenta o no con más palabras.
	 */
	private boolean containMoreWords(String[] message, int wordPosition) {
		return wordPosition < message.length;
	}

	/**
	 * Método para buscar el número máximo de palabras según los mensajes proporcionados.
	 * 
	 * @param messages Mensajes en las que se buscará el número máximo de palabras. 
	 * @return Obtiene el número máximo que se encontró.
	 */
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
