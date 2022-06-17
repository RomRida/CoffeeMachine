package com.romri.coffeemachine.services;

import com.romri.coffeemachine.models.CoffeeMachine;
import com.romri.coffeemachine.models.CoffeeType;
import com.romri.coffeemachine.models.Power;
import com.romri.coffeemachine.repositories.CoffeeMachineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class CoffeeMachineServiceUnitTest {

    @Mock
    private CoffeeMachineRepository coffeeMachineRepository;
    @Mock
    private CoffeeTypeService coffeeTypeService;
    @InjectMocks
    private CoffeeMachineService coffeeMachineService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void successfullyGetCoffeeMachineStatusById() {
        //given
        CoffeeMachine mockedCoffeeMachine = new CoffeeMachine(1L, Power.ON, null, 0.0, 0.0, 0.0);
        given(coffeeMachineRepository.findById(1L))
                .willReturn(Optional.of(mockedCoffeeMachine));
        //when
        CoffeeMachine testCoffeeMachine = coffeeMachineService.getCoffeeMachineStatusById(1L);
        //then
        assertThat(testCoffeeMachine.getStatus())
                .isEqualTo(mockedCoffeeMachine.getStatus());
        assertThat(testCoffeeMachine.getId())
                .isEqualTo(mockedCoffeeMachine.getId());
        assertThat(testCoffeeMachine.getAmountOfCoffeeBeans())
                .isEqualTo(mockedCoffeeMachine.getAmountOfCoffeeBeans());
    }

    @Test
    void failedToGetCoffeeMachineStatusById() {
        //given
        given(coffeeMachineRepository.findById(any()))
                .willThrow(new NoSuchElementException());
        //when
        Throwable throwable = catchThrowable(() -> coffeeMachineService.getCoffeeMachineStatusById(12L));
        //then
        assertThat(throwable)
                .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void successfullyAddBeans() {
        //given
        CoffeeMachine mockedCoffeeMachine = new CoffeeMachine(1L, Power.ON, null, 0.0, 0.0, 0.0);
        CoffeeMachine expectedCoffeeMachine = new CoffeeMachine(1L, Power.ON, null, 0.0, 0.0, 2.3);
        given(coffeeMachineRepository.findById(1L))
                .willReturn(Optional.of(mockedCoffeeMachine));
        given(coffeeMachineRepository.save(any()))
                .willReturn(expectedCoffeeMachine);
        //when
        CoffeeMachine testCoffeeMachine = coffeeMachineService.addBeans(1L, 2.3);
        //then
        assertThat(testCoffeeMachine.getAmountOfCoffeeBeans())
                .isEqualTo(expectedCoffeeMachine.getAmountOfCoffeeBeans());
    }

    @Test
    void failedToAddTooManyBeans() {
        //given
        CoffeeMachine mockedCoffeeMachine = new CoffeeMachine(1L, Power.ON, null, 0.0, 0.0, 0.0);
        given(coffeeMachineRepository.findById(1L))
                .willReturn(Optional.of(mockedCoffeeMachine));
        //when
        Throwable throwable = catchThrowable(() -> coffeeMachineService.addBeans(1L, 10.1));
        //then
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable.getMessage())
                .isEqualTo("Amount of beans is too much, try putting in less then 5kg total");
    }

    @Test
    void successfullyAddWater() {
        //given
        CoffeeMachine mockedCoffeeMachine = new CoffeeMachine(1L, Power.ON, null, 0.0, 0.0, 0.0);
        CoffeeMachine expectedCoffeeMachine = new CoffeeMachine(1L, Power.ON, null, 2.3, 0.0, 0.0);
        given(coffeeMachineRepository.findById(1L))
                .willReturn(Optional.of(mockedCoffeeMachine));
        given(coffeeMachineRepository.save(any()))
                .willReturn(expectedCoffeeMachine);
        //when
        CoffeeMachine testCoffeeMachine = coffeeMachineService.addWater(1L, 2.3);
        //then
        assertThat(testCoffeeMachine.getAmountOfCoffeeBeans())
                .isEqualTo(expectedCoffeeMachine.getAmountOfCoffeeBeans());
    }

    @Test
    void failedToAddWater() {
        //given
        CoffeeMachine mockedCoffeeMachine = new CoffeeMachine(1L, Power.ON, null, 0.0, 0.0, 0.0);
        given(coffeeMachineRepository.findById(1L))
                .willReturn(Optional.of(mockedCoffeeMachine));
        //when
        Throwable throwable = catchThrowable(() -> coffeeMachineService.addWater(1L, 10.1));
        //then
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable.getMessage())
                .isEqualTo("Amount of water is too much, try putting in less then 5l total");
    }

    @Test
    void turnCoffeeMachineOnOrOff() {
        //given
        CoffeeMachine mockedCoffeeMachine = new CoffeeMachine(1L, Power.OFF, null, 0.0, 0.0, 0.0);
        CoffeeMachine expectedCoffeeMachine = new CoffeeMachine(1L, Power.ON, null, 0.0, 0.0, 0.0);
        given(coffeeMachineRepository.findById(1L))
                .willReturn(Optional.of(mockedCoffeeMachine));
        given(coffeeMachineRepository.save(any()))
                .willReturn(expectedCoffeeMachine);
        //when
        CoffeeMachine testCoffeeMachine = coffeeMachineService.turnOnOrOff(1L);
        //then
        assertThat(testCoffeeMachine.getStatus())
                .isEqualTo(expectedCoffeeMachine.getStatus());
    }

    @Test
    void successfullyAddMilk() {
        ///given
        CoffeeMachine mockedCoffeeMachine = new CoffeeMachine(1L, Power.ON, null, 0.0, 0.0, 0.0);
        CoffeeMachine expectedCoffeeMachine = new CoffeeMachine(1L, Power.ON, null, 0.0, 0.1, 0.0);
        given(coffeeMachineRepository.findById(1L))
                .willReturn(Optional.of(mockedCoffeeMachine));
        given(coffeeMachineRepository.save(any()))
                .willReturn(expectedCoffeeMachine);
        //when
        CoffeeMachine testCoffeeMachine = coffeeMachineService.addMilk(1L, 0.1);
        //then
        assertThat(testCoffeeMachine.getAmountOfCoffeeBeans())
                .isEqualTo(expectedCoffeeMachine.getAmountOfCoffeeBeans());
    }

    @Test
    void failedToAddMilk() {
        //given
        CoffeeMachine mockedCoffeeMachine = new CoffeeMachine(1L, Power.ON, null, 0.0, 0.0, 0.0);
        given(coffeeMachineRepository.findById(1L))
                .willReturn(Optional.of(mockedCoffeeMachine));
        //when
        Throwable throwable = catchThrowable(() -> coffeeMachineService.addMilk(1L, 10.1));
        //then
        assertThat(throwable)
                .isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable.getMessage())
                .isEqualTo("Amount of milk is too much, try putting in less then 3l total");
    }

    @Test
    void successfullyMakeCoffee() {
        ///given
        CoffeeMachine mockedCoffeeMachine = new CoffeeMachine(1L, Power.ON, null, 0.200, 0.200, 0.200);
        CoffeeType type = new CoffeeType(1L, "Espresso", 0.030, 0.0, 0.018, null);
        given(coffeeMachineRepository.findById(1L))
                .willReturn(Optional.of(mockedCoffeeMachine));
        given(coffeeTypeService.findByName("Espresso"))
                .willReturn(type);
        //when
        String testMakingCoffee = coffeeMachineService.makeCoffee(1L, "Espresso");
        //then
        assertThat(testMakingCoffee)
                .isEqualTo("Here you go, your Espresso");
    }

    @Test
    void failedToMakeCoffee() {
        ///given
        CoffeeMachine mockedCoffeeMachine = new CoffeeMachine(1L, Power.ON, null, 0.0, 0.0, 0.0);
        CoffeeType type = new CoffeeType(1L, "Cappuccino", 0.030, 0.060, 0.018, null);
        given(coffeeMachineRepository.findById(1L))
                .willReturn(Optional.of(mockedCoffeeMachine));
        given(coffeeTypeService.findByName("Cappuccino"))
                .willReturn(type);
        //when
        Throwable throwable = catchThrowable(() -> coffeeMachineService.makeCoffee(1L, "Cappuccino"));
        //then
        assertThat(throwable.getMessage())
                .isEqualTo("Not enough beans, water, milk, make sure there is enough for desired drink");
    }
}