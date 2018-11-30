package fi.tamk.tiko.jsonparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JsonParserReader class
 * Used to read Json files
 *
 * @author Toni Vänttinen (toni.vanttinen@cs.tamk.fi)
 * @version V1
 * @since 2018-11-30
 */

public class JsonParserReader {

    /**
     * Reads a Json file and returns a List of products
     *
     * First every line is read from the file and given to be cleaned up
     * Next the line is cleaned up and added to a list
     * Lastly a Product is created from the lines and that product is given to a another list
     * The product list is returned at the end
     *
     * @param file A File that is in json form and used to make a product
     * @return A List of products
     * @throws IOException if file is not there
     */
    public List<Product> readFile(File file) throws IOException {
        List<Product> tmpList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        List<String> countList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.contains("name")) {
                    nameList.add(cleanLine(line));
                } else if (line.contains("count")) {
                    countList.add(cleanLine(line));
                }
            }
        }
        for(int i = 0; i < nameList.size(); i++) {
            tmpList.add(new Product(i,nameList.get(i),Integer.parseInt(countList.get(i))));
        }
        return tmpList;
    }

    /**
     * Cleans up the given String
     *
     * First the line is split at : and lastly all \ and , are replaced with nothing
     *
     * @param line A String that is to be cleaned up
     * @return A cleaned up String
     */
    private String cleanLine(String line) {
        String[] parts = line.split(":");
        String tmpOne = parts[1].replaceAll("\"","");
        String tmpTwo = tmpOne.replace(",","");
        tmpTwo.trim();
        return tmpTwo;
    }
}
