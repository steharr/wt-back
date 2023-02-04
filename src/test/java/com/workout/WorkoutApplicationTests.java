package com.workout;

import com.workout.application.WorkoutService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WorkoutApplicationTests {


	@Autowired
	WorkoutService workoutService;

	@Test
	void contextLoads() {
	}

	@Test
	void testWOrkoutService(){
		var tests = workoutService.getWorkout();
	}


}
