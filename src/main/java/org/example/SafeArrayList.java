package org.example;

import java.util.ArrayList;

public class SafeArrayList<T> extends ArrayList<T> {
    //returns null if index is out of bounds and positive
    @Override
    public T get(int index) {
        if (index >= this.size()) return null;
        return super.get(index);
    }
}
