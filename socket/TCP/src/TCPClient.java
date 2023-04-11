import java.io.*;
import java.net.*;
import java.util.Scanner;

class TCPClient {
    public static void main(String argv[]) throws Exception
    {
        String sentence;
        String modifiedSentence = null;
        int access = 0;             // 0 --> access denied;     1 --> access granted
        String sendMessage;

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
                    modifiedSentence = null;
                } else {
                    System.out.println("Access Denied - Username/Password Incorrect");
                    modifiedSentence = null;
                }
            } else if (choice == 1 && access == 1) {   // printout the list of usernames from the server
                outToServer.writeBytes("list" + '\n');  // send "list" to server
                modifiedSentence = inFromServer.readLine();
                System.out.println("user list get from server: " + modifiedSentence);
            } else if (choice == 2 && access == 1) {    // sent a message to server
                System.out.println("Please type the user's name you want to sent message: ");
                String name = input.nextLine();
                System.out.println("Please type your message detail: ");
                String message = input.nextLine();
                sendMessage = "sent " + name + " " + message;
                outToServer.writeBytes(sendMessage + '\n');

                modifiedSentence = inFromServer.readLine();


            } else if (choice == 3 && access == 1) {    // get message from server
                outToServer.writeBytes("get" + '\n');  // send "list" to server
                modifiedSentence = inFromServer.readLine();


            } else if (choice == 4) {
                clientSocket.close();
                continue;
            } else {
                System.out.println("Access Denied - Username/Password Incorrect");
            }

        }
    }
}
