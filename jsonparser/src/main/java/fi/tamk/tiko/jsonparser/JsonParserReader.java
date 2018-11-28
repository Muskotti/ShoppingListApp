package fi.tamk.tiko.jsonparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParserReader {

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

    private String cleanLine(String line) {
        String[] parts = line.split(":");
        String tmpOne = parts[1].replaceAll("\"","");
        String tmpTwo = tmpOne.replace(",","");
        tmpTwo.trim();
        return tmpTwo;
    }
}
