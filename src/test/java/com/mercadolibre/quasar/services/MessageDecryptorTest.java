package com.mercadolibre.quasar.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.starwars.quasar.domain.exceptions.MessageException;
import com.starwars.quasar.domain.services.MessageDecryptorImpl;

public class MessageDecryptorTest {
	
	private MessageDecryptorImpl decryptor;
	
	@BeforeEach
	public void setUp() {
		this.decryptor = new MessageDecryptorImpl();
	}
	
	@Test
	public void itShouldDecipherWhenWordsAreConsistent() {
		String expect = "este es un mensaje secreto";
		String message = this.decryptor.getMessage(
				new String[] { "este", "", "", "mensaje", "" },
				new String[] { "", "es", "", "", "secreto" }, 
				new String[] { "este", "", "un", "", "" });
		assertEquals(message, expect);
	}
	
	@Test
	public void itShouldFailDecipherWhenWordsAreNotConsistent() {
		assertThrows(MessageException.class, () -> {
			this.decryptor.getMessage(
					new String[] { "OK", "", "", "mensaje", "" },
					new String[] { "", "es", "", "", "secreto" }, 
					new String[] { "este", "", "un", "", "" });
		});
	}

}
