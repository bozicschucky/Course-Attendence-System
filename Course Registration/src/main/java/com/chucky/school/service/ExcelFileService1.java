package com.chucky.school.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.chucky.school.DTO.CourseOfferingDetailsDTO;

import java.util.List;

@Service("excelFileService1")
public class ExcelFileService1 {

  public Workbook createExcelFile(List<CourseOfferingDetailsDTO> data) {
    Workbook workbook = new XSSFWorkbook();
    Sheet sheet = workbook.createSheet("Course Offerings Attendance Report");
    Row headerRow = sheet.createRow(0);

    // Create header cells
    Cell cell = headerRow.createCell(0);
    cell.setCellValue("ID");
    cell.setCellValue("Course ID");
    cell.setCellValue("Faculty ID");
    cell.setCellValue("Capacity");
    cell.setCellValue("Course Offering Type");
    cell.setCellValue("Room");
    cell.setCellValue("Start Date");
    cell.setCellValue("End Date");

    for (int i = 0; i < data.size(); i++) {
      Row row = sheet.createRow(i + 1);
      row.createCell(0).setCellValue(data.get(i).getId());
      row.createCell(1).setCellValue(data.get(i).getCourseId());
      row.createCell(2).setCellValue(data.get(i).getFacultyId());
      row.createCell(3).setCellValue(data.get(i).getCapacity());
      row.createCell(4).setCellValue(data.get(i).getCourseOfferingType());
      row.createCell(5).setCellValue(data.get(i).getRoom());
      row.createCell(6).setCellValue(data.get(i).getStartDate().toString());
      row.createCell(7).setCellValue(data.get(i).getEndDate().toString());
    }

    return workbook;
  }
}