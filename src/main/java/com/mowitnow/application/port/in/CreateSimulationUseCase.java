package com.mowitnow.application.port.in;

import com.mowitnow.domain.simulation.Simulation;

import java.util.List;

public interface CreateSimulationUseCase {
    Simulation create(List<String> data);
}
