package distributors;

public final class DistributorChanges {
    /**
     * id ul distribuitorului care are schimbari de costuri in luna curenta
     */
    private int id;
    /**
     * costul infrastructurii actualizat
     */
    private int infrastructureCost;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    public void setInfrastructureCost(int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    @Override
    public String toString() {
        return "NewCostChangesInputData{"
                + "id=" + id
                + ", infrastructureCost=" + infrastructureCost
                + '}';
    }
}
