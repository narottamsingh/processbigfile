package com.bce.fileprocess;

import java.util.concurrent.atomic.AtomicInteger;

public class Constant {
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void inc() { 
        counter.incrementAndGet(); 
    }

    public static int getCount() { 
        return counter.get();
    }
}
