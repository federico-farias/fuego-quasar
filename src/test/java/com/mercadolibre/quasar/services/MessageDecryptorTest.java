package com.mercadolibre.quasar.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.starwars.quasar.domain.exceptions.MessageException;
import com.starwars.quasar.domain.services.MessageDecryptor;

public class MessageDecryptorTest {
	
	private MessageDecryptor decryptor;
	
	@BeforeEach
	public void setUp() {
		this.decryptor = new MessageDecryptor();
	}
	
	@Test
	public void itShouldDecipherWhenWordsAreConsistent() {
		String expect = "este es un mensaje secreto";
		String message = this.decryptor.decipher(
				new String[] { "este", "", "", "mensaje", "" },
				new String[] { "", "es", "", "", "secreto" }, 
				new String[] { "este", "", "un", "", "" });
		assertEquals(message, expect);
	}
	
	@Test
	public void itShouldFailDecipherWhenWordsAreNotConsistent() {
		assertThrows(MessageException.class, () -> {
			this.decryptor.decipher(
					new String[] { "OK", "", "", "mensaje", "" },
					new String[] { "", "es", "", "", "secreto" }, 
					new String[] { "este", "", "un", "", "" });
		});
	}

}
