package fileio;

import producers.ProducersInputData;

import java.util.Comparator;

public final class SortProducersInput implements Comparator<ProducersInputData> {
    @Override
    public int compare(ProducersInputData o1, ProducersInputData o2) {
        return o1.getId() - o2.getId();
    }
}
