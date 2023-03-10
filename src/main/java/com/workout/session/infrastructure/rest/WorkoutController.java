package com.workout.session.infrastructure.rest;

import com.workout.session.application.WorkoutService;
import com.workout.session.domain.model.Workout;
import com.workout.session.domain.model.WorkoutAnalysis;
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
        return ResponseEntity.ok(this.workoutService.getWorkouts());
    }

    @GetMapping("analysis/{id}")
    public ResponseEntity<WorkoutAnalysis> analysis(@PathVariable Long id) {
        Optional<WorkoutAnalysis> analysis = workoutService.getWorkoutAnalysis(id);
        return analysis.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
