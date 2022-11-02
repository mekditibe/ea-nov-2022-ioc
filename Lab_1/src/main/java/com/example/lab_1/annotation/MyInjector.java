package com.example.lab_1.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyInjector {

    String packageName = "com.example.lab_1.annotation";
    List<HashMap<Class, Object>> Ioc = new ArrayList<>();
    HashMap<Class, Object> IocObj = new HashMap<>();
    Object getFromIoc(Class className) {
        return Ioc.stream().filter(c->c.containsKey(className)).findAny();
    }
//    void saveFromIoc(Class className) throws InstantiationException, IllegalAccessException {
//        Ioc.add(IocObj.put(className,className.newInstance()))
//    }
}


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface MyBean
{

}
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface MyAutowired
{
    //HashMap<String,V> MyInjector () default HashMap<String>;
}

@MyBean
class A {
    @MyAutowired
    B b;
    @Override
    public String toString() {
        return "Class A" ;
    }
}

@MyBean
class B {
    @MyAutowired
    A a;
    @Override
    public String toString() {
        return "Class B" ;
    }
}

@MyBean
class C {
    @MyAutowired
    B b;
    @MyAutowired
    A a;
    @Override
    public String toString() {
        return "Class C" ;
    }
};

class BeanNotFoundException extends Exception{
    public BeanNotFoundException(String message){
        super(message);
    }
}