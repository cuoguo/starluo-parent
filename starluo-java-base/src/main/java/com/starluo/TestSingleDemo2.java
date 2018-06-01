package com.starluo;

/**
 * @author cong.zhang
 * @date 2017/12/15.
 * @time 15:56.
 */
public class TestSingleDemo2 {

    public static void main(String[] args) {
        Person p = null;
        p = Person.getPerson();
        System.out.println(p.name);
    }

}

class Person {
    private static Person person = new Person();
    String name ;

    public Person() {
        this.name = "徐晓";
    }

    public static Person getPerson(){
        return person;
    }

}
