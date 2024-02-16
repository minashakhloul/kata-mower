package com.mowitnow.domain.entities;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Simulation {
    private Mower mower;
    private String instructions;

}
