package test;

import test.model.Student;

import java.util.Optional;

/**
 * @Description:
 * @author: Xu Yuwen
 * @Date: 2022-06-28 08:57
 */
public class main {
    public static void main(String[] args) {

        /*Optional<Student> st = Optional.ofNullable(new Student());
        System.out.println(st.isPresent());*/
        Student st = null;
        Optional<Student> sttt = Optional.ofNullable(st);

        Student st0 = sttt.orElse(new Student("xxx","1"));
        System.out.println(st0);

        Student st1 = sttt.orElseGet(()->new Student("xyw","11"));
        System.out.println(st1);

        System.out.println(sttt.orElseThrow(()->new RuntimeException()));

    }
}
