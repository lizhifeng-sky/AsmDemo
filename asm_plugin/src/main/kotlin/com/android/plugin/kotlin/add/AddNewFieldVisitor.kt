package com.android.plugin.kotlin.add

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes


/**
 * @author lizhifeng
 * @date 2020/5/6 10:54
 * 创建 一个变量
 * 基本数据类型 变量
 * 引用类型 变量
 * 静态常量
 *
 */
class AddNewFieldVisitor(methodVisitor: MethodVisitor)
    : MethodVisitor(Opcodes.ASM5, methodVisitor) {
    override fun visitCode() {
        mv.visitVarInsn(Opcodes.ALOAD,0)
        mv.visitLdcInsn("usudhusahid")
        mv.visitFieldInsn(
                Opcodes.PUTFIELD,
                "com/android/asm/add/AddFieldActivity",
                "addField",
                "Ljava/lang/String;")
        super.visitCode()
    }
}