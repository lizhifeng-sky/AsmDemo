package com.android.no.plugin.test;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.BIPUSH;
import static org.objectweb.asm.Opcodes.GETFIELD;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.IRETURN;
import static org.objectweb.asm.Opcodes.PUTFIELD;

/**
 * @author lizhifeng
 * @date 2020/5/7 10:01
 */
public class MyClassLoader extends ClassLoader {
    public static void main(String[] args) {
        MyClassLoader myClassLoader = new MyClassLoader();
        try {
            Class classByBytes = myClassLoader.getClassByBytes(createNewClass());
            Object o = classByBytes.newInstance();
            Field field = classByBytes.getField("age");
            Object o1 = field.get(o);
            Method method = classByBytes.getMethod("getAge");
            Object o2 = method.invoke(o);
            System.out.println("Field age:  " + o1);
            System.out.println("Method method :  " + o2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static byte[] createNewClass() {
        ClassWriter cw = new ClassWriter(0);
        //创建类头部信息：jdk版本，修饰符，类全名，签名信息，父类，接口集
        cw.visit(Opcodes.V1_7, ACC_PUBLIC, "com/android/Student",
                null, "java/lang/Object", null);
        //创建字段age：修饰符，变量名，类型，签名信息，初始值（不一定会起作用后面会说明）
        cw.visitField(ACC_PUBLIC, "age", "I", null,
                new Integer(11))
                .visitEnd();
        //创建方法：修饰符，方法名，类型，描述（输入输出类型），签名信息，抛出异常集合
        MethodVisitor initMehodVisitor = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        initMehodVisitor.visitCode();
        // aload_0
        initMehodVisitor.visitVarInsn(ALOAD, 0);
        // 获取变量的值，
        initMehodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);

        //赋值 start
        initMehodVisitor.visitVarInsn(ALOAD, 0);
        initMehodVisitor.visitIntInsn(BIPUSH, 10);
        initMehodVisitor.visitFieldInsn(PUTFIELD, "com/android/Student", "age", "I");
        //赋值 end

        // 结束
        initMehodVisitor.visitInsn(IRETURN);
        // 设置操作数栈和本地变量表的大小
        initMehodVisitor.visitMaxs(2, 1);
        //结束方法生成
        initMehodVisitor.visitEnd();

        // 方法的逻辑全部使用jvm指令来书写的比较晦涩，门槛较高，后面会介绍简单的方法
        MethodVisitor getAgeMethodVisitor = cw.visitMethod(ACC_PUBLIC, "getAge", "()I", null, null);
        // 创建方法第一步
        getAgeMethodVisitor.visitCode();
        // 将索引为 #0 的本地变量列表加到操作数栈下。#0 索引的本地变量列表永远是 this ，当前类实例的引用。
        getAgeMethodVisitor.visitVarInsn(ALOAD, 0);
        // 获取变量的值，
        getAgeMethodVisitor.visitFieldInsn(GETFIELD, "com/android/Student", "age", "I");
        // 返回age
        getAgeMethodVisitor.visitInsn(IRETURN);
        // 设置操作数栈和本地变量表的大小
        getAgeMethodVisitor.visitMaxs(1, 1);
        //结束方法生成
        getAgeMethodVisitor.visitEnd();
        //结束类生成
        cw.visitEnd();
        //返回class的byte[]数组
        return cw.toByteArray();
    }

    public Class getClassByBytes(byte[] bytes) {
        return defineClass(null, bytes, 0, bytes.length);
    }
}
