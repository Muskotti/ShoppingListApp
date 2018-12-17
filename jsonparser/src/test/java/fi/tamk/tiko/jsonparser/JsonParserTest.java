package fi.tamk.tiko.jsonparser;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * JsonParserTest class
 * Used to test the writing part
 *
 * @author Toni Vänttinen (toni.vanttinen@cs.tamk.fi)
 * @version V1
 * @since 2018-12-17
 */
public class JsonParserTest {

    /**
     * JsonParser object that is used to comparisons
     */
    private JsonParser test = new JsonParser();

    /**
     * Compares that the made product is equal to the real thing
     */
    @Test
    public void writeToJson() {
        ArrayList<String> tmp = new ArrayList<>();
        tmp.add("{");
        tmp.add("\"id\":\"" + "0" + "\",");
        tmp.add("\"name\":\""+ "asd" + "\",");
        tmp.add("\"count\":\"" + "1" + "\"");
        tmp.add("}");
        StringBuilder sb = new StringBuilder();
        for(String s : tmp){
            sb.append(s);
            sb.append("\n");
        }
        Product pro = new Product(0,"asd",1);
        Assert.assertEquals(sb.toString(),test.writeToJson(pro));
    }

    /**
     * Tests if the start of the json file is correct
     */
    @Test
    public void start() {
        ArrayList<String> tmp = new ArrayList<>();
        tmp.add("{");
        tmp.add("\"Products\": [");
        StringBuilder sb = new StringBuilder();
        for(String s : tmp){
            sb.append(s);
            sb.append("\n");
        }
        Assert.assertEquals(sb.toString(),test.start());
    }

    /**
     * Tests if the end of the json file is correct
     */
    @Test
    public void end() {
        ArrayList<String> tmp = new ArrayList<>();
        tmp.add("]");
        tmp.add("}");
        StringBuilder sb = new StringBuilder();
        for(String s : tmp){
            sb.append(s);
            sb.append("\n");
        }
        Assert.assertEquals(sb.toString(),test.end());
    }

    /**
     * Tests if the next part of the json file is correct
     */
    @Test
    public void next() {
        ArrayList<String> tmp = new ArrayList<>();
        tmp.add(",");
        StringBuilder sb = new StringBuilder();
        for(String s : tmp){
            sb.append(s);
            sb.append("\n");
        }
        Assert.assertEquals(sb.toString(),test.next());
    }
}