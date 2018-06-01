package com.starluo.base.junit;

import junit.framework.TestCase;

/**
 * @author cong.zhang
 * @date 2017/12/15.
 * @time 15:26.
 */
public class AppTest extends TestCase {

    public void testObject(){
        Person person1 = new Person("tt");
        Person person2 = new Person("pp");
        Person person3 = new Person("dd");

        System.out.println(person1.toString());
        System.out.println(person2.toString());
        System.out.println(person3.toString());
        System.out.println("-----------------------");
        Person.city = "美国";
        System.out.println(person1.toString());
        System.out.println(person2.toString());
        System.out.println(person3.toString());

    }



}

class Person {
    static String city="中国";
    String name;

    static int count;
    public Person() {
        count++ ; // 增加了一个对象
        System.out.println("产生了："+count+"个对象！");
    }

    public Person(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + ',' +
                "city='" + city + '\'' +
                '}';
    }
}