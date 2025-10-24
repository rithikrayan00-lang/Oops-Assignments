import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 5000;  // Server port number
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started. Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client connected.");

            // Create input stream to receive file
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter("received.txt"));

            String line;
            while ((line = in.readLine()) != null) {
                fileWriter.write(line);
                fileWriter.newLine();
            }

            System.out.println("File received successfully and saved as 'received.txt'.");

            fileWriter.close();
            in.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";  // Server IP (use actual IP if on different machine)
        int port = 5000;

        try (Socket socket = new Socket(serverAddress, port)) {
            System.out.println("Connected to server.");

            // Read the file to send
            BufferedReader fileReader = new BufferedReader(new FileReader("send.txt"));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String line;
            while ((line = fileReader.readLine()) != null) {
                out.write(line);
                out.newLine();
            }

            System.out.println("File sent successfully.");

            fileReader.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
