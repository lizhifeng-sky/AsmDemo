package com.android.plugin.kotlin.exec

/**
 * @author lizhifeng
 * @date 2020/4/29 20:36
 * 插入执行 this.method()
 *
 *  /**
 * descriptor
 * 无参数方法->()
 * 有参数方法->((Ljava/lang/String;Ljava/lang/String;)
 *
 * 方法返回类型
 * Integer 'I'
 * Void 'V'
 * Boolean 'Z'
 * Byte 'B'
 * Character 'C'
 * Short 'S'
 * Double 'D'
 * Float 'F'
 * Long 'J';
 * Object 'Ljava/lang/Object'
 * Object[] '[[Ljava/lang/Object'
 * int[] '[I'
 * */
 */
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class ExecThisMethodVisitor(methodVisitor: MethodVisitor)
    : MethodVisitor(Opcodes.ASM5, methodVisitor) {
    override fun visitCode() {
        println(">>>>>> ExecThisMethodVisitor ")
        //visit this
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        //visit method
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                "android/app/Activity",
                "finish",
                "()V",
                false
        )
        super.visitCode()
    }
}

