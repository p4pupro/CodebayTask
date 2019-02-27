import models.User;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import utils.FileManagement;
import utils.UserManagement;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Author: Domingo Pérez
 * GitHub: https://github.com/p4pupro
 */

public class Interfaces {

    final static Logger logger = Logger.getLogger(FileManagement.class);

    public static void main(String[] args) {

        //PropertiesConfigurator is used to configure logger from properties file
        PropertyConfigurator.configure("src/main/resources/log4j.properties");

        UserManagement userManagement = new UserManagement();


        Scanner sn = new Scanner(System.in);
        boolean exit = false;
        int option; //Save user options

        while (!exit) {

            System.out.println("1. Get users active.");
            System.out.println("2. Get city that start with.");
            System.out.println("3. Get users by creating date.");
            System.out.println("4. Add user.");
            System.out.println("5. Exit.");

            try {

                System.out.println("Choose one option");
                option = sn.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Users Active");
                        List<User> userActive = userManagement.getActiveUsers();
                        userActive.forEach(System.out::println);
                        break;
                    case 2:
                        System.out.println("City");
                        String cityStartWith = sn.next();
                        System.out.println(userManagement.getCityStartWith(cityStartWith));
                        break;
                    case 3:
                        System.out.println("Users by creating date");
                        System.out.println("Write 1 to Desc or 2 to Asc");
                        int election = sn.nextInt();
                        List<User> orderListUsersByDate = userManagement.getListUserByCreatingDate(election);
                        orderListUsersByDate.forEach(System.out::println);
                        break;
                    case 4:
                        System.out.println("--Add user--");
                        Date now = new Date();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX"); // Format Date
                        String date = simpleDateFormat.format(now);

                        User user = new User();
                        System.out.println("Set name:");
                        String name = sn.next();
                        user.setName(name);
                        System.out.println("Set surname:");
                        String surname = sn.next();
                        user.setSurname(surname);
                        System.out.println("Set email:");
                        String email = sn.next();
                        user.setEmail(email);
                        System.out.println("Set true to active and false to inactive:");
                        boolean active = sn.nextBoolean();
                        user.setActive(active);
                        System.out.println("Set city:");
                        String city = sn.next();
                        user.setCity(city);
                        user.setCreationDate(date);
                        userManagement.AddUser(user);
                        break;
                    case 5:
                        logger.info("Leaving the program");
                        exit = true;
                        break;
                    default:
                        System.out.println("Just numbers between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                logger.error(e.getMessage() + " -- " + e.getStackTrace());
                System.out.println("You should insert a number.");
                sn.next();
            }
        }
    }
}
