package com.mowitnow.application.service;

import com.mowitnow.application.port.in.CreateLawnUseCase;
import com.mowitnow.application.validator.DataValidator;
import com.mowitnow.domain.lawn.Lawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CreateLawnServiceTest {
    CreateLawnUseCase createLawnUseCase;

    @BeforeEach
    public void setup(){
        createLawnUseCase = new CreateLawnService();
    }
    @Test
    public void should_create_lawn_from_data() {
        String input = "5 5 ";
        Lawn lawn = createLawnUseCase.create(input);
        Assertions.assertNotNull(lawn);
        Assertions.assertEquals(0, lawn.getBottomLeft().getX());
        Assertions.assertEquals(0, lawn.getBottomLeft().getY());
        Assertions.assertEquals(5, lawn.getUpperRight().getX());
        Assertions.assertEquals(5, lawn.getUpperRight().getY());
    }
}
