package com.logic.io.reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReaderJOBJ implements Reader {

    @Override
    @SuppressWarnings("unchecked")
    public ArrayList<Object> readObjects(String path) throws IOException, ClassNotFoundException {
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(path));

        ArrayList<Object> input = (ArrayList<Object>) is.readObject();
        is.close();
        return input;
    }
}
