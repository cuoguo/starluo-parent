package com.starluo.stream;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author cong.zhang
 * @date 2017/12/20.
 * @time 9:42.
 */
public class StreamDemo {
    static InputStream in = null;
    static OutputStream out = null;

    public static void main(String[] args) {
        File f = new File("c:\\temps.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        outStream(f);
        // getName()方法，取得文件名
        System.out.println("文件名：" + f.getName());
        // getPath()方法，取得文件路径
        System.out.println("文件路径：" + f.getPath());
        // getAbsolutePath()方法，得到绝对路径名
        System.out.println("绝对路径：" + f.getAbsolutePath());
        // getParent()方法，得到父文件夹名
        System.out.println("父文件夹名称：" + f.getParent());
        // exists()，判断文件是否存在
        System.out.println(f.exists() ? "文件存在" : "文件不存在");
        // canWrite()，判断文件是否可写
        System.out.println(f.canWrite() ? "文件可写" : "文件不可写");
        // canRead()，判断文件是否可读
        System.out.println(f.canRead() ? "文件可读" : "文件不可读");
        /// isDirectory()，判断是否是目录
        System.out.println(f.isDirectory() ? "是" : "不是目录");
        // isFile()，判断是否是文件
        System.out.println(f.isFile() ? "是文件" : "不是文件");
        // isAbsolute()，是否是绝对路径名称
        System.out.println(f.isAbsolute() ? "是绝对路径" : "不是绝对路径");
        // lastModified()，文件最后的修改时间
        System.out.println("文件最后修改时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(f.lastModified())));
        // length()，文件的长度
        System.out.println("文件的长度：" + f.length());
        System.out.println("--------------------------------------------------");
        inStream(f);
    }

    private static void inStream(File f) {

        try {
            in = new FileInputStream(f);
            // 开辟一个空间用于接收文件读进来的数据
            byte[] b1 = new byte[1024];
            int i = 0;
            i = in.read(b1) ;
            in.close();

            //将byte数组转换为字符串输出
            System.out.println(new String(b1, 0, i));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void outStream(File f) {
        try {
            out = new FileOutputStream(f);
            // 将字符串转成字节数组
            byte[] b = "Hello Stream ! ! !".getBytes();
            out.write(b);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
