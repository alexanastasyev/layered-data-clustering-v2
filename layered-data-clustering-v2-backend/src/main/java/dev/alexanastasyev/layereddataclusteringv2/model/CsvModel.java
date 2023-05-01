package dev.alexanastasyev.layereddataclusteringv2.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class CsvModel {
    private final Map<String, String> values = new LinkedHashMap<>();

    public CsvModel() {
    }

    public void putValue(String key, String value) {
        this.values.put(key, value);
    }

    public String getValue(String key) {
        return this.values.get(key);
    }

    public Map<String, String> getValues() {
        return values;
    }
}
