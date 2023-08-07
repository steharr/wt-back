package com.workout.session.domain.model;

import java.math.BigDecimal;

public class ProgressionData {

    private final ProgressEnum progressEnum;
    private final BigDecimal lastValue;

    public ProgressionData(ProgressEnum progressEnum, BigDecimal lastValue) {
        this.progressEnum = progressEnum;
        this.lastValue = lastValue;
    }

    public ProgressEnum progressEnum() {
        return progressEnum;
    }

    public BigDecimal lastValue() {
        return lastValue;
    }
}
