package com.formation.app;

import com.formation.app.dao.DaoFactory;
import com.formation.app.dao.jdbc.JdbcPlaceDao;
import com.formation.app.dao.jdbc.JdbcTripDao;
import com.formation.app.model.Place;
import com.formation.app.model.Trip;

import java.text.BreakIterator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        interaction();
    }

     private static void interaction() {
        boolean open = true;
        switch (printMenu()) {
            case 1 -> addPlace();
            case 2 -> findPlaceAll();
            case 3 -> editPlace();
            case 4 -> removePlace();
            case 8 -> open = false;
            default -> System.out.println("Selection inconnue, veuillez réessayer");
        }
        if(open) {
            System.out.println();
            interaction();
        }
    }
    private static int printMenu() {
        menu();
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextInt()) {
            return scan.nextInt();
        } else {
            System.out.println("Saisie incorrecte");
            System.out.print("- ");
            return printMenu();
        }
    }
    private static void menu(){
        System.out.println("Welcome aboard !");
        System.out.println("What do you want to do?");
        System.out.println("1 - Add a Place");
        System.out.println("2 - Find Place");
        System.out.println("3 - Edit Place");
        System.out.println("4 - Remove a Place");
        System.out.println("5 - Add Trip");
        System.out.println("6 - Find Trip");
        System.out.println("7 - Remove Trip");
        System.out.println("8 - Quit");
        System.out.print("- ");
    }
    private static void addPlace(){
        System.out.println("Name :");
        Scanner scan = new Scanner(System.in);
        System.out.print("- ");
        String name = scan.next();
        JdbcPlaceDao jdbcPlaceDao = DaoFactory.GetPlaceDao();
        Long  idPlace = jdbcPlaceDao.createPlace(new Place(name));
        System.out.println("Place added with the id :" + idPlace);

    }
    private static void  editPlace(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the id of the place  :");
        System.out.print("- ");
        if (! scan.hasNextLong()) {
            editPlace();
        }
        Long idPlace =  scan.nextLong();
        findPlaceById(idPlace);
        System.out.println("This place. Are you sure you want to modify. [yes/no]");
        String rpt = scan.next();
        if(rpt.equalsIgnoreCase("yes")){
            System.out.println("New name Place");
            String name = scan.next();
            JdbcPlaceDao jdbcPlaceDao = DaoFactory.GetPlaceDao();
            jdbcPlaceDao.updatePlace(new Place(idPlace,name));
            System.out.println("****** the information has been modified*****");

        }
    }
    private static void  removePlace(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the id of the place  :");
        System.out.print("- ");
        if (! scan.hasNextLong()) {
            removePlace();
        }
        Long idPlace =  scan.nextLong();
        findPlaceById(idPlace);
        System.out.println("This place be removed with it. Are you sure you want to remove it. [yes/no]");
        String rpt = scan.next();
        if(rpt.equalsIgnoreCase("yes")){
            JdbcPlaceDao jdbcPlaceDao = DaoFactory.GetPlaceDao();
            jdbcPlaceDao.removePlace(idPlace);
            System.out.println("****** information Supprimée*****");
        }
    }
    private static void findPlaceById(Long idPlace){
        JdbcPlaceDao jdbcPlaceDao = DaoFactory.GetPlaceDao();
        Place place = jdbcPlaceDao.findPlaceById(idPlace);
        System.out.println("Place :" + place.getName());
    }
    private static void findPlaceAll(){
        JdbcPlaceDao jdbcPlaceDao = DaoFactory.GetPlaceDao();
        List<Place> listplace = jdbcPlaceDao.findAllPlace();

        for (Place f: listplace) {
            System.out.println("Place id :" + f.getId() + "  name :" + f.getName());
        }
        System.out.println("......");

    }


    //////TRIP//////
    private static void addTrip(){
        System.out.println("Name :");
        Scanner scan = new Scanner(System.in);
        System.out.print("- ");
        String name = scan.next();

      /*  Long idTrip = null;
        JdbcTripDao jdbcTripDao = DaoFactory.GetTripDao();
        idTrip = jdbcTripDao.createTrip(new Trip(idDepart,idArrive,prix));
        System.out.println("Trip added");
       findTripById(idTrip);*/

    }
    private static void  editTrip(Long idTrip,Long idDepart, Long idArrive, Float prix){
        System.out.println("****** information Actuelle*****");
        findTripById(idTrip);
        JdbcTripDao jdbcTripDao = DaoFactory.GetTripDao();
        jdbcTripDao.updateTrip(new Trip(idTrip,idDepart,idArrive,prix));
        System.out.println("****** information Changée*****");
        findTripById(idTrip);
    }
    private static void  removeTrip(Long idTrip){
        JdbcTripDao jdbcTripDao = DaoFactory.GetTripDao();
        jdbcTripDao.removeTrip(idTrip);
        System.out.println("****** information Supprimée*****");

    }


    private static void findTripById(Long afloat){

        JdbcTripDao jdbcTripDao = DaoFactory.GetTripDao();
        Trip trip = jdbcTripDao.findTripById(afloat);
        System.out.println("Trip id :" + trip.getId());
        System.out.println("Trip Depart :" + trip.getNomDepart());
        System.out.println("Trip Arrivée :" + trip.getNomArrivee());

    }
}
