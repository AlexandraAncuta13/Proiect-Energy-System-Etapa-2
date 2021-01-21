package producers;

import distributors.DistributorsInputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public final class ProducersInputData extends Observable {

    /**
     * id-ul distribuitorului
     */
    private int id;
    /**
     * tipul de energie pe care il ofera un distribuitor
     */
    private String energyType;
    /**
     * numarul maxim de distribuitori pe care producatorul ii poate alimenta
     */
    private int maxDistributors;
    /**
     * pretul unui KW de energie oferita
     */
    private double priceKW;
    /**
     * cantitatea de energie pe care o ofera fiecarui distribuitor asignat
     */
    private int energyPerDistributor;
    /**
     * lista cu distribuitorii asignati
     */
    private List<DistributorsInputData> distributors = new ArrayList<>();

    /**
     * statistica a fiecarei luni privind distribuitorii asignati
     */
    private List<MonthlyStats> monthlyStats = new ArrayList<>();

    /**
     * metoda care notifica distribuitorii asignati si efectueaza modificarile
     */
    public void setEnergy(final int energy) {
        setChanged();
        this.energyPerDistributor = energy;
        notifyObservers();
    }

    /**
     * metoda care decide dcaca tipul de energie este renewable sau nu
     */
    public int findEnergyType() {
        if (energyType.equals("WIND") || energyType.equals("SOLAR") || energyType.equals("HYDRO")) {
            return 1;
        }
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnergyType() {
        return energyType;
    }

    public void setEnergyType(String energyType) {
        this.energyType = energyType;
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

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public List<DistributorsInputData> getDistributors() {
        return distributors;
    }

    public void setDistributors(List<DistributorsInputData> distributors) {
        this.distributors = distributors;
    }

    public List<MonthlyStats> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(List<MonthlyStats> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

    @Override
    public String toString() {
        return "ProducersInputData{"
                + "id=" + id
                + ", energyType='" + energyType + '\''
                + ", maxDistributors=" + maxDistributors
                + ", priceKW=" + priceKW
                + ", energyPerDistributor=" + energyPerDistributor
                + ", distributors=" + distributors
                + '}';
    }
}
