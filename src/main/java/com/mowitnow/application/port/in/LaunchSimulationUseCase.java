package com.mowitnow.application.port.in;

import com.mowitnow.domain.lawn.Lawn;
import com.mowitnow.domain.simulation.Simulation;

public interface LaunchSimulationUseCase {
    void launch(Simulation simulation, Lawn lawn);
}
