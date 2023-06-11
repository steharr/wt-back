package com.workout.session.infrastructure.repository;

import com.workout.session.infrastructure.repository.entity.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<WorkoutEntity, Long> {

    List<WorkoutEntity> findWorkoutEntitiesByUserUsername(String username);
}
