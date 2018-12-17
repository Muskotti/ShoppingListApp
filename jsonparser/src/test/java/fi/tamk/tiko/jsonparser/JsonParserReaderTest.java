package fi.tamk.tiko.jsonparser;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonParserReaderTest {

    private JsonParserReader test = new JsonParserReader();

    @Test
    public void readFile() throws IOException {
        PrintWriter writer = new PrintWriter("test.txt", "UTF-8");
        writer.println("" + "{\"Products\": [" +
                "{\n" +
                "\"id\":\"0\",\n" +
                "\"name\":\"qwe\",\n" +
                "\"count\":\"6\"\n" +
                "}\n" +
                "]}");
        writer.close();
        File file = new File("test.txt");
        List<Product> tmpList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        List<String> countList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.contains("name")) {
                    nameList.add(test.cleanLine(line));
                } else if (line.contains("count")) {
                    countList.add(test.cleanLine(line));
                }
            }
        }
        for(int i = 0; i < nameList.size(); i++) {
            tmpList.add(new Product(i,nameList.get(i),Integer.parseInt(countList.get(i))));
        }
        List<Product> actual = tmpList;
        List<Product> expected = test.readFile(file);
        Assert.assertEquals(actual.get(0).getName(),expected.get(0).getName());
    }

    @Test
    public void cleanLine() {
        String line = "{\n" +
                "\"id\":\"0\",\n" +
                "\"name\":\"qwe\",\n" +
                "\"count\":\"6\"\n" +
                "}\n";
        String[] parts = line.split(":");
        String tmpOne = parts[1].replaceAll("\"","");
        String tmpTwo = tmpOne.replace(",","");
        tmpTwo.trim();
        Assert.assertEquals(tmpTwo, test.cleanLine(line));
    }
}