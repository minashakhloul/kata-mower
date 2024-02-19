package com.mowitnow;

import com.mowitnow.application.service.CreateLawnService;
import com.mowitnow.application.service.SimulationService;
import com.mowitnow.application.service.impl.MowerServiceImpl;
import com.mowitnow.infrastructure.adapters.in.SimulationFileAdapter;
import com.mowitnow.infrastructure.adapters.out.view.ConsoleMowerDisplayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

public class MowerIntegrationTest {
    SimulationFileAdapter simulationFileAdapter;

    @BeforeEach
    public void setup(){
        SimulationService simulationService = new SimulationService(new MowerServiceImpl(), new ConsoleMowerDisplayer());
        simulationFileAdapter = new SimulationFileAdapter(simulationService, new CreateLawnService());
    }
    @Test
    public void run_correct_global_test_case_1() throws URISyntaxException {
        System.out.println("Use case 1");
        simulationFileAdapter.simulate("input-correct-1.txt");
        System.out.println("=================");
    }

    @Test
    public void run_correct_global_test_case_2() throws URISyntaxException {
        System.out.println("Use case 2");
        simulationFileAdapter.simulate("input-correct-2.txt");
        System.out.println("=================");
    }

    @Test
    public void run_empty_global_test_case() throws URISyntaxException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> simulationFileAdapter.simulate("input-empty.txt"));
    }
}
