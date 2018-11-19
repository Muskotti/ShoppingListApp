package fi.tamk.tiko.jsonparser;

import java.util.ArrayList;

/**
 * Jsonparser class
 * Used to make a JSON files
 *
 * @author Toni Vänttinen (toni.vanttinen@cs.tamk.fi)
 * @version V1
 * @since 2018-11-19
 */
public class JsonParser {

    /**
     * Writes a Json object
     *
     * First the method makes String ArrayList where all of the info is stored
     * Next the info is placed into the array from the pro object
     * Lastly a string line is generated from the array
     *
     * @param pro Product class where the info is taken
     * @return A String generated from the ArrayList
     */
    public String writeToJson(Product pro) {
        ArrayList<String> tmp = new ArrayList<>();
        tmp.add("{");
        tmp.add("\"id\":\"" + pro.getId() + "\",");
        tmp.add("\"name\":\""+pro.getName() + "\",");
        tmp.add("\"count\":\"" + pro.getCount() + "\"");
        tmp.add("}");
        StringBuilder sb = new StringBuilder();
        for(String s : tmp){
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Generate start for the JSON file
     *
     * Method that generate the start of the JSON file that contains a Product Array
     *
     * @return A String generated from the ArrayList
     */
    public String start() {
        ArrayList<String> tmp = new ArrayList<>();
        tmp.add("{");
        tmp.add("\"Products\": [");
        StringBuilder sb = new StringBuilder();
        for(String s : tmp){
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Generates the end for the JSON file
     *
     * Method that generates the end for the JSON file that contains Array
     *
     * @return A String generated from the ArrayList
     */
    public String end() {
        ArrayList<String> tmp = new ArrayList<>();
        tmp.add("]");
        tmp.add("}");
        StringBuilder sb = new StringBuilder();
        for(String s : tmp){
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Generates the JSON documentation needed in between two objects in the array
     * @return A String generated from the ArrayList
     */
    public String next() {
        ArrayList<String> tmp = new ArrayList<>();
        tmp.add(",");
        StringBuilder sb = new StringBuilder();
        for(String s : tmp){
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }
}
