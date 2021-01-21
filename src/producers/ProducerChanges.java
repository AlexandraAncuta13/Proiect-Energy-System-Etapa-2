package producers;

public final class ProducerChanges {

    /**
     * id-ul producatorului la care au aparut schimbari
     */
    private int id;
    /**
     * energia pe care o ofera producatorul fiecarui distribuitor asignat
     */
    private int energyPerDistributor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    @Override
    public String toString() {
        return "ProducerChanges{"
                + "id=" + id
                + ", energyPerDistributor=" + energyPerDistributor
                + '}';
    }
}
