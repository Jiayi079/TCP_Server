import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

class TCPServer {
    public static void main(String argv[]) throws Exception
    {
        String clientSentence;
        String capitalizedSentence = null;

        HashMap<String, String> data = new HashMap<String, String>();

        data.put("alice", "1234");
        data.put("bob", "5678");

        for ( String key : data.keySet() ) {

            String[] key = new String[];
            capitalizedSentence = capitalizedSentence + key + "; ";
        }


        String[] aliceMessage = new String[100];
        String[] bobMessage = new String[100];

        ServerSocket welcomeSocket = new ServerSocket(6789);
        System.out.println("SERVER is running ... ");

        while(true) {
            Socket connectionSocket = welcomeSocket.accept();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            clientSentence = inFromClient.readLine();
            System.out.println("FROM CLIENT: " + clientSentence);

            String[] split = clientSentence.split(" ");
            // check if user input arrive to server correctly
//            for (String a : split) {
//                System.out.println(a);
//            }

            // check user exist
            // System.out.println(split[0]);
            if(data.containsKey(split[0].toLowerCase())) {
                // System.out.println(split[0] + " is in our dataset");
                // String a = data.get(split[0]);

                // check if password is correct
                if (split[1].equals(data.get(split[0]))) {
                    capitalizedSentence = "true".toUpperCase() + '\n';
                } else {
                    capitalizedSentence = "false".toUpperCase() + '\n';
                }
            } else if (clientSentence.equalsIgnoreCase("list")) { // check if user need the list of the users
                // capitalizedSentence = String.valueOf(data) + '\n';
                // System.out.println(capitalizedSentence);
                for ( String key : data.keySet() ) {
                    capitalizedSentence = capitalizedSentence + key + "; ";
                }
            } else if (split[0].equalsIgnoreCase("sent")) {   // receive message from the client
                if (data.containsKey(split[1].toLowerCase())) { // check if client sent the message to the correct user
                    if(split[1].equalsIgnoreCase("")) {

                    }
                } else {
                    // return doesn't have this user's name to the client
                }

            } else if (clientSentence.equalsIgnoreCase("get")) {

            }

            outToClient.writeBytes(capitalizedSentence + '\n');
            capitalizedSentence = "";       // clear everytime for the loop

//             capitalizedSentence = clientSentence.toUpperCase() + '\n';
//             outToClient.writeBytes(capitalizedSentence);
        }
    }
}
