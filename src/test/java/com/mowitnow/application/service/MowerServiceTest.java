package com.mowitnow.application.service;

import com.mowitnow.application.service.impl.MowerServiceImpl;
import com.mowitnow.domain.coordinate.Coordinate;
import com.mowitnow.domain.lawn.Lawn;
import com.mowitnow.domain.mower.Mower;
import com.mowitnow.domain.mower.Orientation;
import com.mowitnow.domain.mower.Pivot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MowerServiceTest {
    MowerService mowerService;

    @BeforeEach
    public void setup() {
        mowerService = new MowerServiceImpl();
    }

    @Test
    public void should_create_mower_from_input_data() {
        String input = "1 2 N";
        Mower mower = mowerService.create(input);
        Assertions.assertNotNull(mower);
        Assertions.assertEquals(1, mower.getCoordinate().getX());
        Assertions.assertEquals(2, mower.getCoordinate().getY());
        Assertions.assertEquals(Orientation.N, mower.getOrientation());
    }

    @Test
    public void should_apply_instructions_left_forward_from_input_data() {
        Mower mower = Mower.builder()
                .coordinate(Coordinate.builder()
                        .x(1)
                        .y(2)
                        .build())
                .orientation(Orientation.N)
                .build();
        Lawn lawn = Lawn.builder()
                .bottomLeft(Coordinate.builder()
                        .x(0)
                        .y(0)
                        .build())
                .upperRight(Coordinate.builder()
                        .x(5)
                        .y(5)
                        .build())
                .build();
        List<Pivot> instructions = List.of(Pivot.LEFT, Pivot.STEP_FORWARD, Pivot.LEFT, Pivot.STEP_FORWARD, Pivot.LEFT,
                Pivot.STEP_FORWARD, Pivot.LEFT, Pivot.STEP_FORWARD, Pivot.STEP_FORWARD);

        Mower mowerAfterMovements = mowerService.applyInstructions(lawn, mower, instructions);

        Assertions.assertNotNull(mowerAfterMovements);
        Assertions.assertEquals(1, mowerAfterMovements.getCoordinate().getX());
        Assertions.assertEquals(3, mowerAfterMovements.getCoordinate().getY());
        Assertions.assertEquals(Orientation.N, mowerAfterMovements.getOrientation());
    }

    @Test
    public void should_apply_instructions_right_forward_from_input_data() {
        Mower mower = Mower.builder()
                .coordinate(Coordinate.builder()
                        .x(3)
                        .y(3)
                        .build())
                .orientation(Orientation.E)
                .build();
        Lawn lawn = Lawn.builder()
                .bottomLeft(Coordinate.builder()
                        .x(0)
                        .y(0)
                        .build())
                .upperRight(Coordinate.builder()
                        .x(5)
                        .y(5)
                        .build())
                .build();
        List<Pivot> instructions = List.of(Pivot.STEP_FORWARD, Pivot.STEP_FORWARD, Pivot.RIGHT, Pivot.STEP_FORWARD, Pivot.STEP_FORWARD,
                Pivot.RIGHT, Pivot.STEP_FORWARD, Pivot.RIGHT, Pivot.RIGHT, Pivot.STEP_FORWARD);

        Mower mowerAfterMovements = mowerService.applyInstructions(lawn, mower, instructions);

        Assertions.assertNotNull(mowerAfterMovements);
        Assertions.assertEquals(5, mowerAfterMovements.getCoordinate().getX());
        Assertions.assertEquals(1, mowerAfterMovements.getCoordinate().getY());
        Assertions.assertEquals(Orientation.E, mowerAfterMovements.getOrientation());
    }
}
