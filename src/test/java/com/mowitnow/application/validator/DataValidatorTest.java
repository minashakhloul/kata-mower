package com.mowitnow.application.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DataValidatorTest {
    @Test
    public void should_validate_input_data() {
        List<String> lines = List.of(
                "5 5 ",
                "1 2 N ",
                "GAGAGAGAA"
        );
        boolean test = DataValidator.isValid(lines);
        Assertions.assertTrue(test);
    }

    @Test
    public void should_throw_exception_when_first_line_wrong() {
        List<String> lines = List.of(
                "5 5R ",
                "1 2 N ",
                "GAGAGAGAA"
        );
        Assertions.assertThrows(IllegalArgumentException.class, () -> DataValidator.isValid(lines));
    }

    @Test
    public void should_throw_exception_when_mower_line_wrong() {
        List<String> lines = List.of(
                "5 5 ",
                "1 2F N ",
                "GAGAGAGAA"
        );
        Assertions.assertThrows(IllegalArgumentException.class, () -> DataValidator.isValid(lines));
    }

    @Test
    public void should_throw_exception_when_instruction_line_wrong() {
        List<String> lines = List.of(
                "5 5 ",
                "1 2 N ",
                "GAGAGAG AA F"
        );
        Assertions.assertThrows(IllegalArgumentException.class, () -> DataValidator.isValid(lines));
    }

    @Test
    public void should_throw_exception_when_file_is_empty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> DataValidator.isValid(List.of()));
    }
}
