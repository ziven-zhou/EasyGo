package com.ziven.easygo.annotation;

/**
 * @author Ziven
 */
public interface BeanType {
    String BOOLEAN = "boolean";
    String BYTE = "byte";
    String SHORT = "short";
    String INT = "int";
    String LONG = "long";
    String CHAR = "char";
    String FLOAT = "float";
    String DOUBLE = "double";
    String STRING = "java.lang.String";
    String OBJECT = "java.lang.Object";
    String BOX_BOOLEAN = "java.lang.Boolean";
    String BOX_BYTE = "java.lang.Byte";
    String BOX_SHORT = "java.lang.Short";
    String BOX_INT = "java.lang.Integer";
    String BOX_LONG = "java.lang.Long";
    String BOX_CHAR = "java.lang.Character";
    String BOX_FLOAT = "java.lang.Float";
    String BOX_DOUBLE = "java.lang.Double";

    String HAS_TO_STRING = "hasToString";
    String NO_TO_STRING = "noToString";
    String HAS_SERIALIZABLE = "hasSerializable";
    String NO_SERIALIZABLE = "noSerializable";
}
