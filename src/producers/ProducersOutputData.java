package producers;

import java.util.List;

public final class ProducersOutputData {

    /**
     * id-ul producatorului
     */
    private int id;
    /**
     * numarul maxim de distribuitori pe care producatorul ii poate alimenta
     */
    private int maxDistributors;
    /**
     * pretul unui KW de energie
     */
    private double priceKW;
    /**
     * tipul de energie oferrita
     */
    private String energyType;
    /**
     * catitatea de energie pe care producatorul o ofera fiecarui distribuitor
     */
    private int energyPerDistributor;
    /**
     * statistica din fiecare luna cu privire la distribuitori
     */
    private List<MonthlyStats> monthlyStats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public double getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(double priceKW) {
        this.priceKW = priceKW;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public List<MonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(List<MonthlyStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }
}
