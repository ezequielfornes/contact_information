package org.example;

public class Contact {
   int id;
   String firstName;
   String lastName;
   String email;
   String zipCode;
   String address;

   public Contact(int id, String firstName, String lastName, String email, String zipCode, String address) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.zipCode = zipCode;
      this.address = address;
   }
}
