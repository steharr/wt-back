package com.workout.session.infrastructure.rest;

import com.workout.session.application.WorkoutService;
import com.workout.session.domain.model.Workout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/workout")
@CrossOrigin(origins = "http://localhost:4200")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;


    @GetMapping()
    public Workout getWorkout() {
        return this.workoutService.getWorkout();
    }

    @PostMapping()
    public void saveWorkout(@RequestBody Workout workout) {
        log.info("Saving workout...begin");
        this.workoutService.saveWorkout(workout);
        log.info("Saving workout...complete");
    }
}
