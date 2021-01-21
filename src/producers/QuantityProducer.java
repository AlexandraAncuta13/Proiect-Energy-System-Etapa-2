package producers;

import distributors.DistributorsInputData;

import java.util.List;

public final class QuantityProducer implements ProducerStrategy {

    private DistributorsInputData distributorsInputData;
    private List<ProducersInputData> producersInputData;

    public QuantityProducer(DistributorsInputData distributorsInputData,
                            List<ProducersInputData> producers) {
        this.distributorsInputData = distributorsInputData;
        this.producersInputData = producers;
    }
    @Override
    public void getProducers() {
        int energy = 0;
        producersInputData.sort(new SortByQuantity());
        for (ProducersInputData p : producersInputData) {
            if (p.getDistributors().size() < p.getMaxDistributors()) {
                distributorsInputData.getProducers().add(p);
                p.getDistributors().add(distributorsInputData);
                p.addObserver(distributorsInputData);
                energy += p.getEnergyPerDistributor();
            }
            if (distributorsInputData.getEnergyNeededKW() <= energy) {
                break;
            }
        }
    }

    public DistributorsInputData getDistributorsInputData() {
        return distributorsInputData;
    }

    public void setDistributorsInputData(DistributorsInputData distributorsInputData) {
        this.distributorsInputData = distributorsInputData;
    }

    public List<ProducersInputData> getProducersInputData() {
        return producersInputData;
    }

    public void setProducersInputData(List<ProducersInputData> producersInputData) {
        this.producersInputData = producersInputData;
    }
}
