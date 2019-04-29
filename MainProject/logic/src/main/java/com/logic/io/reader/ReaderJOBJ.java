package com.logic.io.reader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


/**
 * <h1>Reader JOBJ</h1>
 *
 * Class for reading serialized objects from file and returning them as objects
 * in an arraylist
 *
 * @author Fredrik Pedersen
 * @since 26-04-2019
 */
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
