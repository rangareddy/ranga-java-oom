package com.ranga.oom;

import java.util.*;

public class KillProcess {
    public static void main(String[] args) {
        List<int[]> list = new ArrayList();
        processKillLoop(list);
    }

    private static void processKillLoop(List<int[]> list) {
        for (int i = 1; ; i++) {
            try{
                list.add(new int[100_000_000]);
            }catch(Throwable t){
                t.printStackTrace();
            }
        }
    }
}
