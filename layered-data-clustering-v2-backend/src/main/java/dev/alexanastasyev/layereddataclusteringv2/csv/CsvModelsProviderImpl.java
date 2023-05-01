package dev.alexanastasyev.layereddataclusteringv2.csv;

import dev.alexanastasyev.layereddataclusteringv2.model.CsvModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvModelsProviderImpl implements CsvModelsProvider {

    private List<CsvModel> csvModels;

    @Override
    public synchronized List<CsvModel> provideCsvModels() {
        if (this.csvModels == null) {
            this.csvModels = readModelsFromFile();
        }
        return this.csvModels;
    }

    private List<CsvModel> readModelsFromFile() {
        try (XSSFWorkbook workbook = new XSSFWorkbook(new File("data.xlsx"))) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowNumber = sheet.getPhysicalNumberOfRows();
            int columnNumber = sheet.getRow(0).getPhysicalNumberOfCells();

            List<String> headers = new ArrayList<>();

            for (int columnIndex = 0; columnIndex < columnNumber; columnIndex++) {
                headers.add(sheet.getRow(0).getCell(columnIndex).toString());
            }

            List<CsvModel> csvModels = new ArrayList<>();
            for (int rowIndex = 1; rowIndex < rowNumber; rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);
                if (row == null) {
                    continue;
                }
                CsvModel csvModel = new CsvModel();
                for (int columnIndex = 0; columnIndex < columnNumber; columnIndex++) {
                    XSSFCell cell = row.getCell((short) columnIndex);
                    if (cell == null) {
                        continue;
                    }
                    csvModel.putValue(headers.get(columnIndex), cell.toString());
                }
                csvModels.add(csvModel);
            }

            return csvModels;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
