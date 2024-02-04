package com.polarbookshop.catalogservice.domain;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BookValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void shouldReturnErrorWhenTitleNotProvided() {
        Book book = Book.of("1234567890", "", "Kaneman", 5.90);
        Set<ConstraintViolation<Book>> errors = validator.validate(book);
        assertThat(errors).hasSize(1);
        assertThat(errors.iterator().next().getMessage()).isEqualTo("The book title must be defined");
    }

}
