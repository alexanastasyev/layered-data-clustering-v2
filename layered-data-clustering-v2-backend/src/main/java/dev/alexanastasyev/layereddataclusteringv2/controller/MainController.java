package dev.alexanastasyev.layereddataclusteringv2.controller;

import dev.alexanastasyev.layereddataclusteringv2.model.ClusteringModel;
import dev.alexanastasyev.layereddataclusteringv2.model.Student;
import dev.alexanastasyev.layereddataclusteringv2.service.ClusteringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class MainController {
    private final ClusteringService clusteringService;

    @Autowired
    public MainController(ClusteringService clusteringService) {
        this.clusteringService = clusteringService;
    }

    @GetMapping("/data")
    public ResponseEntity<List<ClusteringModel<Student>>> getModels() {
        return ResponseEntity.ok(clusteringService.getClusteringModels());
    }

    @GetMapping("/clusters")
    public ResponseEntity<List<Set<Student>>> getClusters(@RequestParam double level) {
        return ResponseEntity.ok(clusteringService.getClusters(level));
    }
}
