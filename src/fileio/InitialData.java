package fileio;

import consumers.ConsumersInputData;
import distributors.DistributorsInputData;
import producers.ProducersInputData;

import java.util.List;

/**
 * Informatii despre datele initiale
 */
public final class InitialData {
    private List<ConsumersInputData> consumers;

    private List<DistributorsInputData> distributors;

    private List<ProducersInputData> producers;

    public List<ConsumersInputData> getConsumers() {
        return consumers;
    }

    public void setConsumers(List<ConsumersInputData> consumers) {
        this.consumers = consumers;
    }

    public List<DistributorsInputData> getDistributors() {
        return distributors;
    }

    public void setDistributors(List<DistributorsInputData> distributors) {
        this.distributors = distributors;
    }

    public List<ProducersInputData> getProducers() {
        return producers;
    }

    public void setProducers(List<ProducersInputData> producers) {
        this.producers = producers;
    }

    @Override
    public String toString() {
        return "InitialData{"
                + "consumers=" + consumers
                + ", distributors=" + distributors
                + '}';
    }
}
