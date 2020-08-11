package com.android.plugin.kotlin.exec

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * @author lizhifeng
 * @date 2020/4/29 20:36
 * 插入执行 field.method
 * 局部变量（基本数据类型除外）.method
 * 成员变量（基本数据类型除外）.method
 */


class ExecFieldMethodVisitor(methodVisitor: MethodVisitor)
    : MethodVisitor(Opcodes.ASM5, methodVisitor) {
    override fun visitCode() {
//        ALOAD 0

//        GETFIELD
//        com/android/asm/exec/ExecFieldActivity.execFieldTest
//        : Lcom/android/asm/exec/ExecFieldTest;

//        INVOKEVIRTUAL
//        com/android/asm/exec/ExecFieldTest.execFieldMethod
//        ()V
        println(">>>>>> ExecFieldMethodVisitor ")
        mv.visitVarInsn(Opcodes.ALOAD,0)
        mv.visitFieldInsn(Opcodes.GETFIELD,
                "com/android/asm/exec/ExecFieldActivity",
                "execFieldTest",
                "Lcom/android/asm/simulate/ExecFieldTest;")

        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                "com/android/asm/simulate/ExecFieldTest",
                "execFieldMethod",
                "()V",
                false
        )

        mv.visitVarInsn(Opcodes.ALOAD,0)
        mv.visitLdcInsn("usudhusahid")
        mv.visitFieldInsn(Opcodes.PUTFIELD,
                "com/android/asm/exec/ExecFieldActivity",
                "text",
                "Ljava/lang/String;")
        super.visitCode()
    }
}

