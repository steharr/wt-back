package com.workout.session.infrastructure.rest;

import com.workout.common.exception.ApplicationException;
import com.workout.session.application.WorkoutService;
import com.workout.session.domain.dto.ExerciseAnalysisDTO;
import com.workout.session.domain.dto.ExerciseTypeDTO;
import com.workout.session.domain.dto.WorkoutAnalysisDTO;
import com.workout.session.domain.model.Workout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/workout")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    @PostMapping("/save")
    public void workout(@RequestBody Workout workout, Authentication a) {
        try {
            log.info("Saving workout...begin");
            String username = a == null ? "" : a.getPrincipal().toString();
            this.workoutService.saveWorkout(workout, username);
            log.info("Saving workout...complete");
        } catch (Exception e) {
            throw new ApplicationException(String.join(":", "Technical Error Saving Workout", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteWorkout(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.workoutService.deleteWorkout(id));
        } catch (Exception e) {
            log.error("Error deleting workout id: {}, Cause:{}", id, e.getMessage());
            throw new ApplicationException(String.join(":", "Technical Error Deleting Workout", e.getMessage()));
        }
    }

    @GetMapping("home")
    public ResponseEntity<List<Workout>> home(Authentication a) {
        try {
            String username = a == null ? "" : a.getPrincipal().toString();
            return ResponseEntity.ok(this.workoutService.getWorkouts(username));
        } catch (Exception e) {
            log.error("Error retrieving data: {}", e.getMessage());
            throw new ApplicationException(String.join(":", "Technical Error Loading Homepage", e.getMessage()));
        }
    }

    @GetMapping("analysis/workout/{id}")
    public ResponseEntity<WorkoutAnalysisDTO> workoutAnalysis(@PathVariable Long id) {
        try {
            Optional<WorkoutAnalysisDTO> analysis = workoutService.getWorkoutAnalysis(id);
            return analysis.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
        } catch (Exception e) {
            log.error("Error retrieving data: {}", e.getMessage());
            throw new ApplicationException(String.join(":", "Technical Error Loading Analysis", e.getMessage()));
        }
    }

    @GetMapping("analysis/exercise/{exercise}")
    public ResponseEntity<ExerciseAnalysisDTO> exerciseAnalysis(@PathVariable String exercise, Authentication a) {
        try {
            String username = a == null ? "" : a.getPrincipal().toString();
            ExerciseAnalysisDTO analysis = workoutService.getExerciseAnalysis(exercise, username);
            return new ResponseEntity<>(analysis, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error retrieving data: {}", e.getMessage());
            throw new ApplicationException(String.join(":", "Technical Error Loading Analysis", e.getMessage()));
        }
    }

    @GetMapping("exercises")
    public ResponseEntity<List<ExerciseTypeDTO>> exercises() {
        try {
            return new ResponseEntity<>(workoutService.getExcerisesTypes(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error retrieving data: {}", e.getMessage());
            throw new ApplicationException(String.join(":", "Technical Error Loading Exercises", e.getMessage()));
        }
    }
}
