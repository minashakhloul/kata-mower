package com.mowitnow.domain.entities;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Lawn {
    private Coordinate upperRight;
    private Coordinate bottomLeft;
}
