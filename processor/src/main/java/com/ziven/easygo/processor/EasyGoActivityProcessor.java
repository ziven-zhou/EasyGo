package com.ziven.easygo.processor;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import com.ziven.easygo.annotation.IEasyGoActivity;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.Element;

import static com.ziven.easygo.processor.Constant.ANNOTATION_CLASS_ACTIVITY;
import static com.ziven.easygo.processor.Constant.CAN_NOT_MODIFY;
import static com.ziven.easygo.processor.Constant.CLASS_SEPARATOR;
import static com.ziven.easygo.processor.Constant.METHOD_OBTAIN_ACTIVITY_PATH;
import static com.ziven.easygo.processor.Constant.PATH_AUTOWIRED;
import static com.ziven.easygo.processor.Constant.PROJECT;
import static javax.lang.model.element.Modifier.PUBLIC;

/**
 * @author :zhiyuan.zhou
 * @date :2021/6/21
 */
public class EasyGoActivityProcessor extends AbstractEasyGoProcessor {
    /**
     * Parse Process
     *
     * @param elements Elements
     */
    @Override
    protected void parseProcess(Set<? extends Element> elements) {
        if(elements == null || elements.isEmpty()) {
            getLogger().info(" EasyGoActivityProcessor, has no method");
            return;
        }
        ParameterizedTypeName typeName = ParameterizedTypeName.get(ClassName.get(Map.class), ClassName.get(String.class), ClassName.get(Class.class.getClass()));
        MethodSpec.Builder obtainActivityPath = MethodSpec.methodBuilder(METHOD_OBTAIN_ACTIVITY_PATH)
                .addAnnotation(Override.class)
                .returns(typeName)
                .addModifiers(PUBLIC)
                .addStatement("$T activityPath = new $T<>()", typeName, ClassName.get(HashMap.class));

        for(Element element : elements) {
            String path = element.getAnnotation(ANNOTATION_CLASS_ACTIVITY).path();
            obtainActivityPath.addStatement("activityPath.put($S, $L.class)", path, element.asType());
        }
        obtainActivityPath.addStatement("return activityPath");

        String className = PROJECT + CLASS_SEPARATOR + ANNOTATION_CLASS_ACTIVITY.getSimpleName();
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(className)
                .addModifiers(PUBLIC)
                .addJavadoc(CAN_NOT_MODIFY)
                .addSuperinterface(IEasyGoActivity.class)
                .addMethod(obtainActivityPath.build());

        try {
            JavaFile.builder(PATH_AUTOWIRED, classBuilder.build())
                    .build().writeTo(getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get Annotation class
     *
     * @return Class
     */
    @Override
    protected Class<? extends Annotation> getAnnotationClass() {
        return ANNOTATION_CLASS_ACTIVITY;
    }
}
