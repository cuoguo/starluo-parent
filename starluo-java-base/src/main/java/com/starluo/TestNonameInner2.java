package com.starluo;

/**
 * @author cong.zhang
 * @date 2017/12/15.
 * @time 17:48.
 */
public class TestNonameInner2 {

    public static void main(String[] args) {
        B b = new B() ;
        b.test();
    }

}

interface A {
    public void fun1() ;
}

class B {
    int i = 10 ;
    public void get(A a){
        a.fun1();
    }

    public void test() {
        this.get(new A() {
            @Override
            public void fun1() {
                System.out.println(i) ;
            }
        });
    }
}
