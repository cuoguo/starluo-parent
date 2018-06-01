package com.starluo.applet;

import javax.swing.*;

/**
 * @author cong.zhang
 * @date 2018/4/23.
 * @time 9:58.
 */
public class WelcomeInMessageDialogBox {

    public static void main(String[] args) {
        // JOptionPane.showMessageDialog(null, "Welcome to  Java !");

        // JOptionPane.showMessageDialog(null, "Welcome to  Java !", "猜猜我是谁！",JOptionPane.INFORMATION_MESSAGE);

        long totalMillseconds = System.currentTimeMillis();

        long totalSeconds = totalMillseconds / 1000;

        long currentSecond = totalSeconds % 60;

        long totalMinuts = totalSeconds / 60;

        long currentMinute = totalMinuts % 60;

        long totalHours = totalMinuts / 60;

        long currentHours = totalHours % 24;

        System.out.println("Current time is " + currentHours + ":" + currentMinute + ":" + currentSecond + " GMT");
    }

}
