package com.android.plugin.kotlin.add

import jdk.internal.org.objectweb.asm.ClassWriter
import jdk.internal.org.objectweb.asm.MethodVisitor
import jdk.internal.org.objectweb.asm.Opcodes


/**
 * @author lizhifeng
 * @date 2020/5/6 10:54
 * 创建方法
 * 创建普通方法（访问权限private public等）
 * 创建静态方法
 */
class AddNewMethodVisitor(methodVisitor: MethodVisitor)
    : MethodVisitor(Opcodes.ASM5, methodVisitor) {
    override fun visitCode() {
        super.visitCode()

        // 添加add方法
        val classWriter= ClassWriter(null, ClassWriter.COMPUTE_MAXS)
        mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC, "add", "(II)I", null, null)
        mv.visitCode()
        mv.visitVarInsn(Opcodes.ILOAD, 1)
        mv.visitVarInsn(Opcodes.ILOAD, 2)
        mv.visitInsn(Opcodes.IADD)
        mv.visitInsn(Opcodes.IRETURN)
        mv.visitMaxs(2, 3)
        mv.visitEnd()
    }
}