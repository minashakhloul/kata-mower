package com.mowitnow.application.service;

import com.mowitnow.domain.lawn.Lawn;
import com.mowitnow.domain.mower.Mower;
import com.mowitnow.domain.mower.Pivot;

import java.util.List;

public interface MowerService {
    Mower create(String input);
    Mower applyInstructions(Lawn lawn, Mower mower, List<Pivot> instructions);
}
