package dev.alexanastasyev.layereddataclusteringv2.converter;

import dev.alexanastasyev.layereddataclusteringv2.model.ClusteringModel;
import dev.alexanastasyev.layereddataclusteringv2.model.CsvModel;
import dev.alexanastasyev.layereddataclusteringv2.model.Student;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class CsvModelToClusteringModelConverter implements Function<CsvModel, ClusteringModel<Student>> {

    private static final String STUDENT_NUMBER_KEY = "№ п/п";
    private static final String STUDENT_NAME_KEY = "ФИО студента";
    private static final String EMPTY_COLUMN_KEY = "w32";

    @Override
    public ClusteringModel<Student> apply(CsvModel csvModel) {
        Map<String, String> values = new LinkedHashMap<>(csvModel.getValues());
        Student student = getStudentFromValues(values);
        values.remove(STUDENT_NUMBER_KEY);
        values.remove(STUDENT_NAME_KEY);
        values.remove(EMPTY_COLUMN_KEY);
        Map<String, Double> clusteringValues = new LinkedHashMap<>();
        values.keySet().forEach(key -> {
            BigDecimal value = BigDecimal.valueOf(Double.parseDouble(values.get(key)));
            value = value.setScale(3, RoundingMode.HALF_UP);
            clusteringValues.put(key, value.doubleValue());
        });
        return new ClusteringModel<>(student, clusteringValues);
    }

    private Student getStudentFromValues(Map<String, String> values) {
        int studentNumber = Double.valueOf(values.get(STUDENT_NUMBER_KEY)).intValue();
        String studentName = values.get(STUDENT_NAME_KEY);
        return new Student(studentNumber, studentName);
    }

}
