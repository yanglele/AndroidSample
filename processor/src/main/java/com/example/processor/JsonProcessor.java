package com.example.processor;

import com.example.processor_lib.Json;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * Desc:
 * Author:yl
 * Email:yangle06@anjuke.com
 * data:2018/9/13
 * version:
 * update:
 */
public class JsonProcessor extends AbstractProcessor {

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> set = new HashSet<>();
        set.add(Json.class.getCanonicalName());
        return set;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        // 准备在gradle的控制台打印信息
        Messager messager = processingEnv.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE, "start: --------------");

        // 打印注解
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Json.class);
        for (Element element : elements) {
            String name = element.getSimpleName().toString();
            String value = element.getAnnotation(Json.class).value();
            messager.printMessage(Diagnostic.Kind.NOTE, name + " --> " + value);
        }
        //该方法返回ture表示该注解已经被处理, 后续不会再有其他处理器处理; 返回false表示仍可被其他处理器处理.
        return true;
    }
}
