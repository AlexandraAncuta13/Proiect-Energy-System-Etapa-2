package producers;

import java.util.Comparator;

public final class SortByPrice implements Comparator<ProducersInputData> {

    @Override
    public int compare(ProducersInputData p1, ProducersInputData p2) {
        if (p1.getPriceKW() > p2.getPriceKW()) {
            return 1;
        }
        if (p1.getPriceKW() < p2.getPriceKW()) {
            return -1;
        }
        if (p1.getEnergyPerDistributor() > p2.getEnergyPerDistributor()) {
            return -1;
        }
        if (p1.getEnergyPerDistributor() < p2.getEnergyPerDistributor()) {
            return 1;
        }
        return p1.getId() - p2.getId();
    }
}
