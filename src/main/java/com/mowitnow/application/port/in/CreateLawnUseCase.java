package com.mowitnow.application.port.in;

import com.mowitnow.domain.lawn.Lawn;

public interface CreateLawnUseCase {

    Lawn create(String input);
}
