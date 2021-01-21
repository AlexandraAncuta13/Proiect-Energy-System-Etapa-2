package distributors;

public final class Contracts {
    /**
     * id ul consumatorului asignat la un anumit distribuitor
     */
    private final int consumerId;
    /**
     * factura pe care o are de platit fiecare consumator distribuitorului
     */
    private final int price;
    /**
     * numarul de luni ramase pana la terminarea contractului
     */
    private final int remainedContractMonths;

    public Contracts(final int consumerId, final int price, final int remainedContractMonths) {
        this.consumerId = consumerId;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
    }

    public int getConsumerId() {
        return consumerId;
    }

    public int getPrice() {
        return price;
    }

    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    @Override
    public String toString() {
        return "Contracts{"
                + "consumerId=" + consumerId
                + ", price=" + price
                + ", remainedContractMonths=" + remainedContractMonths
                + '}';
    }
}
