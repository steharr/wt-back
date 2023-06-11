package com.workout.common;

public interface Mapper<Model, Entity, Dto> {

    Model entityToModel(Entity ent);

    Entity modelToEntity(Model model);

    Dto modelToDto(Model model);
}
