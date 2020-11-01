package com.xiaojianhx.demo.designpattern.observer;

public class Test {

    public static void main(String[] args) {

        var server = new Server();

        var client1 = new Client("client1");
        var client2 = new Client("client2");
        var client3 = new Client("client3");

        server.registerObserver(client1);
        server.registerObserver(client2);
        server.registerObserver(client3);
        server.update("PHP是世界上最好用的语言！");

        System.out.println("----------------------------------------------");
        server.removeObserver(client1);
        server.update("JAVA是世界上最好用的语言！");
    }
}