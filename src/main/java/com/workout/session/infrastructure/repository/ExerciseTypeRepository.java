package com.workout.session.infrastructure.repository;

import com.workout.session.infrastructure.repository.entity.ExceriseTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseTypeRepository extends JpaRepository<ExceriseTypeEntity, Long> {
}
