package com.xiaojianhx.demo.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ServerThread extends Thread {

    private static int number = 0; // 保存本进程的客户计数

    private Socket socket = null; // 保存与本线程相关的Socket对象

    private Format f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ServerThread(Socket socket, int clientnum) {
        this.socket = socket;
        number = clientnum;
        System.out.println("当前在线的用户数: " + number);
    }

    public void run() {

        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 由Socket对象得到输出流,并构造PrintWriter对象
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            // 在标准输出上打印从客户端读入的字符串
            System.out.print("[Client]: " + in.readLine() + "\t\t----" + f.format(new Date()));

            Scanner sysin = new Scanner(System.in);

            String line = sysin.nextLine();

            while (!line.equals("bye")) {

                // 向客户端输出该字符串
                out.println(line);

                // 刷新输出流,使Client马上收到该字符串
                out.flush();

                // 从Client读入一字符串,并打印到标准输出上
                System.out.println("[Client]: " + in.readLine() + "\t\t" + f.format(new Date()));

                line = sysin.nextLine();
                System.out.println("[----Server]: " + line + "\t\t" + f.format(new Date()));
            }

            sysin.close();
            out.close();
            in.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("Error. " + e);
        }
    }
}
