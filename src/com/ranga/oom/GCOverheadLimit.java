package com.ranga.oom;

import java.util.*;

public class GCOverheadLimit {
    public static void main(String[] args) {
        gcOverheadLimit();
    }

    private static void gcOverheadLimit() {
        Map m = System.getProperties();
        Random random = new Random();
        while (true) {
            m.put(random.nextInt(), "Ranga Reddy");
        }
    }
}
