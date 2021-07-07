package com.ziven.easygo.processor;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.ziven.easygo.annotation.EasyGoMethod;
import com.ziven.easygo.annotation.EasyGoType;
import com.ziven.easygo.annotation.IEasyGoMethod;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.Element;

import static com.ziven.easygo.processor.Constant.ANNOTATION_CLASS;
import static com.ziven.easygo.processor.Constant.CAN_NOT_MODIFY;
import static com.ziven.easygo.processor.Constant.CLASS_SEPARATOR;
import static com.ziven.easygo.processor.Constant.METHOD_EASY_GO_METHOD;
import static com.ziven.easygo.processor.Constant.METHOD_OBTAIN_EASY_GO_TYPE;
import static com.ziven.easygo.processor.Constant.PATH_ANNOTATION;
import static com.ziven.easygo.processor.Constant.PATH_AUTOWIRED;
import static com.ziven.easygo.processor.Constant.TYPE_EASY_GO_TYPE;
import static javax.lang.model.element.Modifier.PUBLIC;

/**
 * @author Ziven
 * @date 2021/6/2
 */
public class EasyGoMethodProcessor extends AbstractEasyGoProcessor {

    @Override
    protected void parseProcess(Set<? extends Element> elements) {
        if(elements == null || elements.isEmpty()) {
            getLogger().info(" Step 1 EasyGoMethodProcessor, has no method");
            return;
        }
        Map<Element, List<Element>> categories = categories(elements);
        if(categories.isEmpty()) {
            getLogger().info(" Step 2 EasyGoMethodProcessor, has no method");
            return;
        }
        generate(categories);
    }

    private void generate(Map<Element, List<Element>> elements) {
        String suffix = getAnnotationClass().getSimpleName();
        for (Map.Entry<Element, List<Element>> entry : elements.entrySet()) {
            Element element = entry.getKey();
            List<Element> elementList = entry.getValue();
            String packageName = getElements().getPackageOf(element).asType().toString();
            String className = element.getSimpleName().toString();
            String newClassName = className + CLASS_SEPARATOR + suffix;

            TypeSpec.Builder classBuilder = TypeSpec.classBuilder(newClassName)
                    .addModifiers(PUBLIC);

            ClassName easyGoType = ClassName.get(PATH_ANNOTATION, TYPE_EASY_GO_TYPE);
            ClassName list = ClassName.get(List.class);
            TypeName listEasyGoType = ParameterizedTypeName.get(list, easyGoType);

            MethodSpec.Builder obtainEasyGoType = MethodSpec.methodBuilder(METHOD_OBTAIN_EASY_GO_TYPE)
                    .addAnnotation(Override.class)
                    .returns(listEasyGoType)
                    .addModifiers(PUBLIC)
                    .addStatement("$T listEasyGoType = new $T<>()", listEasyGoType, ClassName.get(ArrayList.class));

            TypeName listObject = ParameterizedTypeName.get(list, ClassName.get(Object.class));
            MethodSpec.Builder easyGoMethod = MethodSpec.methodBuilder(METHOD_EASY_GO_METHOD)
                    .addAnnotation(Override.class)
                    .returns(Object.class)
                    .addModifiers(PUBLIC)
                    .addParameter(Object.class, "target")
                    .addParameter(String.class, "path")
                    .addParameter(listObject, "params")
                    .addStatement("Object result = null")
                    .beginControlFlow("switch(path)");

            for(Element e : elementList) {
                EasyGoMethod annotation = e.getAnnotation(ANNOTATION_CLASS);
                String path = annotation.path();
                obtainEasyGoType.addStatement("listEasyGoType.add(new $T($S, $T.$L))", easyGoType, path, TypeName.get(EasyGoType.ThreadMode.class), annotation.threadMode());

                easyGoMethod.addCode("case $S:\n", path);
                String fieldName = e.asType().toString();
                if(fieldName.endsWith("void")) {
                    easyGoMethod.addStatement("\t(($L.$L) target).$L($L)",
                            packageName,
                            className,
                            e.getSimpleName().toString(),
                            getParams(fieldName));
                } else {
                    easyGoMethod.addStatement("\tresult = (($L.$L) target).$L($L)",
                            packageName,
                            className,
                            e.getSimpleName().toString(),
                            getParams(fieldName));
                }
                easyGoMethod.addStatement("break");
            }

            easyGoMethod.endControlFlow().addStatement("return result");
            obtainEasyGoType.addStatement("return listEasyGoType");
            classBuilder.addMethod(obtainEasyGoType.build())
                    .addJavadoc(CAN_NOT_MODIFY)
                    .addSuperinterface(IEasyGoMethod.class)
                    .addMethod(easyGoMethod.build());

            try {
                JavaFile.builder(PATH_AUTOWIRED, classBuilder.build())
                        .build().writeTo(getFiler());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getParams(String fieldType) {
        String[] fieldAll = fieldType.substring(fieldType.indexOf("(") + 1, fieldType.indexOf(")")).split(",");
        int fieldCount = fieldAll.length - 1;
        StringBuilder params = new StringBuilder();
        if(fieldCount >= 0) {
            for(int i=0; i<fieldCount; i++) {
                params.append(String.format(getLocale(), "(%s) params.get(%d), ", fieldAll[i], i));
            }
            params.append(String.format(getLocale(), "(%s) params.get(%d)", fieldAll[fieldCount], fieldCount));
        }
        return params.toString();
    }

    private Map<Element, List<Element>> categories(Set<? extends Element> elements) {
        Map<Element, List<Element>> categories = new HashMap<>(8);
        if(elements == null || elements.isEmpty()) {
            return categories;
        }
        Element enclosingElement;
        List<Element> elementList;
        for (Element element : elements) {
            enclosingElement = element.getEnclosingElement();
            elementList = categories.get(enclosingElement);
            if(elementList == null) {
                elementList = new ArrayList<>();
                categories.put(enclosingElement, elementList);
            }
            elementList.add(element);
        }
        return categories;
    }

    @Override
    protected Class<? extends Annotation> getAnnotationClass() {
        return ANNOTATION_CLASS;
    }
}
