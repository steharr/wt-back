package com.workout.infrastructure.rest;

import com.workout.application.WorkoutService;
import com.workout.domain.model.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        this.workoutService.saveWorkout(workout);
    }
}
