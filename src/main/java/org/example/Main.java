package org.example;

import java.io.IOException;
import java.util.List;

import static org.example.ExcelReader.readContactsFromExcel;

public class Main {
   public static void main(String[] args) {
      try {
         String filePath = "/contacts.xlsx";
         List<Contact> contacts = readContactsFromExcel(filePath);

         List<String> matches = ContactService.findMatches(contacts);
         for (String match : matches) {
            System.out.println(match);
         }

      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
