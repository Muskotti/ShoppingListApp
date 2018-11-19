package fi.tamk.tiko.jsonparser;

import java.util.ArrayList;

public class JsonParser {
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

    public String toString(Product pro) {
        return this.writeToJson(pro);
    }

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
