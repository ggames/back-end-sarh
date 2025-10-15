package com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.plantofpositions.application.ports.persistence.ExcelPlantsReportSpiPort;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.repository.PlantOfPositionRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebAdapter
public class ExcelPlantsReportAdapter implements ExcelPlantsReportSpiPort {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean createExcel(List<PlantOfPosition> plants) {

        try {
            SXSSFWorkbook wb = new SXSSFWorkbook(1);
            SXSSFSheet sheet = wb.createSheet();

            final var style = wb.createCellStyle();
            final var font = wb.createFont();

            font.setBold(true);
            style.setFont(font);

            Row nRow = null;
            Cell nCell = null;

            // Generar la cabecera
            Object[] columns = {"ID","Cod Cargo", "Nombre", "Apellido", "Departamento", "Materia"};

            nRow = sheet.createRow(0);

            for (int i = 0; i < columns.length; i++) {
                nCell = nRow.createCell(i);
                nCell.setCellValue(columns[i].toString());
            }

            // Generamos el cuerpo del excel
            Iterator<PlantOfPosition> it = plants.iterator();
            int pageRowNo = 1;

            while (it.hasNext()) {
                PlantOfPosition objExcelPlant = it.next();
                nRow = sheet.createRow(pageRowNo++);
                nRow.createCell(0).setCellValue(objExcelPlant.getId().toString());

                nRow.createCell(1).setCellValue(objExcelPlant.getPosition().getPointID().getPositionCode().toString());
                nRow.createCell(2).setCellValue(objExcelPlant.getAgent().getFirstname());
                nRow.createCell(3).setCellValue(objExcelPlant.getAgent().getLastname());
                nRow.createCell(4).setCellValue(objExcelPlant.getOrganizationalSubUnit().getNameSubUnit());
                nRow.createCell(5).setCellValue(objExcelPlant.getOrganizationalSubUnit().getOrganizationalUnit().getNameUnit());
            }

            FileOutputStream fileOutputStream = new FileOutputStream("report-excel.xlsx");
            wb.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            wb.dispose();

            return true;

        } catch (Exception e) {

            return false;
        }


    }
}
