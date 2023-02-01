package com.workout.infrastructure.repository;

import com.workout.infrastructure.repository.entity.WorkoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<WorkoutEntity,Long> {
}
