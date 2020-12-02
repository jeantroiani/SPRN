import SPRN.SPRN;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        SPRN sprn = new SPRN();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("You can now start interacting with the SRPN calculator");

        try {
            while (true) {
                String command = reader.readLine();
                sprn.processCommand(command);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

}
