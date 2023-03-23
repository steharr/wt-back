package com.workout.session.infrastructure.rest;

import com.workout.session.application.WorkoutService;
import com.workout.session.domain.dto.WorkoutAnalysisDTO;
import com.workout.session.domain.model.Workout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/workout")
@CrossOrigin(origins = "http://localhost:4200")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;


    @GetMapping()
    public Workout workout() {
        return this.workoutService.getWorkout();
    }

    @PostMapping()
    public void workout(@RequestBody Workout workout) {
        log.info("Saving workout...begin");
        this.workoutService.saveWorkout(workout);
        log.info("Saving workout...complete");
    }

    @GetMapping("home")
    public ResponseEntity<List<Workout>> home() {
        try {
            return ResponseEntity.ok(this.workoutService.getWorkouts());
        } catch (Exception e) {
            log.error("Error retrieving data: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("analysis/{id}")
    public ResponseEntity<WorkoutAnalysisDTO> analysis(@PathVariable Long id) {
        Optional<WorkoutAnalysisDTO> analysis = workoutService.getWorkoutAnalysis(id);
        return analysis.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
