package com.mowitnow.domain.mower;

import com.mowitnow.domain.coordinate.Coordinate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Mower {
    private Coordinate coordinate;
    private Orientation orientation;
}
