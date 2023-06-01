package com.workout.session.infrastructure.rest;

import com.workout.security.domain.model.Account;
import com.workout.security.domain.model.AccountService;
import com.workout.session.application.WorkoutService;
import com.workout.session.domain.dto.WorkoutAnalysisDTO;
import com.workout.session.domain.model.Workout;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private AccountService accountService;

    @PostMapping()
    public void workout(@RequestBody Workout workout, Authentication a) {
        log.info("Saving workout...begin");
        String username = ((Account) a.getCredentials()).getUsername();
        this.workoutService.saveWorkout(workout, username);
        log.info("Saving workout...complete");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteWorkout(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(this.workoutService.deleteWorkout(id));
        } catch (Exception e) {
            log.error("Error deleting workout id: {}, Cause:{}", id, e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("home")
    public ResponseEntity<List<Workout>> home(Authentication a) {
        try {
            String username = a == null ? "" : ((Account) a.getCredentials()).getUsername();
            return ResponseEntity.ok(this.workoutService.getWorkouts(username));
        } catch (Exception e) {
            log.error("Error retrieving data: {}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("analysis/{id}")
    public ResponseEntity<WorkoutAnalysisDTO> analysis(@PathVariable Long id) {
        Optional<WorkoutAnalysisDTO> analysis = workoutService.getWorkoutAnalysis(id);
        return analysis.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
