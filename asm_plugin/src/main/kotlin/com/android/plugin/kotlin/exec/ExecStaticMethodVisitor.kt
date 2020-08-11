package com.android.plugin.kotlin.exec

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * @author lizhifeng
 * @date 2020/4/29 20:36
 * 插入执行 static method
 */


class ExecStaticMethodVisitor(methodVisitor: MethodVisitor)
    : MethodVisitor(Opcodes.ASM5, methodVisitor) {

    override fun visitCode() {
        println(">>>>>> ExecStaticMethodVisitor ")
        mv.visitLdcInsn("TAG")
        mv.visitLdcInsn("===== 我的插入执行 static.method=====")
        mv.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "android/util/Log",
                "e",
                "(Ljava/lang/String;Ljava/lang/String;)I",
                false
        )
        mv.visitInsn(Opcodes.POP)
        super.visitCode()
    }


    override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor {
        return super.visitAnnotation(descriptor, visible)
    }

}

