package com.example.rxjava;

import com.example.model.Course;
import com.example.model.Student;

import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MyClass {

    private static SimpleDateFormat formatter;

    public static void main(String[] args) {
//        simpleAction();
//        simpleOnSubscribe();
//        simpleFrom();
//        simpleOnSubscribe1();
        simpleMap();
//        simpleFlatMap();
//        simpleLift();
//        simpleFilter();
//        simpleSubscribeNext();
    }


    private static boolean isInt(String num) {
        try {
            Integer.valueOf(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //from也是调用的OnSubscribe
    private static void simpleFilter() {
        String[] names = {"11", "yll", "ylll"};
        Observable<String> observable = Observable
                .from(names)
                .filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return isInt(s);
            }
        });

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };

        observable.subscribe(subscriber);
    }

    //Subscriber是对Observer的扩展,基本的还是Observer
    private static void simpleSubscribeNext() {

//        Observable.create(new Observable.OnSubscribe<String>() {
//            @Override
//            public void call(Subscriber<? super String> subscriber) {
//                subscriber.onNext("hello");
//                subscriber.onNext("1");
//                subscriber.onCompleted();
//            }
//        }).filter(new Func1<String, Boolean>() {
//            @Override
//            public Boolean call(String s) {
//                return isInt(s);
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onCompleted() {
//                System.out.println("complete");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("error");
//            }
//
//            @Override
//            public void onNext(String s) {
//                System.out.println(s);
//            }
//        });

        Observable.OnSubscribe<String> onSubscribe = new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onNext("1");
                subscriber.onCompleted();
            }
        };

        Observable<String> observable = Observable.create(onSubscribe);

        Observable<String> observableFilter = observable.filter(new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return isInt(s);
            }
        });

        Observable<Integer> observableMap = observableFilter.map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return Integer.valueOf(s);
            }
        });

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onNext(Integer s) {
                System.out.println(s);
            }
        };

        observableMap.subscribe(observer);
    }

    //自定义操作符，int转为string
    private static void simpleLift() {
        Integer[] integerList = {1, 2, 3};
        Observable.from(integerList).doOnNext(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println("onNext = " + integer);
            }
        }).lift(new Observable.Operator<String, Integer>() {
            @Override
            public Subscriber<? super Integer> call(final Subscriber<? super String> subscriber) {
                return new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        subscriber.onNext("" + integer);
                    }
                };
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    //flatMap会遍历student的courseList
    private static void simpleFlatMap() {

        //场景：遍历student course
        Course course1 = new Course("China");
        Course course2 = new Course("English");
        Course course3 = new Course("Japanese");

        List<Course> courseList1 = new ArrayList<>();
        courseList1.add(course1);
        courseList1.add(course2);

        Student student1 = new Student("yl", courseList1);

        List<Course> courseList2 = new ArrayList<>();
        courseList1.add(course1);
        courseList1.add(course3);

        Student student2 = new Student("yll", courseList2);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);

        Observable.from(studentList).subscribe(new Subscriber<Student>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Student student) {
                for (Course course : student.getCourseList()) {
                    System.out.println(course.getCourseName());
                }
            }
        });

        //map不能实现
        Observable.from(studentList).map(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                return Observable.from(student.getCourseList());
            }
        }).subscribe(new Subscriber<Observable<Course>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Observable<Course> courseObservable) {
            }
        });

        //使用FlatMap
        Observable.from(studentList).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student) {
                return Observable.from(student.getCourseList());
            }
        }).subscribe(new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                System.out.println(course.getCourseName());
            }
        });

    }


    //map将String类型转换为Integer,map 一对一转换
    private static void simpleMap() {
        String[] names = {"11", "22", "33"};
        Observable.from(names)
                .map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                if (Integer.valueOf(s) > 0) {
                    return Integer.parseInt(s);
                }
                return 0;
            }
        }).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 1;
            }
        })
                .subscribeOn(Schedulers.immediate())
                .observeOn(Schedulers.immediate())
                .subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });
    }

    //Subscriber是对Observer的扩展,基本的还是Observer
    private static void simpleOnSubscribe1() {
        Subscription subscribe = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.computation()).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    //from也是调用的OnSubscribe
    private static void simpleFrom() {
        String[] names = {"yl", "yll", "ylll"};
        Subscription subscribe = Observable.from(names).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    //创建被观察者基本方法 create
    private static void simpleOnSubscribe() {
        Subscription subscribe = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("error");
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    //Action为单一动作
    private static void simpleAction() {
        String[] names = {"yl", "yll", "ylll"};
        Observable<String> observable = Observable.from(names);
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("onNextAction = " + s);
            }
        };


        Action0 onCompleteAction = new Action0() {
            @Override
            public void call() {
                System.out.println("onCompleteAction");
            }
        };

        Action1<Throwable> onErrorAction = new Action1<Throwable>() {
            @Override
            public void call(Throwable s) {
                System.out.println("onErrorAction = " + s);
            }
        };

        observable.subscribe(onNextAction);
        observable.subscribe(onNextAction, onErrorAction, onCompleteAction);
    }


}
