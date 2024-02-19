package com.mowitnow.application.port.in;

import com.mowitnow.domain.mower.Mower;
import com.mowitnow.domain.simulation.Simulation;

import java.util.List;
import java.util.Map;

public interface CreateSimulationUseCase {
    Simulation create(List<String> data);
}
