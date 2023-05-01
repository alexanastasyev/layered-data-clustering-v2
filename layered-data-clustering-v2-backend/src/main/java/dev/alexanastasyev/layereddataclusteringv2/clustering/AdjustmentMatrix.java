package dev.alexanastasyev.layereddataclusteringv2.clustering;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class AdjustmentMatrix {

    private final double[][] matrix;

    public AdjustmentMatrix(int size) {
        this.matrix = new double[size][size];

        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                this.matrix[i][j] = -1;
            }
        }
    }

    public AdjustmentMatrix(AdjustmentMatrix adjustmentMatrix) {
        double[][] matrix = adjustmentMatrix.getMatrix();
        int size = matrix.length;
        this.matrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, size);
        }
    }

    public void addDistance(int indexFrom, int indexTo, double distance) {
        this.matrix[indexFrom][indexTo] = distance;
        this.matrix[indexTo][indexFrom] = distance;
    }

    public List<Set<Integer>> getClusters(double level) {
        this.removeEdges(level);

        Set<Integer> visited = new HashSet<>();

        List<Set<Integer>> result = new ArrayList<>();

        for (int i = 0; i < this.matrix.length; i++) {
            if (!visited.contains(i)) {

                visited.add(i);

                Set<Integer> cluster = new HashSet<>();
                cluster.add(i);
                addAllConnected(cluster, visited, i);

                AtomicBoolean added = new AtomicBoolean(true);
                while (added.get()) {
                    added.set(false);
                    Set<Integer> clusterCopy = new HashSet<>(cluster);

                    clusterCopy.forEach(item -> {
                        if (addAllConnected(cluster, visited, item)) {
                            added.set(true);
                        }
                    });
                }

                result.add(cluster);

            }
        }

        return result;
    }

    public AdjustmentMatrix copy() {
        return new AdjustmentMatrix(this);
    }

    public double[][] getMatrix() {
        return matrix;
    }

    private boolean addAllConnected(Set<Integer> cluster, Set<Integer> visited, int index) {
        boolean added = false;
        for (int i = 0; i < this.matrix[index].length; i++) {
            if (this.matrix[index][i] > 0) {
                if (visited.add(i)) {
                    added = cluster.add(i);
                }
            }
        }
        return added;
    }

    private void removeEdges(double level) {
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                if (this.matrix[i][j] > level) {
                    this.matrix[i][j] = -1;
                }
            }
        }
    }
}
