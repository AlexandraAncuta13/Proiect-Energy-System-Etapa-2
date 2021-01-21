package fileio;

import consumers.ConsumersInputData;
import distributors.DistributorChanges;
import producers.ProducerChanges;

import java.util.List;

/**
 * Informatii despre schimbarile lunare
 */
public final class MonthlyUpdates {

    private List<ConsumersInputData> newConsumers;

    private List<DistributorChanges> distributorChanges;

    private List<ProducerChanges> producerChanges;


    public List<ConsumersInputData> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(List<ConsumersInputData> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public List<DistributorChanges> getDistributorChanges() {
        return distributorChanges;
    }

    public void setDistributorChanges(List<DistributorChanges> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }

    public List<ProducerChanges> getProducerChanges() {
        return producerChanges;
    }

    public void setProducerChanges(List<ProducerChanges> producerChanges) {
        this.producerChanges = producerChanges;
    }

    @Override
    public String toString() {
        return "MonthlyUpdates{"
                + "newConsumers=" + newConsumers
                + ", distributorChanges=" + distributorChanges
                + ", producerChanges=" + producerChanges
                + '}';
    }
}
