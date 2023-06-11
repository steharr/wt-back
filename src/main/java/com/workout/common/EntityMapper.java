package com.workout.common;

public interface EntityMapper<M, E> {

    M entityToModel(E ent);

    E modelToEntity(M model);
}
