package producers;

public interface ProducerStrategy {
    /**
     * Functie folosita pentru a asigna distribuitorii, in functie de strategia acestora
     * la producatorii corespunzatori
     */
    void getProducers();
}
