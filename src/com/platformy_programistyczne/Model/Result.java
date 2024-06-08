package com.platformy_programistyczne.Model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Result model class specified by player's name
 * and value of a result (how many fruits/frogs snake ate)
 */
public class Result {
    private int result;
    private String name;

    public Result(int result, String name) {
        this.result = result;
        this.name = name;
    }

    public Result() { }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to append result to the file result.txt
     * @throws IOException - exception advanced if there is some problem with input/output
     * operations
     */
    public void saveResult() throws IOException {
        var output = new BufferedWriter(new FileWriter("src/results/result.txt", true));
        output.append(name + " " + result + "\n");
        output.close();
    }
}
