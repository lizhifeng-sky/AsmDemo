package com.android.plugin.kotlin

import jdk.internal.org.objectweb.asm.Label
import jdk.internal.org.objectweb.asm.MethodVisitor
import jdk.internal.org.objectweb.asm.Type
import jdk.internal.org.objectweb.asm.commons.AdviceAdapter
import org.objectweb.asm.Opcodes

/**
 * @author lizhifeng
 * @date 2020/6/30 15:17
 */
class TryCatch(api: Int,
               mv: MethodVisitor?,
               access: Int,
               name: String?,
               desc: String?) : AdviceAdapter(api, mv, access, name, desc) {
    private val start = Label()
    private val end = Label()
    private val handler = Label()

    override fun onMethodEnter() {
        super.onMethodEnter()
        mv.visitLabel(start)
        mv.visitTryCatchBlock(start, end, handler, "java/lang/Exception")
    }

    override fun visitMaxs(maxStack: Int, maxLocals: Int) {
        //标志：try块结束   
        mv.visitLabel(end)
        //标志：catch块开始位置  
        mv.visitLabel(handler);
        mv.visitFrame(Opcodes.F_SAME1, 0, null, 1,
                arrayOf("java/lang/Exception"))
        //异常信息保存到局部变量   
        val local = newLocal(Type.LONG_TYPE)
        mv.visitVarInsn(ASTORE, local)
        //抛出异常   
        mv.visitVarInsn(ALOAD, local)
        mv.visitInsn(ATHROW)
        super.visitMaxs(maxStack, maxLocals)

    }
}

