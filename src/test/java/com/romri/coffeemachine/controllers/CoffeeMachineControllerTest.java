package com.romri.coffeemachine.controllers;

import com.romri.coffeemachine.models.CoffeeMachine;
import com.romri.coffeemachine.models.Power;
import com.romri.coffeemachine.services.CoffeeMachineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.BDDMockito.given;

class CoffeeMachineControllerTest {

    @Mock
    private CoffeeMachineService coffeeMachineService;
    @InjectMocks
    private CoffeeMachineController coffeeMachineController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMachineStatus() {
        //given
        CoffeeMachine mockedCoffeeMachine = new CoffeeMachine(1L, Power.ON, null, 0.0, 0.0, 0.0);
        given(coffeeMachineService.getCoffeeMachineStatusById(1L))
                .willReturn(mockedCoffeeMachine);
        //when
        CoffeeMachine testCoffeeMachine = coffeeMachineController.getMachineStatus(1L);
        //then
        assertThat(testCoffeeMachine.getStatus())
                .isEqualTo(mockedCoffeeMachine.getStatus());
    }

    @Test
    void addNegativeAmountOfBeans() {
        //given
        //when
        Throwable throwable = catchThrowable(()->coffeeMachineController.addBeans(1L, -10.0));
        //then
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void addTooMuchWater() {
        //given
        //when
        Throwable throwable = catchThrowable(()->coffeeMachineController.addWater(1L, 10.0));
        //then
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void successfullyAddMilk() {
        //given
        CoffeeMachine expectedMockedCoffeeMachine = new CoffeeMachine(1L, Power.ON, null, 0.0, 0.500, 0.0);
        given(coffeeMachineService.addMilk(1L,0.500))
                .willReturn(expectedMockedCoffeeMachine);
        //when
        CoffeeMachine testCoffeeMachine = coffeeMachineController.addMilk(1L, 0.500);
        //then
        assertThat(testCoffeeMachine.getAmountOfMilk())
                .isEqualTo(expectedMockedCoffeeMachine.getAmountOfMilk());
    }

    @Test
    void successfullyTurnOnOrOff() {
        //given
        CoffeeMachine expectedMockedCoffeeMachine = new CoffeeMachine(1L, Power.OFF, null, 0.0, 0.500, 0.0);
        given(coffeeMachineService.turnOnOrOff(1L))
                .willReturn(expectedMockedCoffeeMachine);
        //when
        CoffeeMachine testCoffeeMachine = coffeeMachineController.turnOnOrOff(1L);
        //then
        assertThat(testCoffeeMachine.getStatus())
                .isEqualTo(expectedMockedCoffeeMachine.getStatus());
    }

    @Test
    void makeCoffee() {
        //given
        CoffeeMachine expectedMockedCoffeeMachine = new CoffeeMachine(1L, Power.ON, null, 0.0, 0.500, 0.0);
        given(coffeeMachineService.makeCoffee(1L, "Espresso"))
                .willReturn("Here you go, your Espresso");
        //when
        String testCoffee = coffeeMachineController.makeCoffee(1L, "Espresso");
        //then
        assertThat(testCoffee)
                .isEqualTo("Here you go, your Espresso");
    }
}