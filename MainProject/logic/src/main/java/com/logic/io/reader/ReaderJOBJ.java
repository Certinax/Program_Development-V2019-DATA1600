package com.logic.io.reader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ReaderJOBJ implements Reader {

    @Override
    public ArrayList<Object> readObjects(String path) {
        ArrayList<Object> objectsFromFile = new ArrayList<>();
        Object serializedObject = null;

        try {
            FileInputStream fin = new FileInputStream(path);
            ObjectInputStream oin = new ObjectInputStream(fin);
            serializedObject = oin.readObject();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); //TODO This should send a message to the GUI!
        }
        objectsFromFile.add(serializedObject);

        throw new UnsupportedOperationException("readObjects in ReaderJOBJ is not finnished yet");
        //return objectsFromFile;
    }
}
