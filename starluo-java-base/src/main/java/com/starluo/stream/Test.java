package com.starluo.stream;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author cong.zhang
 * @date 2017/12/22.
 * @time 16:43.
 */
public class Test {

    public static void main(String[] args) throws IOException {
        System.getProperties().list(System.out);
        System.out.println("-------------------------------------");
        Properties sp = System.getProperties();
        Enumeration<?> enumeration = sp.propertyNames();
        while (enumeration.hasMoreElements()) {
            String key = (String) enumeration.nextElement();
            System.out.println(key + "=" + sp.getProperty(key));
        }


        Runtime runtime = Runtime.getRuntime();
        runtime.exec("atom.exe");
    }

}
