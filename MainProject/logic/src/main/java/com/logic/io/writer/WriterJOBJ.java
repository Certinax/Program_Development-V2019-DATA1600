package com.logic.io.writer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * <h1>Writer JOBJ</h1>
 *
 * Class for serializing objects to file
 *
 * @author Fredrik Pedersen
 * @since 25-04-2019
 */

public class WriterJOBJ implements Writer {

    @Override
    public void writeObject(Object obj, String path, boolean append) throws IOException {
            FileOutputStream fos = new FileOutputStream(path, append);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(obj);
    }

    //TODO find out how to serialize an object and write it to a file with a custom header/template
    @Override
    public void writeObject(Object obj, String path, boolean append, String[] template) {
        throw new UnsupportedOperationException("This method haven't been implemented yet!");
    }
}
