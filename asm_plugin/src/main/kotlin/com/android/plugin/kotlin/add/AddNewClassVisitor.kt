package com.android.plugin.kotlin.add

import jdk.internal.org.objectweb.asm.ClassVisitor
import jdk.internal.org.objectweb.asm.ClassWriter
import jdk.internal.org.objectweb.asm.Opcodes


/**
 * @author lizhifeng
 * @date 2020/5/6 10:54
 * 创建 一个class
 * 创建内部类
 * 创建外部类
 * 创建匿名内部类
 */
class AddNewClassVisitor(classWriter: ClassWriter)
    : ClassVisitor(Opcodes.ASM5, classWriter) {
    override fun visit(version: Int, access: Int, name: String?, signature: String?, superName: String?, interfaces: Array<out String>?) {
        super.visit(version, access, name, signature, superName, interfaces)
        val cw = ClassWriter(0)
        //创建类头部信息：jdk版本，修饰符，类全名，签名信息，父类，接口集
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "com/android/Student",
                null, "java/lang/Object", null)
        //创建字段age：修饰符，变量名，类型，签名信息，初始值（不一定会起作用后面会说明）
        cw.visitField(Opcodes.ACC_PUBLIC, "age", "I", null, 11)
                .visitEnd()
        //创建方法：修饰符，方法名，类型，描述（输入输出类型），签名信息，抛出异常集合
        val initMehodVisitor = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>",
                "()V", null, null)
        initMehodVisitor.visitCode()
        // aload_0
        initMehodVisitor.visitVarInsn(Opcodes.ALOAD, 0)
        // 获取变量的值，
        initMehodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL,
                "java/lang/Object", "<init>", "()V", false)

        //赋值 start
        initMehodVisitor.visitVarInsn(Opcodes.ALOAD, 0)
        initMehodVisitor.visitIntInsn(Opcodes.BIPUSH, 10)
        initMehodVisitor.visitFieldInsn(Opcodes.PUTFIELD, "com/android/Student",
                "age", "I")
        //赋值 end
        // 结束
        initMehodVisitor.visitInsn(Opcodes.IRETURN)
        // 设置操作数栈和本地变量表的大小
        initMehodVisitor.visitMaxs(2, 1)
        //结束方法生成
        initMehodVisitor.visitEnd()

        // 方法的逻辑全部使用jvm指令来书写的比较晦涩，门槛较高，后面会介绍简单的方法
        val getAgeMethodVisitor = cw.visitMethod(Opcodes.ACC_PUBLIC, "getAge",
                "()I", null, null)
        // 创建方法第一步
        // 创建方法第一步
        getAgeMethodVisitor.visitCode()
        // 将索引为 #0 的本地变量列表加到操作数栈下。#0 索引的本地变量列表永远是 this ，当前类实例的引用。
        // 将索引为 #0 的本地变量列表加到操作数栈下。#0 索引的本地变量列表永远是 this ，当前类实例的引用。
        getAgeMethodVisitor.visitVarInsn(Opcodes.ALOAD, 0)
        // 获取变量的值，
        // 获取变量的值，
        getAgeMethodVisitor.visitFieldInsn(Opcodes.GETFIELD, "com/android/Student",
                "age", "I")
        // 返回age
        // 返回age
        getAgeMethodVisitor.visitInsn(Opcodes.IRETURN)
        // 设置操作数栈和本地变量表的大小
        // 设置操作数栈和本地变量表的大小
        getAgeMethodVisitor.visitMaxs(1, 1)
        //结束方法生成
        //结束方法生成
        getAgeMethodVisitor.visitEnd()
        //结束类生成
        //结束类生成
        cw.visitEnd()
    }
}