package com.xiaojianhx.demo.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Client {

    private Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Client() {
        try {

            Socket socket = new Socket("localhost", 8888);
            System.out.println("Established a connection...");

            PrintWriter out = new PrintWriter(socket.getOutputStream());

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Scanner sysin = new Scanner(System.in);
            String line = sysin.nextLine();

            while (!line.equals("bye")) { // 若从标准输入读入的字符串为 "bye"则停止循环

                out.println(line);
                out.flush();

                // 从Server读入一字符串，并打印到标准输出上
                System.out.println("[Server]: " + in.readLine() + "\t\t" + f.format(new Date()));

                line = sysin.nextLine();
                System.out.println("[----Client]: " + line + "\t\t" + f.format(new Date()));
            }
            sysin.close();
            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Error. " + e);
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}