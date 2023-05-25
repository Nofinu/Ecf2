package org.example.util;

import org.example.Service.CoachService;
import org.example.Service.CourseService;
import org.example.Service.CustomerService;
import org.example.model.Address;
import org.example.model.Coach;
import org.example.model.Course;
import org.example.model.Customer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IHM {
    private final Scanner scanner;
    private final CustomerService customerService;
    private final CourseService courseService;
    private final CoachService coachService;

    public IHM() {
        scanner = new Scanner(System.in);
        customerService = new CustomerService();
        courseService = new CourseService();
        coachService = new CoachService();
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
                case 5:
                    addCustomerToCourseAction();
                    break;
                case 6:
                    addCourseAction();
                    break;
                case 7:
                    deleteCourseAction();
                    break;
                case 8:
                    editCourseAction();
                    break;
                case 9:
                    addCoachAction();
                    break;
                case 10:
                    deleteCoachAction();
                    break;
                case 11:
                    editCoachAction();
                    break;
                case 12:
                    addCoachToCourseAction();
                    break;
                case 13:
                    showAllcourseAction();
                    break;
                case 14 :
                    showCourseCustomerAction();
                    break;
                case 15 :
                    showCourseCoachAction();
                    break;
                case 16:
                    deleteCustomerFromCourseAction();
                    break;
                case 17:
                    deleteCoachFromCourseAction();
                    break;
                case 0:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("entrer une valeur valide");
                    break;
            }
        } while (entry != 0);
        customerService.end();
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
        System.out.println("----------------");
        System.out.println("13-- afficher tout les cours a venir");
        System.out.println("14-- afficher tout les cours d'un client a venir");
        System.out.println("15-- afficher tout les cours d'un coach a venir");
        System.out.println("----------------");
        System.out.println("16-- supprimer un client d'un cours");
        System.out.println("17-- supprimer un coach d'un cours");
    }

    //gestion Customer
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
                if(!customerService.createAddress(address)){
                    System.out.println("erreure lors de l'ajout de l'adresse");
                    return;
                }
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
                    if(customer.getDateEndInscription().before(new Date(System.currentTimeMillis()))){
                        long dateSecNow = System.currentTimeMillis(); // recuperationd de la date actuelle en milliseconds
                        long dateSecEnd = dateSecNow + 86400000 * nbDay; //ajout du nombres de  jours voulus en milliseconds
                        Date dateEnd = new Date(dateSecEnd);
                        customer.setDateEndInscription(dateEnd);
                    }else{
                        long dateEndSec = customer.getDateEndInscription().getTime();
                        long dateEndSecEdit = dateEndSec + nbDay * 86400000;
                        Date dateEnd = new Date(dateEndSecEdit);
                        customer.setDateEndInscription(dateEnd);
                    }
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
    private void deleteCustomerFromCourseAction (){
        System.out.println("-------- supprimer un client d'un cours --------");
        System.out.println("id du client :");
        int idCustomer = scanner.nextInt();
        System.out.println("id du cours :");
        int idCourse = scanner.nextInt();

        Customer customer = customerService.findById(idCustomer);
        Course course = courseService.findById(idCourse);

        if(customer != null && course != null){
            if(course.removeCustomer(customer)){
                customer.removeCourse(course);
                if(courseService.update(course)){
                    System.out.println("client suprimer du cours ");
                }else{
                    System.out.println("erreur lors de la supression");
                }
            }else{
                System.out.println("le client n'etait pas inscrit au cours");
            }
        }else{
            System.out.println("erreure lors de la recuperation client ou cours");
        }
    }

    //gestion Course
    private void addCourseAction (){
        try{
            System.out.println("-------- ajout d'un cours --------");
            System.out.println("intitulé du cours :");
            String title = scanner.nextLine();

            System.out.println("durée du cours (en minutes):");
            int durationMin = scanner.nextInt();
            scanner.nextLine();

            System.out.println("date du cours (dd/MM/yyyy)");
            Date date = getDate();
            if(date == null){
                return;
            }

            System.out.println("nombre de places :");
            int place = scanner.nextInt();
            scanner.nextLine();

            Course course = new Course(title,durationMin,date,place);
            if(courseService.create(course)){
                System.out.println("le cours a bien ete crée");
            }else{
                System.out.println("erreure lors de la creation du cours");
            }
        }catch (InputMismatchException e){
            System.out.println("entrer une valeur valide");
        }
    }
    private void deleteCourseAction(){
        try {
            System.out.println("-------- suppresion d'un cours --------");
            System.out.println("id du cours :");
            int id = scanner.nextInt();
            scanner.nextLine();

            Course course = courseService.findById(id);
            if (course != null) {
                if (courseService.delete(course)) {
                    System.out.println("cours supprimer");
                } else {
                    System.out.println("erreure lors de la suppresion du cours");
                }
            } else {
                System.out.println("erreure lors de la recuperation du cours");
            }
        } catch (InputMismatchException e) {
            System.out.println("entrer une valeure correcte");
        }
    }
    private void editCourseAction (){
        try {
            System.out.println("-------- Moddification d'un cours --------");
            System.out.println("id du cours a moddifier :");
            int id = scanner.nextInt();
            scanner.nextLine();

            Course course = courseService.findById(id);
            if (course != null) {
                System.out.println("intitulé ( "+course.getTitle()+" ) :");
                String title = scanner.nextLine();
                course.setTitle(title);

                System.out.println("durée ( "+course.getDuration()+" ) :");
                int duration = scanner.nextInt();
                scanner.nextLine();
                course.setDuration(duration);

                System.out.println("date ( "+ course.getDate().toString()+" )(dd/MM/yyyy) :");
                Date date = getDate();
                if(date !=null){
                    course.setDate(date);
                }

                if (courseService.update(course)) {
                    System.out.println("cours modifié");
                } else {
                    System.out.println("erreure lors de la modification");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("entrer une valeur correcte");
        }
    }
    private void addCustomerToCourseAction (){
        try{
            System.out.println("-------- ajout d'un Client a un cours --------");
            System.out.println("id du client :");
            int idCustomer = scanner.nextInt();
            System.out.println("id du cours :");
            int idCourse = scanner.nextInt();

            Customer customer = customerService.findById(idCustomer);
            Course course = courseService.findById(idCourse);
            if(customer != null && course!= null){
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                if(!course.getDate().before( formatter.parse( formatter.format(new Date()) ) )){
                    if(course.getDate().before(customer.getDateEndInscription())){
                        if(course.addCustomer(customer)){
                            customer.addCourse(course);
                            if(customerService.update(customer) && courseService.update(course)){
                                System.out.println("client ajouté au cours");
                            }else{
                                System.out.println("erreure lors de l'ajout");
                            }
                        }else {
                            System.out.println("le cours n'as plus de place");
                        }
                    }else{
                        System.out.println("periode d'abomment expiré a la date de se cours");
                    }
                }else{
                    System.out.println("veuillez choisir un cours qui n'est pas deja passé");
                }
            } else{
                System.out.println("client ou cours incorrecte");
            }
        }catch(InputMismatchException e){
            System.out.println("entrer une valeur valide");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private Date getDate (){
        String dateStr = scanner.nextLine();
        Date date;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
        }catch (Exception e){
            date = new Date();
        }
        if(date.before(new Date())){
            System.out.println("entrer une date valide ");
            return null;
        }
        return date;
    }

    // gestion des coach
    private void addCoachAction (){
        try {
            System.out.println("-------- ajout d'un client --------");
            System.out.println("prenom :");
            String firstName = scanner.nextLine();
            System.out.println("nom :");
            String lastName = scanner.nextLine();
            System.out.println("sport :");
            String sport = scanner.nextLine();

            Coach coach = new Coach(firstName,lastName,sport);
            if(coachService.create(coach)){
                System.out.println("coach crée");
            }else{
                System.out.println("erreure lors de la creation");
            }

        } catch (InputMismatchException e) {
            System.out.println("entrer une valeure correcte");
        }
    }
    private void deleteCoachAction(){
        try {
            System.out.println("-------- suppresion d'un coach --------");
            System.out.println("id du coach :");
            int id = scanner.nextInt();
            scanner.nextLine();

            Coach coach = coachService.findById(id);
            if (coach != null) {
                if (coachService.delete(coach)) {
                    System.out.println("coach supprimer");
                } else {
                    System.out.println("erreure lors de la suppresion du coach");
                }
            } else {
                System.out.println("erreure lors de la recuperation du coach");
            }
        } catch (InputMismatchException e) {
            System.out.println("entrer une valeure correcte");
        }
    }
    private void editCoachAction (){
        try {
            System.out.println("-------- Moddification d'un coach --------");
            System.out.println("id du coach a moddifier :");
            int id = scanner.nextInt();
            scanner.nextLine();

            Coach coach = coachService.findById(id);
            if (coach != null) {
                System.out.println("nom ( " + coach.getLastName() + " ) :");
                String lastName = scanner.nextLine();
                coach.setLastName(lastName);

                System.out.println("prenom ( " + coach.getFirstName() + " ) :");
                String firstName = scanner.nextLine();
                coach.setFirstName(firstName);

                System.out.println("sport ( "+coach.getSports()+" ) :");
                String sport = scanner.nextLine();
                coach.setSports(sport);

                if(coachService.update(coach)){
                    System.out.println("coach modifié");
                }else{
                    System.out.println("erreure lors de la modification");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("entrer une valeur correcte");
        }
    }
    private void addCoachToCourseAction (){
        try{
            System.out.println("-------- ajout d'un coach a un cours --------");
            System.out.println("id du coach :");
            int idCustomer = scanner.nextInt();
            System.out.println("id du cours :");
            int idCourse = scanner.nextInt();

            Coach coach = coachService.findById(idCustomer);
            Course course = courseService.findById(idCourse);
            if(coach != null && course!= null){
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                if(!course.getDate().before( formatter.parse( formatter.format(new Date()) ) )){
                    coach.addCourse(course);
                    if(coachService.update(coach) ){
                        System.out.println("coach ajouté au cours");
                    }else{
                        System.out.println("erreure lors de l'ajout");
                    }
                }else{
                    System.out.println("veuillez choisir un cours qui n'est pas deja passé");
                }
            } else{
                System.out.println("coach ou cours incorrecte");
            }
        }catch(InputMismatchException e){
            System.out.println("entrer une valeur valide");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    private void deleteCoachFromCourseAction (){
        System.out.println("-------- supprimer un coach d'un cours --------");
        System.out.println("id du coach :");
        int idCustomer = scanner.nextInt();
        System.out.println("id du cours :");
        int idCourse = scanner.nextInt();

        Coach coach = coachService.findById(idCustomer);
        Course course = courseService.findById(idCourse);

        if(coach != null && course != null){
            if(coach.removeCourse(course)){
                if(coachService.update(coach)){
                    System.out.println("coach suprimer du cours ");
                }else{
                    System.out.println("erreur lors de la supression");
                }
            }else{
                System.out.println("le coach n'etait pas inscrit au cours");
            }
        }else{
            System.out.println("erreure lors de la recuperation coach ou cours");
        }
    }

    //affichage
    private void showAllcourseAction (){
        System.out.println("-------- afficher les cours a venir --------");
        courseService.findCoursebyDate().forEach(System.out::println);
    }
    private void showCourseCustomerAction (){
        System.out.println("-------- afficher les cours a venir d'un client --------");
        System.out.println("id du client :");
        int id = scanner.nextInt();
        scanner.nextLine();

        Customer customer =customerService.findById(id);
        if(customer != null){
            Date today = getTodayDate();
            if(today != null){
                for (Course c: customer.getCourses()) {
                    if(!c.getDate().before(today)){
                        System.out.println(c);
                    }
                }
            }else{
                System.out.println("probleme sur la recuperation de la date");
            }
        }
    }
    private void showCourseCoachAction (){
        System.out.println("-------- afficher les cours a venir d'un coach --------");
        System.out.println("id du coach :");
        int id = scanner.nextInt();
        scanner.nextLine();

        Coach coach =coachService.findById(id);
        if(coach != null){
            Date today = getTodayDate();
            if(today != null){
                for (Course c: coach.getCourses()) {
                    if(!c.getDate().before(today)){
                        System.out.println(c);
                    }
                }
            }else{
                System.out.println("probleme sur la recuperation de la date");
            }
        }
    }
    private Date getTodayDate (){
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date today = formatter.parse( formatter.format(new Date()) );
            return today;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
