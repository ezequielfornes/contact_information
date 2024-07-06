import org.example.Contact;
import org.example.ContactService;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {

   @Test
   public void testFindMatches_NoDuplicates() {
      List<Contact> contacts = Arrays.asList(
            new Contact(1, "John", "Doe", "john.doe@test.com", "123 Main St", "12345"),
            new Contact(2, "Jane", "Smith", "jane.smith@test.com", "456 Elm St", "67890"),
            new Contact(3, "Michael", "Johnson", "michael.johnson@test.com", "789 Oak St", "54321")
      );
      List<String> matches = ContactService.findMatches(contacts);
      //then
      assertTrue(matches.isEmpty());
   }

   @Test
   public void testFindMatches_WithDuplicates() {
      List<Contact> contacts = Arrays.asList(
            new Contact(1, "John", "test", "test.test@test.com", "123 Main St", "12345"),
            new Contact(2, "Jane", "Smith", "jane.smith@test.com", "456 Elm St", "67890"),
            new Contact(3, "John", "test", "test.test@test.com", "123 Main St", "12345"),
            new Contact(4, "Michael", "Johnson", "michael.johnson@test.com", "789 Oak St", "54321")
      );

      List<String> matches = ContactService.findMatches(contacts);
      //then
      assertFalse(matches.isEmpty());
      assertEquals(1, matches.size());
      assertTrue(matches.get(0).contains("potentially duplicate"));
   }

}
