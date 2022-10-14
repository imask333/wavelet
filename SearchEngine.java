import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    List<String> myStrings = new ArrayList<>();
    List<String> newOne = new ArrayList<>();
    

    public String handleRequest(URI url) {
        /*if (url.getPath().equals("/")) {
            return String.format("Number: %d", num);
        } else if (url.getPath().equals("/increment")) {
            num += 1;
            return String.format("Number incremented!");
        } else {*/
            //System.out.println("Path: " + url.getPath());
        if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s")) {
                myStrings.add(parameters[1]);
            }
            return String.format("String added");
        }else if(url.getPath().contains("/search")){
            String[] parameters2 = url.getQuery().split("=");
            for(String str: myStrings){
                if(str.contains(parameters2[1])){
                    newOne.add(str);
                }
            }
            return String.format("Search Results : " + newOne);
           
        }
        return String.format("404 No match");
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
