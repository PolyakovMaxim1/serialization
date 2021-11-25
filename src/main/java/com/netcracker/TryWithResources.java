package com.netcracker;

import java.io.*;
import java.nio.file.Paths;
import java.util.logging.Logger;

public class TryWithResources {

    public static void main(String[] args) throws FileNotFoundException {
        TryWithResources.test1();
    }

    public static void method() throws IOException {
        throw new IOException("Я упал");
    }

    public static void test1() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(
                Paths.get(".", "src", "main", "resources", "test.txt").toFile()
        );
        BufferedInputStream input = new BufferedInputStream(
                fileInputStream
        );
        try {
            method();
        } catch (FileNotFoundException io) {
            io.printStackTrace();
        } catch (IOException ioException) {
            try (
                    PrintWriter input1 = new PrintWriter(
                            new FileWriter(Paths.get(".", "src", "main", "resources", "test2.txt").toFile()))
            ) {
                ioException.printStackTrace(input1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                input.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public static void test2() {
        try (
                FileInputStream fileInputStream = new FileInputStream(
                        Paths.get(".", "src", "main", "resources", "test.txt").toFile()
                );
                BufferedInputStream input = new BufferedInputStream(fileInputStream)
        ) {
            method();
        } catch (FileNotFoundException io) {
            io.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
