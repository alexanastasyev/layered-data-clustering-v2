package dev.alexanastasyev.layereddataclusteringv2.clustering;

import dev.alexanastasyev.layereddataclusteringv2.model.ClusteringModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ClustersCalculatorImpl<T> implements ClustersCalculator<T> {
    private AdjustmentMatrix adjustmentMatrix;
    private List<ClusteringModel<T>> clusteringModels;

    @Override
    public void loadClusteringModels(List<ClusteringModel<T>> clusteringModels) {
        this.adjustmentMatrix = createAdjustmentMatrix(clusteringModels);
        this.clusteringModels = clusteringModels;
    }

    @Override
    public List<Set<T>> calculateClusters(double level) {
        List<Set<Integer>> indexClusters = adjustmentMatrix.getClusters(level);
        List<Set<T>> entityClusters = new ArrayList<>();
        indexClusters.forEach(cluster -> {
            entityClusters.add(cluster.stream()
                    .map(index -> clusteringModels.get(index).getEntityKey())
                    .collect(Collectors.toSet())
            );
        });
        return entityClusters;
    }

    private AdjustmentMatrix createAdjustmentMatrix(List<ClusteringModel<T>> clusteringModels) {
        AdjustmentMatrix adjustmentMatrix = new AdjustmentMatrix(clusteringModels.size());
        for (int i = 0; i < clusteringModels.size() - 1; i++) {
            for (int j = i + 1; j < clusteringModels.size(); j++) {
                double distance = calculateDistance(clusteringModels.get(i), clusteringModels.get(j));
                adjustmentMatrix.addDistance(i, j, distance);
            }
        }
        return adjustmentMatrix;
    }

    private double calculateDistance(ClusteringModel<T> model1, ClusteringModel<T> model2) {
        double sum = 0;
        Set<String> keys = model1.getClusteringValues().keySet();
        for (String key : keys) {
            sum += sqrDiff(model1.getClusteringValues().get(key), model2.getClusteringValues().get(key));
        }
        return Math.sqrt(sum);
    }

    private double sqrDiff(double value1, double value2) {
        return Math.pow(value1 - value2, 2);
    }
}
