package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
   public static List<Contact> readContactsFromExcel(String filePath) throws IOException {
      List<Contact> contacts = new ArrayList<>();
      try (InputStream inputStream = ExcelReader.class.getResourceAsStream(filePath)) {
         if (inputStream == null) {
            throw new IOException("File not found: " + filePath);
         }

         Workbook workbook = new XSSFWorkbook(inputStream);
         Sheet sheet = workbook.getSheetAt(0);

         for (Row row : sheet) {
            // Skip header row
            if (row.getRowNum() == 0) {
               continue;
            }

            int id = (int) row.getCell(0).getNumericCellValue();
            String firstName = getCellValueAsString(row.getCell(1));
            String lastName = getCellValueAsString(row.getCell(2));
            String email = getCellValueAsString(row.getCell(3));
            String zipCode = getCellValueAsString(row.getCell(4));
            String address = getCellValueAsString(row.getCell(5));

            contacts.add(new Contact(id, firstName, lastName, email, zipCode, address));
         }

         workbook.close();
      } catch (IOException e) {
         System.err.println("Error reading the Excel file: " + e.getMessage());
         throw e;
      }

      return contacts;
   }

   private static String getCellValueAsString(Cell cell) {
      if (cell == null) {
         return "";
      }

      switch (cell.getCellType()) {
         case STRING:
            return cell.getStringCellValue();
         case NUMERIC:
            if (DateUtil.isCellDateFormatted(cell)) {
               return cell.getDateCellValue().toString();
            } else {
               return String.valueOf((int) cell.getNumericCellValue());
            }
         case BOOLEAN:
            return String.valueOf(cell.getBooleanCellValue());
         case FORMULA:
            return cell.getCellFormula();
         case BLANK:
            return "";
         default:
            return "";
      }
   }
}
