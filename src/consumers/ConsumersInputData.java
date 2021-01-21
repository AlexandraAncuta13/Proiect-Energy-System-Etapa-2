package consumers;

import distributors.DistributorsInputData;

public final class ConsumersInputData {
    /**
     * id ul consumatorului
     */
    private int id;
    /**
     * bugetul consumatorului
     */
    private int initialBudget;
    /**
     * venitul lunar al consumatorului
     */
    private int monthlyIncome;
    /**
     * numarul de luni ramase din contractul semnat cu distribuitorul
     */
    private int remainedMonths = 0;
    /**
     * parametru care ia valoarea 0 daca nu are nicio datorie
     *                            1 daca este restant cu o factura
     *                            2 daca a dat faliment
     */
    private int debt;
    /**
     * factura lunara a consumatorului
     */
    private int bill;
    /**
     * distribuitorul la care este asignat consumatorul
     */
    private DistributorsInputData distribuitor;

    /**
     * metoda care calculeaza cheltuielile lunare ale consumatorului
     */
    public int calculateCosts() {
        return bill - monthlyIncome;
    }

    public int getBill() {
        return bill;
    }

    public void setBill(int bill) {
        this.bill = bill;
    }

    public int getRemainedMonths() {
        return remainedMonths;
    }

    public void setRemainedMonths(int remainedMonths) {
        this.remainedMonths = remainedMonths;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(int debt) {
        this.debt = debt;
    }

    public DistributorsInputData getDistribuitor() {
        return distribuitor;
    }

    public void setDistribuitor(DistributorsInputData distribuitor) {
        this.distribuitor = distribuitor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInitialBudget() {
        return initialBudget;
    }

    public void setInitialBudget(int initialBudget) {
        this.initialBudget = initialBudget;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    @Override
    public String toString() {
        return "ConsumersInputData{"
                + "id=" + id
                + ", initialBudget=" + initialBudget
                + ", monthlyIncome=" + monthlyIncome
                + '}';
    }
}
