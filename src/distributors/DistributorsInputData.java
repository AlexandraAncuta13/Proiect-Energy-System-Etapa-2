package distributors;

import consumers.ConsumersInputData;
import producers.ProducersInputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public final class DistributorsInputData implements Observer {
    /**
     * id ul distribuitorului
     */
    private int id;
    /**
     * lungimea contractului pe care il semneaza consumatorii cu distribuitorii
     */
    private int contractLength;
    /**
     * bugetul distribuitorului
     */
    private int initialBudget;
    /**
     * costurile de infrastructura ale distribuitorului care contribuie la pretul facturii
     */
    private int initialInfrastructureCost;
    /**
     * energia de care are nevoie distribuitorul
     */
    private int energyNeededKW;
    /**
     * strategia dupa care un distribuitor isi alege producatorii
     */
    private String producerStrategy;
    /**
     * consumatorii care au semnat contract cu distribuitorul
     */
    private List<ConsumersInputData> consumers = new ArrayList<>();
    /**
     * paramatru care ia valoarea 0 daca distribuitorul nu a dat faliment, 1 altfel
     */
    private int debt;
    /**
     * producatorii de la care distribuitorul primeste energie
     */
    private List<ProducersInputData> producers = new ArrayList<>();
    /**
     * variabila care ia valoarea: 1-daca au aparut schimbari la producatorii la care este asignat
     *                             0-altfel
     */
    private int changeEnergy;
    /**
     * costul contractului pe care consumatorii il vor plati
     */
    private int contractCost;
    /**
     * calculeaza costul de productie in functie de parametrii producatorilor
     */
    public int calculateProductionCost() {
        final int constant = 10;
        double cost = 0;
        for (ProducersInputData p : producers) {
            cost += p.getEnergyPerDistributor() * p.getPriceKW();
        }
        return (int) Math.round(Math.floor(cost / constant));
    }

    /**
     * metoda care calculeaza cheltuielile lunare ale distribuitorului
     */
    public int calculateCosts() {
        int nrConsumers = consumers.size();
        int billCosts = 0;
        for (int i = 0; i < consumers.size(); i++) {
            billCosts += consumers.get(i).getBill();
        }
        return initialInfrastructureCost +  (calculateProductionCost() * nrConsumers) - billCosts;
    }

    /**
     * metoda care calculeaza profitul lunar al distribuitorului
     */
    public int calculateProfit() {
         final double constant = 0.2;
        return (int) Math.round(Math.floor(constant * calculateProductionCost()));
    }

    /**
     * metoda care clculeaza factura pe care o cere distribuitorul
     */
    public int calculateBill() {
        int nrConsumers = consumers.size();
        if (nrConsumers > 0) {
            return (int) Math.round(Math.floor(initialInfrastructureCost / nrConsumers)
                    + calculateProfit() + calculateProductionCost());
        }
        return initialInfrastructureCost + calculateProductionCost() + calculateProfit();
    }

    public List<ConsumersInputData> getConsumers() {
        return consumers;
    }

    public void setConsumers(List<ConsumersInputData> consumers) {
        this.consumers = consumers;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContractLength() {
        return contractLength;
    }

    public void setContractLength(int contractLength) {
        this.contractLength = contractLength;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    public void setInitialInfrastructureCost(int initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(String producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public List<ProducersInputData> getProducers() {
        return producers;
    }

    public void setProducers(List<ProducersInputData> producers) {
        this.producers = producers;
    }

    public int getChangeEnergy() {
        return changeEnergy;
    }

    public void setChangeEnergy(int changeEnergy) {
        this.changeEnergy = changeEnergy;
    }

    public int getContractCost() {
        return contractCost;
    }

    public void setContractCost(int contractCost) {
        this.contractCost = contractCost;
    }

    @Override
    public String toString() {
        return "DistributorsInputData{"
                + "id=" + id
                + ", contractLength=" + contractLength
                + ", initialBudget=" + initialBudget
                + ", initialInfrastructureCost=" + initialInfrastructureCost
                + ", energyNeededKW=" + energyNeededKW
                + ", producerStrategy=" + producerStrategy
                + '}';
    }

    @Override
    public void update(Observable o, Object arg) {
        this.changeEnergy = 1;
    }
}
