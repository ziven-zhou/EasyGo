package com.ziven.easygo.processor;

import static com.ziven.easygo.processor.Constant.ANNOTATION_CLASS_BEANS;
import static com.ziven.easygo.processor.Constant.CAN_NOT_MODIFY;

import static javax.lang.model.element.Modifier.PUBLIC;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.ziven.easygo.annotation.BeanType;
import com.ziven.easygo.annotation.EasyGoBean;
import com.ziven.easygo.annotation.EasyGoBeans;

import java.io.IOException;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.Element;

/**
 * @author Ziven
 */
public class EasyGoBeanProcessor extends AbstractEasyGoProcessor {

    @Override
    protected void parseProcess(Set<? extends Element> elements) {
        if(elements == null || elements.isEmpty()) {
            getLogger().info(" Step 1 EasyGoBeanProcessor, has no type");
            return;
        }

        Map<Element, List<Element>> categories = categories(elements);
        if(categories.isEmpty()) {
            getLogger().info(" Step 2 EasyGoBeanProcessor, has no type");
            return;
        }
        generate(categories);
    }

    private void generate(String clsPath, String clsName, EasyGoBean[] beans) {
        TypeSpec.Builder classBuilder = TypeSpec.classBuilder(clsName)
                .addJavadoc(CAN_NOT_MODIFY)
                .addAnnotation(Keep.class)
                .addModifiers(PUBLIC);
        boolean hasToString = true;
        boolean hasSerializable = false;
        boolean isFirstToString = true;
        StringBuilder toString = new StringBuilder("\"" + clsName + "{\" +");
        for (EasyGoBean bean : beans) {
            String name = bean.name();
            if(BeanType.HAS_TO_STRING.equals(name)) {
                hasToString = true;
                continue;
            } else if(BeanType.NO_TO_STRING.equals(name)) {
                hasToString = false;
                continue;
            }
            if(BeanType.HAS_SERIALIZABLE.equals(name)) {
                hasSerializable = true;
                continue;
            } else if(BeanType.NO_SERIALIZABLE.equals(name)) {
                hasSerializable = false;
                continue;
            }

            String methodName = name.length() > 1
                    ? String.valueOf(name.charAt(0)).toUpperCase(Locale.US) + name.substring(1)
                    : name.toUpperCase(Locale.US);
            getLogger().info(" Step 5 EasyGoBeanProcessor, methodName:" + methodName);

            TypeName typeName;
            String[] tNames;
            String tName;
            if((tName = bean.type()) != null && !tName.isEmpty()) {
                typeName = Constant.typeName(tName);
            } else if((tName = bean.list()) != null && !tName.isEmpty()) {
                typeName = ParameterizedTypeName.get(ClassName.get(List.class), Constant.typeNameBox(tName));
            } else if((tName = bean.set()) != null && !tName.isEmpty()) {
                typeName = ParameterizedTypeName.get(ClassName.get(Set.class), Constant.typeNameBox(tName));
            } else if((tNames = bean.map()) != null && tNames.length == 2) {
                typeName = ParameterizedTypeName.get(ClassName.get(Map.class), Constant.typeNameBox(tNames[0]), Constant.typeNameBox(tNames[1]));
            } else if((tNames = bean.more()) != null && tNames.length >= 2) {
                TypeName[] other = new TypeName[tNames.length - 1];
                for(int i=0; i<tNames.length - 1; i++) {
                    other[i] = Constant.typeNameBox(tNames[i + 1]);
                }
                typeName = ParameterizedTypeName.get(ClassName.get(Map.class), other);
            } else {
                typeName = TypeName.OBJECT;
            }
            classBuilder.addField(FieldSpec.builder(typeName, name, PUBLIC).build());
            classBuilder.addMethod(MethodSpec.methodBuilder("set" + methodName)
                    .addModifiers(PUBLIC)
                    .addParameter(typeName, name)
                    .addStatement("this." + name + " = " + name)
                    .build());
            classBuilder.addMethod(MethodSpec.methodBuilder("get" + methodName)
                    .addModifiers(PUBLIC)
                    .addStatement("return this." + name)
                    .returns(typeName)
                    .build());
            boolean isString = Constant.isString(typeName);
            if(isFirstToString) {
                toString.append("\n")
                        .append("\"").append(name).append(isString ? "='\"" : "=\"")
                        .append(" + ").append(name).append(isString ? " + '\\'' +" : " +");
                isFirstToString = false;
            } else {
                toString.append("\n")
                        .append("\", ").append(name).append(isString ? "='\"" : "=\"")
                        .append(" + ").append(name).append(isString ? " + '\\'' +" : " +");
            }
        }
        toString.append("\n'}'");
        if(hasSerializable) {
            classBuilder.addSuperinterface(Serializable.class);
        }
        if(hasToString) {
            classBuilder.addMethod(MethodSpec.methodBuilder("toString")
                    .addAnnotation(NonNull.class)
                    .addAnnotation(Override.class)
                    .addModifiers(PUBLIC)
                    .addStatement("return " + toString)
                    .returns(String.class)
                    .build());
        }
        try {
            JavaFile.builder(clsPath, classBuilder.build())
                    .build().writeTo(getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generate(Map<Element, List<Element>> elements) {
        elements.forEach((clsElement, list) -> {
            String packageName = getElements().getPackageOf(clsElement).asType().toString();
            getLogger().info(" Step 3 EasyGoBeanProcessor, packageName:" + packageName);
            list.forEach(typeElement -> {
                EasyGoBeans beans = typeElement.getAnnotation(ANNOTATION_CLASS_BEANS);
                EasyGoBean[] beanArray = beans.value();

                String className = typeElement.getSimpleName().toString();
                String newClassName = className.length() > 1
                        ? String.valueOf(className.charAt(0)).toUpperCase(Locale.US) + className.substring(1)
                        : className.toUpperCase(Locale.US);

                getLogger().info(" Step 4 EasyGoBeanProcessor, newClassName:" + newClassName);
                generate(packageName, newClassName, beanArray);
            });
        });
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
            elementList = categories.computeIfAbsent(enclosingElement, k -> new ArrayList<>());
            elementList.add(element);
        }
        return categories;
    }

    @Override
    protected Class<? extends Annotation> getAnnotationClass() {
        return ANNOTATION_CLASS_BEANS;
    }
}
