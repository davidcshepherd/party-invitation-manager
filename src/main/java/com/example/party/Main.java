package com.example.party;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Party Invitation Manager ===");
        System.out.print("Your email (sender): ");
        String sender = scanner.nextLine();
        System.out.print("Email password (app password recommended): ");
        String password = scanner.nextLine();

        EmailService emailService = new EmailService(new ActualEmailSender(sender, password));
        PartyManager manager = new PartyManager(new ContactDatabase(), emailService);
        while (true) {
            System.out.println("\n1. Add contact\n2. View contacts\n3. Create invitation\n4. Exit");
            System.out.print("Choose: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    manager.addContact(name, email);
                }
                case 2 -> manager.getContacts().forEach(System.out::println);
                case 3 -> {
                    System.out.print("Subject: ");
                    String subject = scanner.nextLine();
                    System.out.print("Message: ");
                    String message = scanner.nextLine();
                    Invitation invite = new Invitation(subject, message);

                    System.out.println("Available contacts:");
                    var contacts = manager.getContacts();
                    for (int i = 0; i < contacts.size(); i++) {
                        System.out.println((i + 1) + ". " + contacts.get(i));
                    }
                    System.out.print("Enter recipient numbers (comma separated): ");
                    String[] nums = scanner.nextLine().split(",");
                    for (String n : nums) {
                        invite.addRecipient(contacts.get(Integer.parseInt(n.trim()) - 1));
                    }

                    manager.sendInvitation(invite);
                }
                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }
            }
        }
    }
}
