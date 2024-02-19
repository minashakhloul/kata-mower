package com.mowitnow.infrastructure.adapters.in;

import com.mowitnow.application.port.in.CreateLawnUseCase;
import com.mowitnow.application.port.in.CreateSimulationUseCase;
import com.mowitnow.application.port.in.LaunchSimulationUseCase;
import com.mowitnow.application.service.SimulationService;
import com.mowitnow.domain.lawn.Lawn;
import com.mowitnow.domain.simulation.Simulation;
import com.mowitnow.infrastructure.utils.FileUtils;

import java.net.URISyntaxException;
import java.util.List;

public class SimulationFileAdapter {

    private final CreateSimulationUseCase createSimulationUseCase;
    private final LaunchSimulationUseCase launchSimulationUseCase;
    private final CreateLawnUseCase createLawnUseCase;

    public SimulationFileAdapter(SimulationService simulationService,
                                 CreateLawnUseCase createLawnUseCase) {
        this.createSimulationUseCase = simulationService;
        this.launchSimulationUseCase = simulationService;
        this.createLawnUseCase = createLawnUseCase;
    }

    public void simulate(String filename) throws URISyntaxException {
        List<String> lines = FileUtils.readFileLinesFromResource(filename);
        if (lines == null || lines.isEmpty()) {
            throw new IllegalArgumentException("Cannot continue simulation, empty input");
        }
        Lawn lawn = createLawnUseCase.create(lines.get(0));
        Simulation simulation = createSimulationUseCase.create(lines);
        launchSimulationUseCase.launch(simulation, lawn);
    }
}
