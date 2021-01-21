package fileio;

import producers.ProducersOutputData;

import java.util.Comparator;

public final class SortProducers implements Comparator<ProducersOutputData> {

    @Override
    public int compare(ProducersOutputData o1, ProducersOutputData o2) {
        return o1.getId() - o2.getId();
    }
}
