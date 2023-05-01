package dev.alexanastasyev.layereddataclusteringv2.model;

import java.util.Map;

public class ClusteringModel<T> {
    private final T entityKey;
    private final Map<String, Double> clusteringValues;

    public ClusteringModel(T entityKey, Map<String, Double> clusteringValues) {
        this.entityKey = entityKey;
        this.clusteringValues = clusteringValues;
    }

    public T getEntityKey() {
        return entityKey;
    }

    public Map<String, Double> getClusteringValues() {
        return clusteringValues;
    }
}
