package com.mowitnow.application.service;

import com.mowitnow.application.port.in.CreateSimulationUseCase;
import com.mowitnow.application.port.in.LaunchSimulationUseCase;
import com.mowitnow.application.port.out.view.MowerDisplayer;
import com.mowitnow.application.validator.DataValidator;
import com.mowitnow.domain.lawn.Lawn;
import com.mowitnow.domain.mower.Mower;
import com.mowitnow.domain.mower.Pivot;
import com.mowitnow.domain.simulation.Simulation;

import java.util.*;
import java.util.stream.Collectors;

public class SimulationService implements CreateSimulationUseCase, LaunchSimulationUseCase {
    private final MowerService mowerService;
    private final MowerDisplayer mowerDisplayer;

    public SimulationService(MowerService mowerServiceImpl,
                             MowerDisplayer consoleMowerDisplayer) {
        this.mowerService = mowerServiceImpl;
        this.mowerDisplayer = consoleMowerDisplayer;
    }

    @Override
    public Simulation create(List<String> data) {
        boolean valid = DataValidator.isValid(data);
        if (valid) {
            List<String> subList = data.subList(1, data.size());
            return generateSimulation(subList);
        } else {
            throw new IllegalArgumentException("Cannot create simulation, please check input data");
        }
    }

    @Override
    public void launch(Simulation simulation, Lawn lawn) {
        simulation.getMowersAndInstructions()
                .forEach((key, value) ->
                        mowerDisplayer.display(mowerService.applyInstructions(lawn, key, value)));
    }

    private Simulation generateSimulation(List<String> subList) {
        Map<Mower, List<Pivot>> mowerInstructionMap = new LinkedHashMap<>();
        Mower mower = null;
        for (int i = 0; i < subList.size(); i++) {
            if (i % 2 == 0) {
                mower = mowerService.create(subList.get(i));
                mowerInstructionMap.put(mower, new ArrayList<>());
            } else {
                mowerInstructionMap.get(mower).addAll(createInstruction(subList.get(i)));
            }
        }
        return Simulation.builder().mowersAndInstructions(mowerInstructionMap).build();
    }

    private Collection<Pivot> createInstruction(String instructionLine) {
        return Arrays.stream(instructionLine.split(""))
                .map(Pivot::getFromChar)
                .collect(Collectors.toList());
    }
}
