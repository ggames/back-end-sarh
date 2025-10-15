package com.fich.sarh.plantofpositions.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.plantofpositions.application.ports.entrypoint.api.ExcelPlantReportApiPort;
import com.fich.sarh.plantofpositions.application.ports.persistence.ExcelPlantsReportSpiPort;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@UseCase
public class ExcelPlantsReportUseCase implements ExcelPlantReportApiPort {

    private final ExcelPlantsReportSpiPort excelReport;

    public ExcelPlantsReportUseCase(ExcelPlantsReportSpiPort excelReport) {
        this.excelReport = excelReport;
    }

    @Override
    public void createExcel(List<PlantOfPosition> plants) {
         excelReport.createExcel(plants);
    }
}
