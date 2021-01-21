package producers;

import java.util.Comparator;

public final class SortByQuantity implements Comparator<ProducersInputData> {
    @Override
    public int compare(ProducersInputData p1, ProducersInputData p2) {
        if (p1.getEnergyPerDistributor() > p2.getEnergyPerDistributor()) {
            return -1;
        }
        if (p1.getEnergyPerDistributor() < p2.getEnergyPerDistributor()) {
            return 1;
        }
        if (p1.getPriceKW() > p2.getPriceKW()) {
            return 1;
        }
        if (p1.getPriceKW() < p2.getPriceKW()) {
            return -1;
        }
        if (p1.findEnergyType() == 1 && p2.findEnergyType() == 0) {
            return 1;
        }
        if (p1.findEnergyType() == 0 && p2.findEnergyType() == 1) {
            return -1;
        }

        return p2.getId() - p1.getId();
    }
}
