import Tests.Test;
import Client.Client;

public class Main {
    public static void main(String argv[]) {
        Test.startTests();
 
        Client c = new Client();
        c.startSimulation();
    }
}