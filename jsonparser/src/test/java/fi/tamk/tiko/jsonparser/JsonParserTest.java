package fi.tamk.tiko.jsonparser;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;


public class JsonParserTest {

    private JsonParser test = new JsonParser();
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