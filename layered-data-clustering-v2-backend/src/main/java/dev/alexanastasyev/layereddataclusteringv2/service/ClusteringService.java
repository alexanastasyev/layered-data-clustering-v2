package dev.alexanastasyev.layereddataclusteringv2.service;

import dev.alexanastasyev.layereddataclusteringv2.model.ClusteringModel;
import dev.alexanastasyev.layereddataclusteringv2.model.Student;

import java.util.List;
import java.util.Set;

public interface ClusteringService {
    List<ClusteringModel<Student>> getClusteringModels();
    List<Set<Student>> getClusters(double level);
}
