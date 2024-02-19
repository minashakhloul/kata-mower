package com.mowitnow.domain.simulation;

import com.mowitnow.domain.mower.Mower;
import com.mowitnow.domain.mower.Pivot;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
@Builder
public class Simulation {
    private Map<Mower, List<Pivot>> mowersAndInstructions;
}
