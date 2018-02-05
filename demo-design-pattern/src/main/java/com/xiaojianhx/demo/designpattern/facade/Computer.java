package com.xiaojianhx.demo.designpattern.facade;

public class Computer {

    private Cpu cpu;
    private Memory memory;
    private Disk disk;

    public Computer() {
        cpu = new Cpu();
        memory = new Memory();
        disk = new Disk();
    }

    public void startup() {

        System.out.println("startup computer!");
        cpu.startup();
        memory.startup();
        disk.startup();
        System.out.println("startup computer finshed!");
    }

    public void shutdown() {
        System.out.println("shutdown computer!");
        cpu.shutdown();
        memory.shutdown();
        disk.shutdown();
        System.out.println("shutdown computer finshed!");
    }
}