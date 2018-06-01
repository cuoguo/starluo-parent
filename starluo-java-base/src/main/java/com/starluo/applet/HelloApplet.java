package com.starluo.applet;

import java.applet.Applet;
import java.awt.*;

/**
 * @author cong.zhang
 * @date 2017/12/22.
 * @time 16:49.
 */
public class HelloApplet extends Applet {

    String mystring="";

    @Override
    public void print(Graphics g) {
        g.drawString(mystring,5,30);
    }

    @Override
    public void init() {
        mystring = mystring + "正在初始化……";
        repaint();
    }

    @Override
    public void start() {
        mystring = mystring + "正在开始执行程序……";
        repaint();
    }

    @Override
    public void stop() {
        mystring = mystring + "正在停止执行程序……";
        repaint();
    }

    @Override
    public void destroy() {
        mystring = mystring + "正在收回资源……";
        repaint();
    }

}
