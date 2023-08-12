package com.workout;

import com.workout.security.application.AccountService;
import com.workout.security.domain.dto.AccountDetailsDTO;
import com.workout.security.domain.model.AvatarEyesType;
import com.workout.security.domain.model.AvatarHairType;
import com.workout.session.application.WorkoutService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WorkoutApplicationTests {

    @Autowired
    WorkoutService workoutService;
    @Autowired
    AccountService accountService;

    @Test
    void contextLoads() {
    }

    @Test
    @Disabled
    void testWorkoutServiceGet() {
    }

    @Test
    @Disabled
    void testWorkoutServiceSave() {
    }

    @Test
    void testAccountServiceSave() {
        accountService.save(new AccountDetailsDTO(
                "stephen",
                "harrold",
                29,
                "M",
                "steharr123",
                "steharr123@gmail.com",
                AvatarEyesType.HAPPY,
                AvatarHairType.CLASSIC_2,
                "abc"
        ));
    }


}
