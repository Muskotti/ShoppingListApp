package fi.tamk.tiko.jsonparser;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * JsonParserReadertest class
 * Used to test the reading part
 *
 * @author Toni Vänttinen (toni.vanttinen@cs.tamk.fi)
 * @version V1
 * @since 2018-12-17
 */
public class JsonParserReaderTest {

    /**
     * JsonParserReader object that is used to comparisons
     */
    private JsonParserReader test = new JsonParserReader();

    /**
     * Tests the file reader
     *
     * First a file is created
     * Then that files contance is made in to a Product and added to a list
     * Lastly the lists products name is compared
     *
     * @throws IOException run error
     */
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

    /**
     * Tests if the line cleaning is working, by deleting the unnecessary text
     */
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