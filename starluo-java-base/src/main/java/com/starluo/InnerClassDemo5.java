package com.starluo;

/**
 * @author cong.zhang
 * @date 2017/12/15.
 * @time 16:39.
 */
public class InnerClassDemo5 {

    public static void main(String[] args) {
        Outer1 outer = new Outer1();
        outer.inst(5);
    }

}

class Outer1 {
    int score = 95;

    void inst(final int s){
        int temp = 20;

        class Inner{
            void display() {
                System.out.println("成绩score : " + (score + s + temp));
            }
        }

        Inner inner = new Inner();
        inner.display();
    }
}
