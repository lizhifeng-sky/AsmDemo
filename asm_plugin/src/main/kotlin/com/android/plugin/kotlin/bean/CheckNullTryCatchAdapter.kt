package com.android.plugin.kotlin.bean

import com.android.plugin.kotlin.annotation.CatchAnnotationVisitor
import com.android.plugin.kotlin.annotation.ThreadAnnotationVisitor
import com.android.plugin.kotlin.annotation.TimerAnnotationVisitor
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Type
import org.objectweb.asm.commons.AdviceAdapter
import org.objectweb.asm.Opcodes

/**
 * @author lizhifeng
 * @date 2020/6/30 15:17
 */
class CheckNullTryCatchAdapter(api: Int,
                               mv: MethodVisitor?,
                               access: Int,
                               name: String?,
                               val desc: String?) : AdviceAdapter(api, mv, access, name, desc) {
    private val catchStart = Label()
    private val catchEnd = Label()
    private val catchHandler = Label()
    private var catchAnnotationVisitor: CatchAnnotationVisitor? = null


    override fun visitAnnotation(desc: String?, visible: Boolean): AnnotationVisitor {
        val visitAnnotation = super.visitAnnotation(desc, visible)
        desc?.let {
            println("我要找的 注解啊$desc")
            if (desc == "Lcom/android/asm/annotation/Catch;") {
                catchAnnotationVisitor = CatchAnnotationVisitor(api, visitAnnotation)
                return catchAnnotationVisitor!!
            }
        }
        return visitAnnotation
    }

    override fun onMethodEnter() {
        super.onMethodEnter()
        catchAnnotationVisitor?.let {
            mv.visitLabel(catchStart)
            mv.visitTryCatchBlock(catchStart, catchEnd, catchHandler, "java/lang/Exception")
        }
    }

    override fun visitMaxs(maxStack: Int, maxLocals: Int) {
        catchAnnotationVisitor?.let {
            //标志：try块结束   
            mv.visitLabel(catchEnd)
            //标志：catch块开始位置  
            mv.visitLabel(catchHandler)
            mv.visitFrame(Opcodes.F_SAME1, 0, null, 1,
                    arrayOf("java/lang/Exception"))

            //异常信息保存到局部变量   
            val local = newLocal(Type.LONG_TYPE)
            mv.visitVarInsn(ASTORE, local)
            mv.visitVarInsn(ALOAD, local)
            //抛出异常  
//        mv.visitInsn(ATHROW)
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
                    "java/lang/Exception",
                    "printStackTrace",
                    "()V",
                    false
            )
            mv.visitInsn(Opcodes.RETURN)
        }
        super.visitMaxs(maxStack, maxLocals)
    }
}

