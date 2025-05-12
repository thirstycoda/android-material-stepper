package com.stepstone.stepper.internal.widget;

import androidx.annotation.NonNull;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Checks if {@link StepTab} is in a correct state.
 *
 * @author Piotr Zawadzki
 */
public class StepTabStateMatcher extends TypeSafeMatcher<StepTab> {

    @NonNull
    private final TabState expectedState;

    public StepTabStateMatcher(@NonNull TabState expectedState) {
        this.expectedState = expectedState;
    }

    @Override
    protected boolean matchesSafely(StepTab item) {
        StepTab.AbstractState currentState = item.mCurrentState;
        return switch (expectedState) {
            case ACTIVE -> currentState instanceof StepTab.ActiveNumberState;
            case INACTIVE -> currentState instanceof StepTab.InactiveNumberState;
            case DONE -> currentState instanceof StepTab.DoneState;
            case WARNING -> currentState instanceof StepTab.WarningState;
        };
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(" with state: ").appendValue(expectedState);
    }

    public enum TabState {
        ACTIVE,
        INACTIVE,
        DONE,
        WARNING
    }
}
