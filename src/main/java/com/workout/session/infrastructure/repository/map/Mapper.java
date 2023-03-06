package com.workout.session.infrastructure.repository.map;

public interface Mapper<M, E> {

    M entityToModel(E ent);

    E modelToEntity(M model);
}
