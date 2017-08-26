package com.xiaojianhx.demo.socket;

import java.net.ServerSocket;

public class Server {

    public Server() {
        try {
            int clientcount = 0;
            boolean listening = true;

            ServerSocket server = null;

            try {
                server = new ServerSocket(8888);
                server.setSoTimeout(10000);
                System.out.println("Server starts...");
            } catch (Exception e) {
                System.out.println("Can not listen to. " + e);
            }

            while (listening) {

                clientcount++;
                new ServerThread(server.accept(), clientcount).start();
            }

        } catch (Exception e) {
            System.out.println("Error. " + e);
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}