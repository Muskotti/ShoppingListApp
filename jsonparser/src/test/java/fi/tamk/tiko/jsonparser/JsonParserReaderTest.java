package fi.tamk.tiko.jsonparser;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class JsonParserReaderTest {

    private JsonParserReader test = new JsonParserReader();

    @Ignore
    public void readFile() {

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