package com.excilys.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

// TODO: Auto-generated Javadoc
/**
 * The Interface Date.
 */
@Constraint(validatedBy = DateValidator.class)
@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Date {

	/**
	 * Message.
	 *
	 * @return the string
	 */
	String message() default "{validation.date.invalidFormat}";

	/**
	 * Groups.
	 *
	 * @return the class[]
	 */
	Class<?>[] groups() default {};

	/**
	 * Payload.
	 *
	 * @return the class<? extends payload>[]
	 */
	Class<? extends Payload>[] payload() default {};
}