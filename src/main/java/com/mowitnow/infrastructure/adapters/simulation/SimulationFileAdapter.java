package com.mowitnow.infrastructure.adapters.simulation;

import com.mowitnow.application.simulation.usercase.SimulationPort;
import com.mowitnow.application.simulation.usercase.SimulationService;
import com.mowitnow.infrastructure.utils.FileUtils;

import java.net.URISyntaxException;
import java.util.List;

public class SimulationFileAdapter {

    public void simulate(String filename) throws URISyntaxException {
        List<String> lines = FileUtils.readFileLinesFromResource(filename);
        SimulationPort simulationPort = new SimulationService();
        simulationPort.createAndLaunch(lines);
    }
}
