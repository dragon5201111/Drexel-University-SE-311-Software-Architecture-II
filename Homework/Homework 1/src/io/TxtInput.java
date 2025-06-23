package io;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TxtInput implements Input{

    @Override
    public List<String> readLines(String fileName) {
        try{
            return Files.readAllLines(Paths.get(fileName));
        }catch (Exception e){
            throw new RuntimeException(e + " unable to read file " + fileName + ".");
        }
    }
}
