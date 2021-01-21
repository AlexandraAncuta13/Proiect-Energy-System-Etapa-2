package consumers;

import fileio.Entity;

public final class ConsumersOutputData implements Entity {
    /**
     * id ul consumatorului
     */
    private int id;
    /**
     * variabila care precizeaza daca a dat faliment sau nu
     */
    private boolean isBankrupt;
    /**
     * bugetul final al consumatorului
     */
    private int budget;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public void setIsBankrupt(boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    @Override
    public String toString() {
        return "ConsumersOutputData{"
                + "id=" + id
                + ", isBankrupt=" + isBankrupt
                + ", budget=" + budget
                + '}';
    }
}
