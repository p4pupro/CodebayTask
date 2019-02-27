package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import models.User;
import com.google.gson.Gson;
import org.apache.log4j.Logger;

/**
 * Author: Domingo Pérez
 * GitHub: https://github.com/p4pupro
 */

public class FileManagement {

    final static Logger logger = Logger.getLogger(FileManagement.class);

    Gson gson = new Gson();

    /**
     * Read json string from db.txt
     * @return String
     */
    public String getData() {
        String jsonData = "";
        {
            try {
                logger.debug("Reading file...");
                jsonData = new String(Files.readAllBytes(Paths.get("db.txt")));
                logger.info("File reading successfull...");
                logger.debug("File reading successfull...");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonData;
    }

    /**
     * Casting json string to java object
     * @param jsonData
     * @return List<User>
     */
    public List<User> jsonToEntity(String jsonData) {
        logger.debug("casting string json to java object");
        User [] users = gson.fromJson(jsonData, User[].class);
        return Arrays.asList(users);
    }

    /**
     * Write json data in file db.txt
     * @param data
     */
    public void writeDataInFile(String data) {
        FileWriter fileWriter = null;
        try {
            logger.debug("Trying to write in file...");
            fileWriter = new FileWriter(new File("db.txt"), false);

            fileWriter.write(data);

            logger.debug("Write in file successfully...");

            logger.debug("Close file...");
            fileWriter.close();
        } catch (IOException e) {
            logger.error(e.getStackTrace() + " - " +  e.getMessage());
        }
    }

}
