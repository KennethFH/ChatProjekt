import java.io.IOException;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) throws IOException
    {
        System.out.println("Type \"client\" to start the client or \"server\" to start the server:");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        switch (s){
            case "server":
                Server server = new Server();
                server.start();
                break;
            case "client":
                Client client = new Client();
                client.start();
                break;
        }
    }
}