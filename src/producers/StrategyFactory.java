package producers;

import distributors.DistributorsInputData;

import java.util.List;

public final class StrategyFactory {
    /**
     * functie care instantiaza clasa potrivita, in functie de strategia distribuitorului
     */
    public ProducerStrategy createStrategy(DistributorsInputData distributor,
                                           List<ProducersInputData> producers) {
        if (distributor.getProducerStrategy().equals("GREEN")) {
            return new GreenProducer(distributor, producers);
        }
        if (distributor.getProducerStrategy().equals("PRICE")) {
            return new PriceProducer(distributor, producers);
        }
        return new QuantityProducer(distributor, producers);
    }
}
