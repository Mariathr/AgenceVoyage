package com.formation.app;

import com.formation.app.dao.DaoFactory;
import com.formation.app.dao.jdbc.JdbcPlaceDao;
import com.formation.app.dao.jdbc.JdbcTripDao;
import com.formation.app.model.Place;
import com.formation.app.model.Trip;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
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
            case 5 -> addTrip();
            case 6 -> findTripById();
            case 7 -> removeTrip();
            case 8 -> findTripAll();
            case 9 -> open = false;
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
        System.out.println("2 - Find All Place");
        System.out.println("3 - Edit Place");
        System.out.println("4 - Remove a Place");
        System.out.println("5 - Add Trip");
        System.out.println("6 - Find Trip");
        System.out.println("7 - Remove Trip");
        System.out.println("8 - Find All Trip");
        System.out.println("9 - Quit");
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
        Long idPlace = validationPlace("");
        Scanner scan = new Scanner(System.in);
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
        Long idPlace = validationPlace("");

        System.out.println("All trip with This place be removed with it. Are you sure you want to remove it. [yes/no]");
        String rpt = scan.next();
        if(rpt.equalsIgnoreCase("yes")){
            JdbcPlaceDao jdbcPlaceDao = DaoFactory.GetPlaceDao();
            removeTripPlace(idPlace);
            jdbcPlaceDao.removePlace(idPlace);
            System.out.println("****** information Place Supprimée*****");
        }
    }
    private static Place findPlaceById(Long idPlace){
        JdbcPlaceDao jdbcPlaceDao = DaoFactory.GetPlaceDao();
        Place place = jdbcPlaceDao.findPlaceById(idPlace);
        System.out.println("Place :" + place.getName());
        return place;
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


    private static void addTrip() {
        Long idDeparture = validationPlace("Departure");
        Long idDestination = validationPlace("Destination");
        float prix = valitationPrix();

        JdbcTripDao jdbcTripDao = DaoFactory.GetTripDao();
        Long idTrip = jdbcTripDao.createTrip(new Trip(idDeparture, idDestination, prix));
        System.out.println("Trip added with the ID =" + idTrip);
    }
    private static void  editTrip(Long idTrip,Long idDepart, Long idArrive, Float prix){
        System.out.println("****** information Actuelle*****");
       // findTripById(idTrip);
        JdbcTripDao jdbcTripDao = DaoFactory.GetTripDao();
        jdbcTripDao.updateTrip(new Trip(idTrip,idDepart,idArrive,prix));
        System.out.println("****** information Changée*****");
       // findTripById(idTrip);
    }
    private static void  removeTrip(){
        Scanner scan = new Scanner(System.in);
        Long idTrip= validationTrip();

        System.out.println("This place be removed with it. Are you sure you want to remove it. [yes/no]");
        String rpt = scan.next();
        if(rpt.equalsIgnoreCase("yes")) {
            JdbcTripDao jdbcTripDao = DaoFactory.GetTripDao();
            jdbcTripDao.removeTrip(idTrip);
            System.out.println("****** information Supprimée*****");
        }
    }
    private static void  removeTripPlace(Long idPlace){
            JdbcTripDao jdbcTripDao = DaoFactory.GetTripDao();
            if(jdbcTripDao.removeTripPlace(idPlace)){
                System.out.println("****** information Trip Supprimée*****");
            }
    }

    private static Trip findTripById(){
        Long idTrip= validationTrip();

        JdbcTripDao jdbcTripDao = DaoFactory.GetTripDao();
        Trip trip = jdbcTripDao.findTripById(idTrip);
        System.out.println("Trip id :" + trip.getId());
        System.out.println("Trip Depart :" + trip.getNomDepart());
        System.out.println("Trip Arrivée :" + trip.getNomArrivee());
        return trip;
    }
    private static Trip findTripById(Long idTrip){
        JdbcTripDao jdbcTripDao = DaoFactory.GetTripDao();
        Trip trip = jdbcTripDao.findTripById(idTrip);
        return trip;
    }

    private static void findTripAll() {
        JdbcTripDao jdbcTripDao = DaoFactory.GetTripDao();
        List<Trip> listTrip = jdbcTripDao.findAllTrip();

        for (Trip f : listTrip) {
            System.out.println("Trip id :" + f.getId() + "  Depart :" + f.getNomDepart() + "  Arrivée :" + f.getNomArrivee());
        }
        System.out.println("......");
    }
    ////
    private static Long  validationPlace(String msg){
        Long idPlace =null;
        Scanner scan = new Scanner(System.in);
        System.out.println(msg + " Please enter the id of the place  :");
        System.out.print("- ");
        if (! scan.hasNextLong()) {
            validationPlace(msg);
        }
        idPlace =  scan.nextLong();
        Place place = findPlaceById(idPlace);
        if (place.getId()==null){
            System.err.println("Id not correct");
            validationPlace(msg);
        }
        return idPlace;
    }
    private static Long  validationTrip(){
        Long idTrip =null;
        Scanner scan = new Scanner(System.in);
        System.out.println( " Please enter the id of the Trip  :");
        System.out.print("- ");
        if (! scan.hasNextLong()) {
            validationTrip();
        }
        idTrip =  scan.nextLong();
        Trip trip = findTripById(idTrip);
        if (trip.getId()==null){
            System.err.println("Id not correct");
            validationTrip();
        }
        return idTrip;
    }
    private static float valitationPrix() {
        Float prix=null;
        Scanner scan = new Scanner(System.in);
        System.out.println(" Price :");
        System.out.print("- ");
        if (scan.hasNextFloat()) {
            prix = scan.nextFloat();
        }else{
            valitationPrix();
        }
        return prix;
    }
     /*private static void valitationPrix() {
        Boolean valor = true;
        do {
            System.out.println(" Price :");
            System.out.print("- ");
            if (scan.hasNextFloat()) {
                valor = false;
            }
            scanValeur = scan.next();
        } while (valor);

    }*/
}
