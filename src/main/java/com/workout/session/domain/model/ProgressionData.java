package com.workout.session.domain.model;

import java.math.BigDecimal;

public class ProgressionData {

    private final ProgressEnum progressEnum;
    private final BigDecimal lastValue;

    private final Long sets;
    private final Long reps;

    public ProgressionData(ProgressEnum progressEnum, BigDecimal lastValue, Long sets, Long reps) {
        this.progressEnum = progressEnum;
        this.lastValue = lastValue;
        this.sets = sets;
        this.reps = reps;
    }

    public ProgressEnum progressEnum() {
        return progressEnum;
    }

    public BigDecimal lastValue() {
        return lastValue;
    }

    public Long sets() {
        return sets;
    }

    public Long reps() {
        return reps;
    }
}
