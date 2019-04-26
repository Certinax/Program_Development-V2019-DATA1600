package com.logic.io.writer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * <h1>Writer JOBJ</h1>
 *
 * Class for serializing objects to file
 *
 * @author Fredrik Pedersen
 * @since 25-04-2019
 */

public class WriterJOBJ implements Writer {

    public void writeObject(Object obj, String path) throws IOException {
        ObservableList<Object> object = FXCollections.observableArrayList();
        object.add(obj);

        writeObjects(object, path);
    }

    public <T> void writeObjects(ObservableList<T> objectList, String path) throws IOException {
        ArrayList<T> serializeableList = new ArrayList<>(objectList);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
        out.writeObject(serializeableList);
    }
}
