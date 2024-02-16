package com.mowitnow.application.simulation.usercase;

import com.mowitnow.application.simulation.validator.DataValidator;

import java.util.List;

public class SimulationService implements SimulationPort {

    @Override
    public void createAndLaunch(List<String> data) {
        boolean valid = DataValidator.isValid(data);
        if (valid) {

        } else {
            System.err.println("Cannot create and launch simulation, please check input data");
        }
    }
}
