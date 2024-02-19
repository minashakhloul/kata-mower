package com.mowitnow.domain.lawn;

import com.mowitnow.domain.coordinate.Coordinate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Lawn {
    private Coordinate upperRight;
    private Coordinate bottomLeft;
}
