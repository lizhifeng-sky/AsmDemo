package com.android.plugin.kotlin.add


import org.gradle.internal.impldep.bsh.org.objectweb.asm.Constants
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
/**
 * @author lizhifeng
 * @date 2020/5/6 10:54
 * 创建 一个class
 * 创建内部类
 * 创建外部类
 * 创建匿名内部类
 */
class AddNewClassVisitor(methodVisitor: MethodVisitor)
    : MethodVisitor(Opcodes.ASM5, methodVisitor) {
    override fun visitCode() {
        super.visitCode()

        // 添加add方法
        val classWriter= ClassWriter(null, ClassWriter.COMPUTE_MAXS)
        mv = classWriter.visitMethod(Constants.ACC_PUBLIC, "add", "(II)I", null, null)
        mv.visitCode()
        mv.visitVarInsn(Constants.ILOAD, 1)
        mv.visitVarInsn(Constants.ILOAD, 2)
        mv.visitInsn(Constants.IADD)
        mv.visitInsn(Constants.IRETURN)
        mv.visitMaxs(2, 3)
        mv.visitEnd()
    }
}