package distributors;

import fileio.Entity;

import java.util.ArrayList;
import java.util.List;

public final class DistributorsOutputData implements Entity {
    /**
     * id ul distribuitorului
     */
    private int id;
    /**
     * energia de care are nevoie distribuitorul de la producatori
     */
    private int energyNeededKW;
    /**
     * valoarea facturii pe care trebuie sa o plateasca conusmatorii asignati
     */
    private int contractCost;
    /**
     * bugetul final al distribuitorului
     */
    private int budget;
    /**
     * strategia dupa care distribuitorul isi alege producatorii
     */
    private String producerStrategy;
    /**
     * variabila care determina daca un distribuitor a dat faliment sau nu
     */
    private boolean isBankrupt;
    /**
     * lista contractelor pe care le-a incheiat distribuitorul
     */
    private List<Contracts> contracts = new ArrayList<>();

    public List<Contracts> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contracts> contracts) {
        this.contracts = contracts;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public void setIsBankrupt(boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public int getContractCost() {
        return contractCost;
    }

    public void setContractCost(int contractCost) {
        this.contractCost = contractCost;
    }

    public String getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(String producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    @Override
    public String toString() {
        return "DistributorsOutputData{"
                + "id=" + id
                + ", budget=" + budget
                + ", isBankrupt=" + isBankrupt
                + ", contracts=" + contracts
                + '}';
    }
}
