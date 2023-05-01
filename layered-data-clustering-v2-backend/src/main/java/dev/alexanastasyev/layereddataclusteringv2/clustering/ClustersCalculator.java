package dev.alexanastasyev.layereddataclusteringv2.clustering;

import dev.alexanastasyev.layereddataclusteringv2.model.ClusteringModel;

import java.util.List;
import java.util.Set;

public interface ClustersCalculator<T> {
    void loadClusteringModels(List<ClusteringModel<T>> clusteringModels);
    List<Set<T>> calculateClusters(double level);
}
