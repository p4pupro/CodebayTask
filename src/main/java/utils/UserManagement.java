package utils;

import com.google.gson.Gson;
import models.User;
import org.apache.log4j.Logger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Author: Domingo Pérez
 * GitHub: https://github.com/p4pupro
 */

public class UserManagement {

    final static Logger logger = Logger.getLogger(UserManagement.class);
    FileManagement rf = new FileManagement();
    Gson gson = new Gson();

    /**
     * Find users with active status true.
     * @return List<User>
     */
    public List<User> getActiveUsers() {
        List<User> listUserActives = null;
        try {
            String jsonData = rf.getData();
            List<User> userList = rf.jsonToEntity(jsonData);
            listUserActives = userList.stream().filter(x -> x.isActive()).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage() + " -- " + e.getStackTrace());
        }
        return listUserActives;
    }

    /**
     * Listed cities that start with "cityStartWith" value
     * @param cityStartWith
     * @return List<String>
     */
    public List<String> getCityStartWith(String cityStartWith) {
        List<String> cities = null;
        try {
            String data = rf.getData();
            List<User> listUsers = rf.jsonToEntity(data);

            cities = listUsers.stream().map(user -> user.getCity()).filter(city -> city.startsWith(cityStartWith)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error(e.getMessage() + " -- " + e.getStackTrace());
        }
        return cities;
    }

    /**
     * Listed users by creating date, using someones list and HashMap,
     * to avoid lose the reference user.
     * @param order
     * @return
     */
    public List<User> getListUserByCreatingDate(int order) {
        String data = rf.getData();
        List<User> listUsers = rf.jsonToEntity(data);
        List<Date> listDate = new ArrayList<>();
        List<String> orderUserListByEmail = new ArrayList<>();
        List<User> orderUserListByDate = new ArrayList<>();
        HashMap<String, Date> userWithDate = new HashMap<>();
        listUsers.stream().forEach( user -> {
            Date userDate = castingDate(user.getCreationDate());
            userWithDate.put(user.getEmail(), userDate);
            listDate.add(castingDate(user.getCreationDate()));
        });

        List<Date> orderListDate = shortingDate(listDate, order);

        orderListDate.forEach( orderDate -> {
            userWithDate.forEach( (k,v) -> {
                if(orderDate.equals(v)){
                    orderUserListByEmail.add(k);
                }
            });

        });

        orderUserListByEmail.forEach( email -> {
            listUsers.stream().forEach( user -> {
                if(email.equals(user.getEmail())) {
                    orderUserListByDate.add(user);
                }
            });
        });
        return orderUserListByDate;
    }

    /**
     * Cast string value to Date
     * @param dateString;
     * @return
     */
    public Date castingDate(String dateString) {
        List<Date> listDate = new ArrayList<>();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = null;
            try {
                date = inputFormat.parse(dateString);
            } catch (ParseException e) {
                logger.error(e.getMessage() + " -- " + e.getStackTrace());
            }
       return date;
    }

    /**
     * Shorting list of Date Desc or Asc
     * @param listDate
     * @param order
     * @return
     */
    public List<Date> shortingDate(List<Date> listDate, int order) {
        try {
            if(order == 1) {  // Desc
                Collections.sort(listDate);
                return listDate;

            } else if( order == 2) { // Asc
                Collections.sort(listDate, Collections.reverseOrder());
                return listDate;
            }
        } catch (Exception e) {
            logger.error(e.getMessage() + " -- " + e.getStackTrace());
        }
        return listDate;
    }

    /**
     * Add user to file db.txt, in json format
     * @param user
     */
    public void AddUser(User user) {
        try {
            String oldData = rf.getData();

            List<User> listUsers = rf.jsonToEntity(oldData);
            List<User> listNewUsers = new ArrayList<>();
            listUsers.forEach( oldUser -> {
                listNewUsers.add(oldUser);
            });
            listNewUsers.add(user);
            User [] usersArray = listNewUsers.stream().toArray(User[]::new);
            String jsonUser = gson.toJson(usersArray);
            rf.writeDataInFile(jsonUser);
        }catch (Exception e) {
            logger.error(e.getMessage() + " -- " + e.getStackTrace());
        }
    }

}
