package dev.alexanastasyev.layereddataclusteringv2.csv;

import dev.alexanastasyev.layereddataclusteringv2.model.CsvModel;

import java.util.List;

public interface CsvModelsProvider {
    List<CsvModel> provideCsvModels();
}
