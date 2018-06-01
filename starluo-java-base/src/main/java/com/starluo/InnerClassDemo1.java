package com.starluo;

/**
 * @author cong.zhang
 * @date 2017/12/15.
 * @time 16:12.
 */
public class InnerClassDemo1 {

    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.inst();
    }

}

class Outer {
    int score = 95;

    void inst(){
        Inner inner = new Inner();
        inner.display();
    }

    public class Inner{
        void display(){
            String name = "清风徐来";
            System.out.println("成绩: score = " + score);
        }
    }

    public void print(){
//        System.out.println("姓名：" + name) ;
    }
}
