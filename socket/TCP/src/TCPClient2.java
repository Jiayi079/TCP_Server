import java.io.*;
import java.net.*;
import java.util.Scanner;

class TCPClient2 {
    public static void main(String argv[]) throws Exception
    {
        String sentence;
        String modifiedSentence;
        int access = 0;             // 0 --> access denied;     1 --> access granted

        int choice = -1;
        while(choice != 4) {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            Socket clientSocket = new Socket("127.0.0.1", 6789);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));

            System.out.println("0. Connect to the server\n" +
                    "1. Get the user list\n" +
                    "2. Send a message\n" +
                    "3. Get my messages\n" +
                    "4. Exit");
            System.out.println("input your choice: ");
            Scanner input = new Scanner(System.in);
            choice = input.nextInt();
            if (choice == 0) {
                System.out.println("stablishes a TCP connection with the server.");
                System.out.println("Input your username: ");
                input.nextLine();
                String name = input.nextLine();

                System.out.println("Input your password: ");
                String password = input.nextLine();

                sentence = name + " " + password;
                // sentence = inFromUser.readLine();
                outToServer.writeBytes(sentence + '\n');
                modifiedSentence = inFromServer.readLine();

                // System.out.println("FROM SERVER: " + modifiedSentence);

                // check returns message is true or false
                if(modifiedSentence.equalsIgnoreCase("true")) {
                    System.out.println("Access Granted");
                    access = 1;
                } else {
                    System.out.println("Access Denied - Username/Password Incorrect");
                    access = 0;
                }
            } else if (choice == 1) {   // printout the list of usernames from the server
                if(access == 1) {   // user already input username/password correctly

                } else {
                    System.out.println("Access Denied - Username/Password Incorrect");
                    continue;
                }

            } else if (choice == 2) {
                if(access == 1) {   // user already input username/password correctly

                } else {
                    System.out.println("Access Denied - Username/Password Incorrect");
                    continue;
                }

            } else if (choice == 3) {
                if(access == 1) {   // user already input username/password correctly

                } else {
                    System.out.println("Access Denied - Username/Password Incorrect");
                    continue;
                }

            } else if (choice == 4) {
                clientSocket.close();
                continue;
            }

        }
    }
}
