package edu.eci.arem.springboot;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.*;
import java.io.*;

public class HttpServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        Springboot.main(args);
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }


        boolean running = true;
        while (running){
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine;
            boolean firstLine = true;
            String path ="";
            while ((inputLine = in.readLine()) != null) {
                if(firstLine){
                    String[] receive = inputLine.split(" ");
                    path = receive[1];
                }
                firstLine = false;
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }

            outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n"
                    + "<!DOCTYPE html>\n"
                    + getService(path);
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    public static String getService(String path) throws InvocationTargetException, IllegalAccessException {
        System.out.println(path);
        String outputser = "";
        if(Services.datos.containsKey(path)){
            Method metodo = Services.datos.get(path);
            outputser = (String) metodo.invoke(null);
        }
        return outputser;
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set
    }
}