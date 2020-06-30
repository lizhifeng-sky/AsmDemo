package com.android.no.plugin.test;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.BIPUSH;
import static org.objectweb.asm.Opcodes.GETFIELD;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.IRETURN;
import static org.objectweb.asm.Opcodes.PUTFIELD;

/**
 * @author lizhifeng
 * @date 2020/5/7 09:25
 */
public class CreateClass {

//    /**
//     * public class Student{
//     *     public int age = 11;
//     *
//     *     public int getAge() {
//     *         return age;
//     *     }
//     * }
//     * 1、创建一个类需要先调用visit创建类的头部信息。
//     * 2、分别调用visitMethod或visitField生成需要的创建的方法或者字段。
//     * 3、调用visitEnd结束类的创建
//     * 4、调用ClassWriter 的toByteArray将动态生成的class转为byte[]数组，
//     * 可以用ClassLoader动态载入，或者写出成.class文件
//     *
//     *   //创建ClassWriter ，构造参数的含义是是否自动计算栈帧，操作数栈及局部变量表的大小
//     *   //0：完全手动计算 即手动调用visitFrame和visitMaxs完全生效
//     *   //ClassWriter.COMPUTE_MAXS=1：需要自己计算栈帧大小，但本地变量与操作数已自动计算好，
//     *   当然也可以调用visitMaxs方法，只不过不起作用，参数会被忽略；
//     *   //ClassWriter.COMPUTE_FRAMES=2：栈帧本地变量和操作数栈都自动计算，
//     *   不需要调用visitFrame和visitMaxs方法，即使调用也会被忽略。
//     *   //这些选项非常方便，但会有一定的开销，使用COMPUTE_MAXS会慢10%，
//     *   使用COMPUTE_FRAMES会慢2倍。
//     *
//     * */
    public static byte[] createNewClass() {
        ClassWriter cw = new ClassWriter(0);
        //创建类头部信息：jdk版本，修饰符，类全名，签名信息，父类，接口集
        cw.visit(Opcodes.V1_8, ACC_PUBLIC, "com/android/asm/test/Student", null, "java/lang/Object", null);
        //创建字段age：修饰符，变量名，类型，签名信息，初始值（不一定会起作用后面会说明）
        cw.visitField(ACC_PUBLIC , "age", "I", null, new Integer(11))
                .visitEnd();
        //创建方法：修饰符，方法名，类型，描述（输入输出类型），签名信息，抛出异常集合
        MethodVisitor initMehodVisitor = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
        initMehodVisitor.visitCode();
        // aload_0
        initMehodVisitor.visitVarInsn(ALOAD, 0);
        // 获取变量的值，
        initMehodVisitor.visitMethodInsn(INVOKESPECIAL,"java/lang/Object", "<init>", "()V", false);

        //赋值 start
        initMehodVisitor.visitVarInsn(ALOAD,0);
        initMehodVisitor.visitIntInsn(BIPUSH,10);
        initMehodVisitor.visitFieldInsn(PUTFIELD,"com/android/asm/test/Student","age","I");
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
        getAgeMethodVisitor.visitFieldInsn(GETFIELD, "asm/Student", "age", "I");
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
}
