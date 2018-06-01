package com.starluo.stream;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

/**
 * @author cong.zhang
 * @date 2017/12/20.
 * @time 9:58.
 */
public class RandomFileDemo {

    public static void main(String[] args) throws Exception {
        Employee e1 = new Employee("jack", 11);
        Employee e2 = new Employee("lom", 56);
        Employee e3 = new Employee("tt", 22);
        RandomAccessFile ra = new RandomAccessFile("c:\\employee.txt", "rw");
        ra.write(e1.name.getBytes());
        ra.writeInt(e1.age);
        ra.write(e2.name.getBytes());
        ra.writeInt(e2.age);
        ra.write(e3.name.getBytes());
        ra.writeInt(e3.age);
        ra.close();

        RandomAccessFile raf = new RandomAccessFile("c:\\employee.txt", "r");
        int len = 8;
        // 跳过第一个员工的信息,其姓名8字节,年龄4字节
        raf.skipBytes(12);

        System.out.println("第二个员工信息:");
        String str = "";
        for (int i = 0; i < len; i++) {
            str = str + (char) raf.readByte();
        }
        System.out.println("name:" + str);
        System.out.println("age:" + raf.readInt());

        System.out.println("第一个员工的信息:");
        // 将文件指针移动到文件开始位置
        raf.seek(0);
        str = "";
        for (int i = 0; i < len; i++) {
            str = str + (char) raf.readByte();
        }
        System.out.println("name:" + str);
        System.out.println("age:" + raf.readInt());

        System.out.println("第三个员工的信息:");
        // 跳过第二个员工信息
        raf.skipBytes(12);
        str = "";
        for (int i = 0; i < len; i++) {
            str = str + (char) raf.readByte();
        }
        System.out.println("name:" + str.trim());
        System.out.println("age:" + raf.readInt());

        raf.close();
    }

}

class Employee {

    String name;
    int age;
    final static int LEN = 8;

    public Employee() {
    }

    public Employee(String name, int age) {
        if (name.length() > LEN) {
            name = name.substring(0, 8);
        } else {
            while (name.length() < LEN) {
                name = name + "\u0000";
            }
        }
        this.name = name;
        this.age = age;
    }

}
