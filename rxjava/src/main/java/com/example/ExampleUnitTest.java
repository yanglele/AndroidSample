package com.example;

import com.example.model.Course;
import com.example.model.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private static ReferenceQueue<byte[]> rq = new ReferenceQueue<byte[]>();
    private static int _1M = 1024*1024;

    public static void main(String[] args) {
//        linkedHashMapTest();
//        com.example.Student student = new com.example.Student("qq",null);
//        Gson gson = new Gson();
//        String studentString = gson.toJson(student);
//        Student student1 = gson.fromJson(studentString,Student.class);
//        testMethod(student1);
        system64kMethod();
    }

    public static void system64kMethod(){
        for(int i=0;i<8826;i++){
            System.out.println("public int method_"+i+"(){return 0;}");
        }
    }

    public static void testMethod(Student student){

    }

    public static void testBuilder(){
        Student student = new ExampleUnitTest.Builder("yl")
                .setCourse(new Course("11"))
                .getInstance();
    }

    static class Builder{
        private String name;
        private List<Course> courseList;//课程

        public Builder(String name){
            this.name = name;
        }

        public Builder setCourse(Course course){
            if(courseList == null){
                courseList = new ArrayList<>();
            }
            courseList.add(course);
            return this;
        }

        public Student getInstance(){
            return new Student(name,courseList);
        }
    }


    private static void linkedHashMapTest(){
        LinkedHashMap<Integer,Integer> map = new LinkedHashMap<>(0,0.75f,true);

        map.put(1,1);
        map.put(2,2);
        map.put(3,3);

        map.get(2);

        for(Map.Entry entry : map.entrySet()){
            System.out.println(entry.getKey());
        }
    }

    private static void weakReferenceTest(){
        String s = new String("111");
        ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
        WeakReference<String> weakReference = new WeakReference<>(s,referenceQueue);
        System.out.println("before gc :"+weakReference.toString());
        s=null;
        System.gc();
        System.out.println("after gc weakReference = :"+weakReference.toString());
        System.out.println("after gc weakReference target = :"+weakReference.get());
        System.out.println("queue = "+referenceQueue.poll().toString());
    }

    private static void weakReferenceQueueTest(){
        Object value = new Object();
        Map<Object, Object> map = new HashMap<>();
        Thread thread = new Thread(() -> {
            try {
                int cnt = 0;
                WeakReference<byte[]> k;
                while((k = (WeakReference) rq.remove()) != null) {
                    System.out.println((cnt++) + "回收了:" + k);
                }
            } catch(InterruptedException e) {
                //结束循环
            }
        });
        thread.setDaemon(true);
        thread.start();

        for(int i = 0;i < 10000;i++) {
            byte[] bytes = new byte[_1M];
            WeakReference<byte[]> weakReference = new WeakReference<byte[]>(bytes, rq);
            map.put(weakReference, value);
        }
        System.out.println("map.size->" + map.size());
    }

}