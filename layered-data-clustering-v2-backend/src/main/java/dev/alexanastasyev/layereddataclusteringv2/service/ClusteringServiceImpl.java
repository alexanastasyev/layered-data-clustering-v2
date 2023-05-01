package dev.alexanastasyev.layereddataclusteringv2.service;

import dev.alexanastasyev.layereddataclusteringv2.clustering.ClustersCalculator;
import dev.alexanastasyev.layereddataclusteringv2.model.ClusteringModel;
import dev.alexanastasyev.layereddataclusteringv2.model.CsvModel;
import dev.alexanastasyev.layereddataclusteringv2.model.Student;
import dev.alexanastasyev.layereddataclusteringv2.csv.CsvModelsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ClusteringServiceImpl implements ClusteringService {
    private final CsvModelsProvider csvModelsProvider;
    private final Function<CsvModel, ClusteringModel<Student>> csvModelToClusteringModelConverter;
    private final ClustersCalculator<Student> clustersCalculator;

    @Autowired
    public ClusteringServiceImpl(CsvModelsProvider csvModelsProvider,
                                 Function<CsvModel, ClusteringModel<Student>> csvModelToClusteringModelConverter,
                                 ClustersCalculator<Student> clustersCalculator) {
        this.csvModelsProvider = csvModelsProvider;
        this.csvModelToClusteringModelConverter = csvModelToClusteringModelConverter;
        this.clustersCalculator = clustersCalculator;
    }

    @Override
    public List<ClusteringModel<Student>> getClusteringModels() {
        return csvModelsProvider.provideCsvModels().stream()
                .map(csvModelToClusteringModelConverter)
                .collect(Collectors.toList());
    }

    @Override
    public List<Set<Student>> getClusters(double level) {
        this.clustersCalculator.loadClusteringModels(getClusteringModels());
        return this.clustersCalculator.calculateClusters(level);
    }

}
