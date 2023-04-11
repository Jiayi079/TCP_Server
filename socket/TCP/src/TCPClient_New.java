import java.io.*;
import java.net.*;

class TCPClient_New {
    public static void main(String argv[]) throws Exception
    {
        String sentence;
        String modifiedSentence;
        int input = 1;

        while (input==1) {
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            Socket clientSocket = new Socket("127.0.0.1", 6789);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));
            System.out.println("0. Connect to the server\n" +
                    "1. Get the user list\n" +
                    "2. Send a message\n" +
                    "3. Get my messages\n" +
                    "4. Exit");
            sentence = inFromUser.readLine();
            outToServer.writeBytes(sentence + '\n');


            modifiedSentence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + modifiedSentence);
            clientSocket.close();
        }






    }
}
