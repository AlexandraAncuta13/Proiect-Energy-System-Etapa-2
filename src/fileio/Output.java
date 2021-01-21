package fileio;


import consumers.ConsumersOutputData;
import distributors.DistributorsOutputData;
import producers.ProducersOutputData;

import java.util.ArrayList;
import java.util.List;

/**
 * Informatii despre output
 */
public final class Output {

    private final List<ConsumersOutputData> consumers = new ArrayList<>();

    private final List<DistributorsOutputData> distributors = new ArrayList<>();

    private final List<ProducersOutputData> energyProducers = new ArrayList<>();

    public List<ProducersOutputData> getEnergyProducers() {
        return energyProducers;
    }

    public List<ConsumersOutputData> getConsumers() {
        return consumers;
    }

    public List<DistributorsOutputData> getDistributors() {
        return distributors;
    }

    @Override
    public String toString() {
        return "Output{"
                + "consumers=" + consumers
                + ", distributors=" + distributors
                + ", producers=" + energyProducers
                + '}';
    }
}
