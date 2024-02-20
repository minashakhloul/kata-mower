package com.mowitnow.application.service;

import com.mowitnow.application.port.in.CreateSimulationUseCase;
import com.mowitnow.application.port.in.LaunchSimulationUseCase;
import com.mowitnow.application.service.impl.MowerServiceImpl;
import com.mowitnow.domain.coordinate.Coordinate;
import com.mowitnow.domain.lawn.Lawn;
import com.mowitnow.domain.mower.Mower;
import com.mowitnow.domain.mower.Orientation;
import com.mowitnow.domain.mower.Pivot;
import com.mowitnow.domain.simulation.Simulation;
import com.mowitnow.infrastructure.adapters.out.view.ConsoleMowerDisplayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

class SimulationServiceTest {
    CreateSimulationUseCase createSimulationUseCase;
    LaunchSimulationUseCase launchSimulationUseCase;

    @BeforeEach
    public void setup() {
        SimulationService simulationService = new SimulationService(new MowerServiceImpl(), new ConsoleMowerDisplayer());
        createSimulationUseCase = simulationService;
        launchSimulationUseCase = simulationService;
    }

    @Test
    void should_create_simulation_from_input_data() {
        List<String> lines = List.of(
                "5 5 ",
                "1 2 N ",
                "GAGAGAGAA",
                "3 3 E",
                "AADAADADDA"
        );
        Simulation simulation = createSimulationUseCase.create(lines);
        Assertions.assertNotNull(simulation);
        Assertions.assertNotNull(simulation.getMowersAndInstructions());
        Assertions.assertEquals(2, simulation.getMowersAndInstructions().size());
        Iterator<Mower> iterator = simulation.getMowersAndInstructions().keySet().iterator();
        Mower mower = iterator.next();
        Mower secondMower = iterator.next();
        Assertions.assertEquals(1, mower.getCoordinate().getX());
        Assertions.assertEquals(2, mower.getCoordinate().getY());
        Assertions.assertEquals(Orientation.N, mower.getOrientation());
        List<Pivot> pivots = simulation.getMowersAndInstructions().get(mower);
        List<Pivot> expectedPivots = List.of(Pivot.LEFT, Pivot.STEP_FORWARD, Pivot.LEFT, Pivot.STEP_FORWARD, Pivot.LEFT,
                Pivot.STEP_FORWARD, Pivot.LEFT, Pivot.STEP_FORWARD, Pivot.STEP_FORWARD);
        Assertions.assertEquals(expectedPivots, pivots);

        Assertions.assertEquals(3, secondMower.getCoordinate().getX());
        Assertions.assertEquals(3, secondMower.getCoordinate().getY());
        Assertions.assertEquals(Orientation.E, secondMower.getOrientation());
        List<Pivot> secondMowerPivots = simulation.getMowersAndInstructions().get(secondMower);
        List<Pivot> expectedSecondPivots = List.of(Pivot.STEP_FORWARD, Pivot.STEP_FORWARD, Pivot.RIGHT, Pivot.STEP_FORWARD, Pivot.STEP_FORWARD,
                Pivot.RIGHT, Pivot.STEP_FORWARD, Pivot.RIGHT, Pivot.RIGHT, Pivot.STEP_FORWARD);
        Assertions.assertEquals(expectedSecondPivots, secondMowerPivots);
    }

    @Test
    void should_launch_simulation_correctly() {
        List<String> lines = List.of(
                "5 5 ",
                "1 2 N ",
                "GAGAGAGAA",
                "3 3 E",
                "AADAADADDA"
        );
        Simulation simulation = createSimulationUseCase.create(lines);
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
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        launchSimulationUseCase.launch(simulation, lawn);
        System.out.flush();
        System.setOut(old);
        Assertions.assertEquals("1 3 N \n5 1 E", baos.toString().strip());
    }
}
