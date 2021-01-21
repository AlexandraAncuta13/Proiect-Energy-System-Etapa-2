package fileio;

import consumers.ConsumersInputData;
import distributors.DistributorChanges;
import distributors.DistributorsInputData;
import distributors.SortById;
import producers.MonthlyStats;
import producers.ProducerStrategy;
import producers.ProducersInputData;
import producers.StrategyFactory;
import producers.ProducerChanges;

import java.util.ArrayList;
import java.util.List;

public final class Simulation {

    /**
     * Metoda care implementeaza simularea sistemului energetic
     */
    public void createEnergeticSystem(final Input input) {
        final double penalty = 1.2;
        // Asignez distribuitorii la producatori
        input.getInitialData().getDistributors().sort(new SortById());
        for (int j = 0; j < input.getInitialData().getDistributors().size(); j++) {
            DistributorsInputData dis = input.getInitialData().getDistributors().get(j);
            StrategyFactory strategyFactory = new StrategyFactory();
            ProducerStrategy producerStrategy = strategyFactory
                    .createStrategy(dis, input.getInitialData().getProducers());
            producerStrategy.getProducers();
        }

        // fac calcule pentru initial data (runda initiala), urmand apoi monthly updates
        int minBill = Integer.MAX_VALUE; // il folosesc ca sa aleg factura minima
        int index = 0; // il folosesc pentru a stoca indexul celui mai ieftin distribuitor
        for (int i = 0; i < input.getInitialData().getDistributors().size(); i++) {
            if (input.getInitialData().getDistributors().get(i).calculateBill() < minBill) {
                minBill = input.getInitialData().getDistributors().get(i).calculateBill();
                index = i;
            }
        }
        // Distribuitorul care are factura cea mai mica
        DistributorsInputData minDistributor = input.getInitialData()
                                                    .getDistributors().get(index);
        for (int i = 0; i < input.getInitialData().getConsumers().size(); i++) {
            ConsumersInputData consumer = input.getInitialData().getConsumers().get(i);
            consumer.setRemainedMonths(minDistributor.getContractLength() - 1);
            consumer.setBill(minBill);
            consumer.setDistribuitor(minDistributor);
            minDistributor.getConsumers().add(consumer);
            if (consumer.getInitialBudget() + consumer.getMonthlyIncome() < consumer.getBill()) {
                consumer.setDebt(1);
                consumer.getDistribuitor().setInitialBudget(consumer.getDistribuitor()
                        .getInitialBudget() - consumer.getBill());
                consumer.setInitialBudget(consumer.getInitialBudget()
                        + consumer.getMonthlyIncome());
            } else {
                consumer.setInitialBudget(consumer.getInitialBudget()
                        - consumer.calculateCosts());
            }
        }

        for (int i = 0; i < input.getInitialData().getDistributors().size(); i++) {
            DistributorsInputData distributor = input.getInitialData().getDistributors().get(i);
            distributor.setInitialBudget(distributor.getInitialBudget()
                    - distributor.calculateCosts());
        }

        for (int i = 0; i < input.getNumberOfTurns(); i++) {
            if (input.getMonthlyUpdates().get(i).getDistributorChanges().size() > 0) {
                // Actualizez costurile distribuitorilor
                for (int j = 0; j < input.getMonthlyUpdates().get(i)
                        .getDistributorChanges().size(); j++) {
                    DistributorChanges costChanges = input.getMonthlyUpdates()
                                                        .get(i).getDistributorChanges().get(j);
                    int id = costChanges.getId();
                    int infrastructure = costChanges.getInfrastructureCost();
                    input.getInitialData().getDistributors().get(id)
                            .setInitialInfrastructureCost(infrastructure);
                    if (i == input.getNumberOfTurns() - 1) {
                        input.getInitialData().getDistributors().get(id)
                                .setContractCost(input.getInitialData().getDistributors()
                                        .get(id).calculateBill());
                    }
                }
            }
            for (int j = 0; j < input.getInitialData().getConsumers().size(); j++) {
                ConsumersInputData consumer = input.getInitialData().getConsumers().get(j);
                if (consumer.getDebt() > 1) {
                    consumer.getDistribuitor().getConsumers().remove(consumer);
                }
            }

            // Calculez factura minima
            minBill = Integer.MAX_VALUE;
            for (int j = 0; j < input.getInitialData().getDistributors().size(); j++) {
                if (input.getInitialData().getDistributors().get(j).calculateBill() < minBill
                        && input.getInitialData().getDistributors().get(j).getDebt() == 0) {
                    minBill = input.getInitialData().getDistributors().get(j).calculateBill();
                    index = j;
                }
            }
            // distribuitorul cu factura minima
            minDistributor = input.getInitialData().getDistributors().get(index);

            for (int j = 0; j < input.getMonthlyUpdates().get(i).getNewConsumers().size(); j++) {
                ConsumersInputData consumer = input.getMonthlyUpdates().get(i)
                        .getNewConsumers().get(j);
                consumer.setDistribuitor(minDistributor);
                consumer.setBill(minDistributor.calculateBill());
                minDistributor.getConsumers().add(consumer);
                consumer.setRemainedMonths(minDistributor.getContractLength());
                input.getInitialData().getConsumers().add(consumer);
            }

            for (int j = 0; j < input.getInitialData().getConsumers().size(); j++) {
                ConsumersInputData consumer = input.getInitialData().getConsumers().get(j);
                if (consumer.getDebt() == 2) {
                    continue;
                }
                // Consumatorul are datorii
                if (consumer.getDebt() == 1) {
                    // Consumatorului i s-a terminat contractul
                    if (consumer.getRemainedMonths() == 0) {
                        if (consumer.getInitialBudget() + consumer.getMonthlyIncome()
                                < Math.round(Math.floor(penalty * consumer.getBill())) + minBill) {
                            // A dat faliment
                            consumer.setDebt(2);
                            consumer.setRemainedMonths(minDistributor.getContractLength() - 1);
                            consumer.getDistribuitor().setInitialBudget(consumer.getDistribuitor()
                                    .getInitialBudget() - consumer.getBill());
                            consumer.setInitialBudget(consumer.getInitialBudget()
                                    + consumer.getMonthlyIncome());
                        } else {
                            // Isi permite alt distribuitor
                            consumer.setInitialBudget((int) (consumer.getInitialBudget()
                                    + consumer.getMonthlyIncome()
                                    - Math.round(Math.floor(penalty * consumer.getBill()))
                                    + minBill));
                            consumer.setDebt(0); // Nu mai are datorii
                            consumer.setBill(minBill);
                            consumer.setRemainedMonths(minDistributor.getContractLength() - 1);
                            consumer.getDistribuitor().getConsumers().remove(consumer);
                            consumer.setDistribuitor(minDistributor);
                            minDistributor.getConsumers().add(consumer);
                        }
                    } else {
                        if (consumer.getInitialBudget() + consumer.getMonthlyIncome()
                                < penalty * consumer.getBill() + consumer.getBill()) {
                            // A dat faliment
                            consumer.setRemainedMonths(minDistributor.getContractLength() - 1);
                            consumer.setDebt(2);
                            consumer.setInitialBudget(consumer.getInitialBudget()
                                    + consumer.getMonthlyIncome());
                            consumer.getDistribuitor().setInitialBudget(consumer.getDistribuitor()
                                    .getInitialBudget() - consumer.getBill());
                        } else {
                            // Isi permite sa plateasca noua factura
                            consumer.setInitialBudget((int) (consumer.getInitialBudget()
                                    + consumer.getMonthlyIncome()
                                    - Math.round(Math.floor(penalty * consumer.getBill()))
                                    + consumer.getBill()));
                            consumer.setDebt(0); // Nu mai are datorii
                            consumer.setRemainedMonths(consumer.getRemainedMonths() - 1);
                        }
                    }
                } else {
                    // Nu are datorii
                    if (consumer.getRemainedMonths() == 0) {
                        consumer.setBill(minBill);
                        consumer.setRemainedMonths(minDistributor.getContractLength());
                        consumer.getDistribuitor().getConsumers().remove(consumer);
                        consumer.setDistribuitor(minDistributor);
                        minDistributor.getConsumers().add(consumer);
                    }
                    consumer.setRemainedMonths(consumer.getRemainedMonths() - 1);
                    // Nu isi permite sa plateasca factura
                    if (consumer.getInitialBudget() + consumer.getMonthlyIncome()
                            < consumer.getBill()) {
                        consumer.setDebt(1);
                        consumer.getDistribuitor().setInitialBudget(consumer.getDistribuitor()
                                .getInitialBudget() - consumer.getBill());
                        consumer.setInitialBudget(consumer.getInitialBudget()
                                + consumer.getMonthlyIncome());
                    } else {
                        consumer.setInitialBudget(consumer.getInitialBudget()
                                - consumer.calculateCosts());
                    }
                }
            }
            for (int j = 0; j < input.getInitialData().getDistributors().size(); j++) {
                DistributorsInputData distibutor = input.getInitialData().getDistributors().get(j);
                // A dat faliment
                if (distibutor.getDebt() > 0) {
                    continue;
                }
                distibutor.setInitialBudget(distibutor.getInitialBudget()
                        - distibutor.calculateCosts());
                // Daca da faliment mut toti consumatorii lui la distribuitorul cu factura minima
                if (distibutor.getInitialBudget() < 0) {
                    distibutor.setDebt(1);
                    for (int k = 0; k < distibutor.getConsumers().size(); k++) {
                        ConsumersInputData cons = distibutor.getConsumers().get(k);
                        cons.setDebt(0);
                        cons.setDistribuitor(minDistributor);
                        cons.setRemainedMonths(minDistributor.getContractLength());
                        cons.setBill(minBill);
                    }
                    // Il scot din lista de distribuitori a producatorului
                    for (int k = 0; k < distibutor.getProducers().size(); k++) {
                        ProducersInputData prod = distibutor.getProducers().get(k);
                        prod.getDistributors().remove(distibutor);
                    }
                }
            }
            // Se actualizeaza valorile pentru producatori
            input.getInitialData().getProducers().sort(new SortProducersInput());
            if (input.getMonthlyUpdates().get(i).getProducerChanges().size() > 0) {
                for (int j = 0; j < input.getMonthlyUpdates().get(i)
                        .getProducerChanges().size(); j++) {
                    ProducerChanges producerChanges = input.getMonthlyUpdates()
                            .get(i).getProducerChanges().get(j);
                    int id = producerChanges.getId();
                    int energy = producerChanges.getEnergyPerDistributor();
                    // Se notifica distribuitorii aignati producatorilor la care au aparut schimbari
                    input.getInitialData().getProducers().get(id)
                            .setEnergy(energy);
                }

            }
            // Sortez distribuitorii crescator dupa id
            input.getInitialData().getDistributors().sort(new SortById());
            for (int j = 0; j < input.getInitialData().getDistributors().size(); j++) {
                DistributorsInputData dis = input.getInitialData().getDistributors().get(j);
                // Asignez fiecarui distribuitor producatorul/ producatorii de care are nevoie
                if (dis.getChangeEnergy() == 1 && dis.getDebt() != 1) {
                    // Scot distribuitorul de la toti producatorii de la care lua energie
                    for (int k = 0; k < dis.getProducers().size(); k++) {
                        dis.getProducers().get(k).deleteObserver(dis);
                        dis.getProducers().get(k).getDistributors().remove(dis);
                    }
                    dis.getProducers().clear();
                    StrategyFactory strategyFactory = new StrategyFactory();
                    ProducerStrategy producerStrategy = strategyFactory
                            .createStrategy(dis, input.getInitialData().getProducers());
                    producerStrategy.getProducers();
                    dis.setChangeEnergy(0);
                }
            }
            // Completez monthlyStats din luna respectiva pentru producatori
            for (int j = 0; j < input.getInitialData().getProducers().size(); j++) {
                List<Integer> ids = new ArrayList<>();
                ProducersInputData prod = input.getInitialData().getProducers().get(j);
                MonthlyStats monthlyStats = new MonthlyStats();
                monthlyStats.setMonth(i + 1);
                if (prod.getDistributors().size() > 0) {
                    prod.getDistributors().sort(new SortById());
                    for (int k = 0; k < prod.getDistributors().size(); k++) {
                        ids.add(prod.getDistributors().get(k).getId());
                    }
                }
                monthlyStats.setDistributorsIds(ids);
                prod.getMonthlyStats().add(monthlyStats);
            }
            if (i == input.getNumberOfTurns() - 1) {
                for (int j = 0; j < input.getInitialData().getConsumers().size(); j++) {
                    ConsumersInputData consumer = input.getInitialData().getConsumers().get(j);
                    if (consumer.getDebt() > 1) {
                        consumer.getDistribuitor().getConsumers().remove(consumer);
                    }
                }
            }
            if (i == input.getNumberOfTurns() - 2) {
                for (int j = 0; j < input.getInitialData().getDistributors().size(); j++) {
                    DistributorsInputData dis = input.getInitialData().getDistributors().get(j);
                    dis.setContractCost(dis.calculateBill());
                }
            }
        }
    }
}
