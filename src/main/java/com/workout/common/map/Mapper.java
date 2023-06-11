package com.workout.common.map;

public interface Mapper<Model, Entity, Dto> {

    Model entityToModel(Entity ent);

    Entity modelToEntity(Model model);

    Dto modelToDto(Model model);
}
