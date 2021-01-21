package distributors;

import java.util.Comparator;

public final class SortById implements Comparator<DistributorsInputData> {
    @Override
    public int compare(DistributorsInputData d1, DistributorsInputData d2) {
        return d1.getId() - d2.getId();
    }
}
