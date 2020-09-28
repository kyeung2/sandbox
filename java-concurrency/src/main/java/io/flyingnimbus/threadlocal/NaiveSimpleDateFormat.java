package io.flyingnimbus.threadlocal;

import java.text.SimpleDateFormat;

public class NaiveSimpleDateFormat {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public SimpleDateFormat getFormat() {
        return dateFormat;
    }
}
