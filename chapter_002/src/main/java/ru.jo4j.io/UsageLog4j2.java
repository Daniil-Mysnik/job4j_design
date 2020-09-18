package ru.jo4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j2 {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String s = "newString";
        int i = 1;
        float f = 1.2F;
        double d = 1.1;
        char ch = 'c';
        byte b = 1;
        boolean bool = true;
        long l = 2;
        LOG.debug("String : {}, int : {}, float : {}, double : {}, char : {}, byte : {}, boolean : {}, long : {}",
                s, i, f, d, ch, b , bool, l);
    }
}
