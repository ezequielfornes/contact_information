package org.example;

import java.util.ArrayList;
import java.util.List;

public class ContactService {

   private static final String NONE_SCORE = "None";
   public static List<String> findMatches(List<Contact> contacts) {
      List<String> matches = new ArrayList<>();

      for (int i = 0; i < contacts.size(); i++) {
         for (int j = i + 1; j < contacts.size(); j++) {
            String matchScore = getMatchScore(contacts.get(i), contacts.get(j));
            if (!matchScore.equals(NONE_SCORE)) {
               matches.add(formatMatch(contacts.get(i), contacts.get(j), matchScore));
            }
         }
      }

      return matches;
   }

   private static String getMatchScore(Contact c1, Contact c2) {
      if (c1.id == c2.id) { //Not compare with the same
         return NONE_SCORE;
      }
      int score = 0;

      if (c1.email.equalsIgnoreCase(c2.email)) score += 5;
      if (c1.firstName.equalsIgnoreCase(c2.firstName)) score += 2;
      if (c1.lastName.equalsIgnoreCase(c2.lastName)) score += 2;
      if (c1.address.equalsIgnoreCase(c2.address) && c1.zipCode.equalsIgnoreCase(c2.zipCode) ) score += 4;

      if (score >= 8) return "High";
      if (score >= 4) return "Medium";
      if (score > 0) {
         return "Low";
      }
      return "None";
   }

   private static String formatMatch(Contact c1, Contact c2, String matchScore) {
      return String.format("The id contact %s and %s have a %s level of potentially duplicate",c1.id, c2.id, matchScore);
   }
}
