package com.android.plugin.kotlin.add


import org.gradle.internal.impldep.bsh.org.objectweb.asm.Constants
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
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