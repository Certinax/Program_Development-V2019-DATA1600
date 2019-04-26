package com.logic.io.reader;

import java.io.IOException;
import java.util.ArrayList;

public interface Reader {

    ArrayList<Object> readObjects(String path) throws IOException, ClassNotFoundException;
}
