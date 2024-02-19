package com.mowitnow.infrastructure.adapters.out;

import com.mowitnow.domain.coordinate.Coordinate;
import com.mowitnow.domain.mower.Mower;
import com.mowitnow.domain.mower.Orientation;
import com.mowitnow.infrastructure.adapters.out.view.ConsoleMowerDisplayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ConsoleMowerDisplayerTest {

    @Test
    public void should_print_valid_mower_output_format() {
        Mower mower = Mower.builder()
                .coordinate(Coordinate.builder()
                        .x(1)
                        .y(3)
                        .build())
                .orientation(Orientation.N)
                .build();
        ConsoleMowerDisplayer consoleMowerDisplayer = new ConsoleMowerDisplayer();
        String actual = consoleMowerDisplayer.display(mower);

        String expected = "1 3 N ";
        Assertions.assertEquals(expected, actual);
    }
}
