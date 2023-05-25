package org.example.util;

import org.example.Service.CustomerService;
import org.example.model.Address;
import org.example.model.Customer;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IHM {
    private Scanner scanner;
    private CustomerService customerService;

    public IHM() {
        scanner = new Scanner(System.in);
        customerService = new CustomerService();
    }

    public void start() {
        int entry;
        do {
            menu();
            entry = scanner.nextInt();
            scanner.nextLine();
            switch (entry) {
                case 1:
                    addCustomerAction();
                    break;
                case 2:
                    deleteCustomerAction();
                    break;
                case 3:
                    editCustomerAction();
                    break;
                case 4:
                    showAllCustomerAction();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("entrer une valeur valide");
                    break;
            }
        } while (entry != 0);
    }

    private void menu() {
        System.out.println("-------- menu --------");
        System.out.println("1-- ajouter un clients");
        System.out.println("2-- supprimer un client");
        System.out.println("3-- modifier un client");
        System.out.println("4-- afficher tout les clients");
        System.out.println("5-- inscrire un client a un cours");
        System.out.println("----------------");
        System.out.println("6-- creation d'un cours");
        System.out.println("7-- suppresion d'un cours");
        System.out.println("8-- modification d'un cours");
        System.out.println("----------------");
        System.out.println("9-- creation d'un coach");
        System.out.println("10-- suppresion d'un coach");
        System.out.println("11-- modification d'un coach");
        System.out.println("12-- ajout d'un coach a un cours");
    }

    private void addCustomerAction() {
        try {
            System.out.println("-------- ajout d'un client --------");
            System.out.println("prenom :");
            String firstName = scanner.nextLine();
            System.out.println("nom :");
            String lastName = scanner.nextLine();

            System.out.println("duree de l'inscription (en jour) :");
            long subDuration = scanner.nextLong();
            scanner.nextLine();

            long dateSecNow = System.currentTimeMillis(); // recuperationd de la date actuelle en milliseconds
            long dateSecEnd = dateSecNow + 86400000 * subDuration; //ajout du nombres de  jours voulus en milliseconds
            Date dateEnd = new Date(dateSecEnd);

            System.out.println("---- addresse ----");
            System.out.println("numero + rue :");
            String street = scanner.nextLine();
            System.out.println("ville :");
            String city = scanner.nextLine();
            System.out.println("code postal :");
            String postalCode = scanner.nextLine();

            Address address = customerService.findAddress(street,city,postalCode);
            if(address == null){
                address= new Address(street, city, postalCode);
                customerService.createAddress(address);
            }
            Customer customer = new Customer(lastName, firstName, dateEnd);
            customer.setAddress(address);
            if (customerService.create(customer)) {
                System.out.println("client crée");
            } else {
                System.out.println("erreure lors de la creation du client");
            }
        } catch (InputMismatchException e) {
            System.out.println("entrer une valeure correcte");
        }
    }
    private void deleteCustomerAction() {
        try {
            System.out.println("-------- suppresion d'un client --------");
            System.out.println("id du client :");
            int id = scanner.nextInt();
            scanner.nextLine();

            Customer customer = customerService.findById(id);
            if (customer != null) {
                if (customerService.delete(customer)) {
                    System.out.println("client supprimer");
                } else {
                    System.out.println("erreure lors de la suppresion du client");
                }
            } else {
                System.out.println("erreure lors de la recuperation du client");
            }
        } catch (InputMismatchException e) {
            System.out.println("entrer une valeure correcte");
        }

    }
    private void editCustomerAction() {
        try {
            System.out.println("-------- Moddification d'un client --------");
            System.out.println("id du client a moddifier :");
            int id = scanner.nextInt();
            scanner.nextLine();

            Customer customer = customerService.findById(id);
            if (customer != null) {
                System.out.println("nom ( " + customer.getLastName() + " ) :");
                String lastName = scanner.nextLine();
                customer.setLastName(lastName);

                System.out.println("prenom ( " + customer.getFirstName() + " ) :");
                String firstName = scanner.nextLine();
                customer.setFirstName(firstName);

                System.out.println("prolongation de l'abonement en jour (fin : " + customer.getDateEndInscription() + " ) : ");
                long nbDay = scanner.nextLong();
                scanner.nextLine();
                if (nbDay > 0) {
                    long dateEndSec = customer.getDateEndInscription().getTime();
                    long dateEndSecEdit = dateEndSec + nbDay * 86400000;
                    Date dateEnd = new Date(dateEndSecEdit);
                    customer.setDateEndInscription(dateEnd);
                }

                if (customerService.update(customer)) {
                    System.out.println("client modifié");
                } else {
                    System.out.println("erreure lors de la modification");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("entrer une valeur correcte");
        }
    }
    private void showAllCustomerAction (){
        System.out.println("-------- afficher tout les clients --------");
        customerService.findAll().forEach(System.out::println);
    }


}
