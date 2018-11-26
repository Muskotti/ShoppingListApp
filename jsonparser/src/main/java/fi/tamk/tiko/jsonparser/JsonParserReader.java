package fi.tamk.tiko.jsonparser;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class JsonParserReader {
    public Product readFile(File file) throws IOException {
        Scanner sc = new Scanner(file);
        sc.useDelimiter("\\Z");
        System.out.println(sc.next());
        Product tmp = new Product(0,"asd",1);
        return tmp;
    }
}
