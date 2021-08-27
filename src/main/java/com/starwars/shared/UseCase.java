package com.starwars.shared;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotación para casos de uso que ayuda a desacoplar que tiene como propósito
 * desacoplar la implementación de negoción con el Framework en turno.
 * 
 * @author Federico Farias Sánchez
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface UseCase {

}
