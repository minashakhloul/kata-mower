package com.mowitnow.application.service;

import com.mowitnow.application.port.in.CreateSimulationUseCase;
import com.mowitnow.application.port.in.LaunchSimulationUseCase;
import com.mowitnow.application.service.impl.MowerServiceImpl;
import com.mowitnow.application.validator.DataValidator;
import com.mowitnow.domain.lawn.Lawn;
import com.mowitnow.domain.mower.Mower;
import com.mowitnow.domain.mower.Pivot;
import com.mowitnow.domain.simulation.Simulation;
import com.mowitnow.infrastructure.adapters.out.view.ConsoleMowerDisplayer;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class SimulationService implements CreateSimulationUseCase, LaunchSimulationUseCase {
    private final MowerService mowerService;
    private final ConsoleMowerDisplayer consoleMowerDisplayer;

    public SimulationService(MowerServiceImpl mowerServiceImpl,
                             ConsoleMowerDisplayer consoleMowerDisplayer) {
        this.mowerService = mowerServiceImpl;
        this.consoleMowerDisplayer = consoleMowerDisplayer;
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
        String result = simulation.getMowersAndInstructions()
                .entrySet()
                .stream()
                .map(entry -> consoleMowerDisplayer.display(mowerService.applyInstructions(lawn, entry.getKey(), entry.getValue())))
                .collect(joining(" "));
        System.out.println(result);
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
