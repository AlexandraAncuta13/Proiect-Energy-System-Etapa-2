package fileio;

import java.util.List;

/**
 * Informatii despre input
 */
public final class Input {

    private final int numberOfTurns;

    private final InitialData initialData;

    private final List<MonthlyUpdates> monthlyUpdates;


    public Input() {
        this.numberOfTurns = 0;
        this.initialData = null;
        this.monthlyUpdates = null;
    }

    public Input(final int numberOfTurns, final InitialData initialData,
                 final List<MonthlyUpdates> monthlyUpdates) {
        this.numberOfTurns = numberOfTurns;
        this.initialData = initialData;
        this.monthlyUpdates = monthlyUpdates;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public List<MonthlyUpdates> getMonthlyUpdates() {
        return monthlyUpdates;
    }
}

