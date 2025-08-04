import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args[0].equals("server")){
            try(Server serverSocket = new Server(5, 50, "10.250.122.65")){
                serverSocket.start();
            }
        }else{
            try (Client client = new Client("10.250.122.65", 50)){
                System.out.println("Enter a message: ");
                Scanner scanner = new Scanner(System.in);
                client.send(scanner.nextLine());
            }
        }
    }
}
