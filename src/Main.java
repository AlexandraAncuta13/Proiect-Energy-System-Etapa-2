import com.fasterxml.jackson.databind.ObjectMapper;
import consumers.ConsumersInputData;
import consumers.ConsumersOutputData;
import distributors.DistributorsInputData;
import distributors.DistributorsOutputData;
import distributors.Contracts;
import fileio.Input;
import fileio.Output;
import fileio.Simulation;
import producers.ProducersInputData;
import producers.ProducersOutputData;
import fileio.SortProducers;
import java.io.File;

/**
 * Entry point to the simulation
 */
public final class Main {

    private Main() { }

    /**
     * Main function which reads the input file and starts simulation
     *
     * @param args input and output files
     * @throws Exception might error when reading/writing/opening files, parsing JSON
     */
    public static void main(final String[] args) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        Input input = mapper.readValue(new File(args[0]), Input.class);

        Simulation simulation = new Simulation();
        simulation.createEnergeticSystem(input);

        Output output = new Output();
        for (int i = 0; i < input.getInitialData().getConsumers().size(); i++) {
            final String entity = "consumer";
            ConsumersOutputData consumersOutputData = new ConsumersOutputData();
            ConsumersInputData consumer = input.getInitialData().getConsumers().get(i);
            consumersOutputData.setBudget(consumer.getInitialBudget());
            consumersOutputData.setId(consumer.getId());
            if (consumer.getDebt() == 2) {
                consumersOutputData.setIsBankrupt(true);
            } else {
                consumersOutputData.setIsBankrupt(false);
            }
            output.getConsumers().add(consumersOutputData);
        }

        for (int i = 0; i < input.getInitialData().getDistributors().size(); i++) {
            final String entity = "distributor";
            DistributorsInputData distibutor = input.getInitialData().getDistributors().get(i);
            DistributorsOutputData distributorsOutput = new DistributorsOutputData();
            distributorsOutput.setId(distibutor.getId());
            distributorsOutput.setBudget(distibutor.getInitialBudget());
            if (distibutor.getDebt() == 1) {
                distributorsOutput.setIsBankrupt(true);
            } else {
                distributorsOutput.setIsBankrupt(false);
            }
            // Sortez lista finala de distribuitori primul criteriu fiind numarul de luni
            // ramase din contract, iar al doilea criteriu este id-ul
            distibutor.getConsumers().sort((o1, o2) -> {
                if (o1.getRemainedMonths() < o2.getRemainedMonths()) {
                    return -1;
                }
                if (o1.getRemainedMonths() > o2.getRemainedMonths()) {
                    return 1;
                }
                return o1.getId() - o2.getId();
            });
            for (int k = 0; k < distibutor.getConsumers().size(); k++) {
                ConsumersInputData consumer = distibutor.getConsumers().get(k);
                Contracts contracts = new Contracts(consumer.getId(), consumer.getBill(),
                        consumer.getRemainedMonths());
                distributorsOutput.getContracts().add(contracts);
            }
            distributorsOutput.setProducerStrategy(distibutor.getProducerStrategy());
            distributorsOutput.setEnergyNeededKW(distibutor.getEnergyNeededKW());
            distributorsOutput.setContractCost(distibutor.getContractCost());
            output.getDistributors().add(distributorsOutput);
        }
        for (int i = 0; i < input.getInitialData().getProducers().size(); i++) {
            ProducersInputData prod = input.getInitialData().getProducers().get(i);
            ProducersOutputData producersOutputData = new ProducersOutputData();
            producersOutputData.setId(prod.getId());
            producersOutputData.setMaxDistributors(prod.getMaxDistributors());
            producersOutputData.setPriceKW(prod.getPriceKW());
            producersOutputData.setEnergyType(prod.getEnergyType());
            producersOutputData.setEnergyPerDistributor(prod.getEnergyPerDistributor());
            producersOutputData.setMonthlyStats(prod.getMonthlyStats());
            output.getEnergyProducers().add(producersOutputData);
        }
        output.getEnergyProducers().sort(new SortProducers());
        ObjectMapper objectMapper1 = new ObjectMapper();
        objectMapper1.writerWithDefaultPrettyPrinter().writeValue(new File(args[1]), output);

    }
}
