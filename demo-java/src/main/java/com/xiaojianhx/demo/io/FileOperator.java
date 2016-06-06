package com.xiaojianhx.demo.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class FileOperator {

    public void stream() {

        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream("D:/src.txt");
            os = new FileOutputStream("D:/dst.txt");

            byte[] b = new byte[1024];

            int i = 0;
            while ((i = is.read(b, 0, b.length)) > -1) {
                os.write(b, 0, i);
            }
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void zifuliu() {

        Reader r = null;
        Writer w = null;
        try {
            r = new FileReader("D:/src.txt");
            w = new FileWriter("D:/dst.txt");

            char[] cbuf = new char[1024];
            int i = 0;
            while ((i = r.read(cbuf, 0, cbuf.length)) > -1) {
                w.write(cbuf, 0, i);
            }
            w.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (r != null) {
                try {
                    r.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (w != null) {
                try {
                    w.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {

        int count = 10;
        FileOperator obj = new FileOperator();
        long start0 = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            obj.stream();
        }

        System.out.println("stream:" + (System.currentTimeMillis() - start0) + "ms");

        long start1 = System.currentTimeMillis();

        for (int i = 0; i < count; i++) {
            obj.zifuliu();
        }

        System.out.println("zifuliu:" + (System.currentTimeMillis() - start1) + "ms");
    }
}