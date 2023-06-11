package com.workout.common.map;

public interface EntityMapper<M, E> {

    M entityToModel(E ent);

    E modelToEntity(M model);
}
