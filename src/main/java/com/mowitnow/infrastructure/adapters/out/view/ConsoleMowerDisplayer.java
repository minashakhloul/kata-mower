package com.mowitnow.infrastructure.adapters.out.view;

import com.mowitnow.application.port.out.view.MowerDisplayer;
import com.mowitnow.domain.mower.Mower;

public class ConsoleMowerDisplayer implements MowerDisplayer {
    @Override
    public String display(Mower mower) {
        return new StringBuilder()
                .append(mower.getCoordinate().getX())
                .append(" ")
                .append(mower.getCoordinate().getY())
                .append(" ")
                .append(mower.getOrientation().name())
                .toString();
    }
}
