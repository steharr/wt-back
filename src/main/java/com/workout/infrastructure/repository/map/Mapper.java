package com.workout.infrastructure.repository.map;

public interface Mapper<M, E> {

    M entityToModel(E ent);

    E modelToEntity(M model);
}
