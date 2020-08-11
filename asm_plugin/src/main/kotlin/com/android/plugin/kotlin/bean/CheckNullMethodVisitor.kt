package com.android.plugin.kotlin.bean

import com.android.plugin.kotlin.annotation.CatchAnnotationVisitor
import org.objectweb.asm.*

/**
 * @author lizhifeng
 * @date 2020/4/29 20:24
 */
class CheckNullMethodVisitor(
        val className: String?,
        val name: String,
        val paramList: MutableMap<String, String>,
        val visitMethod: MethodVisitor)
    : MethodVisitor(Opcodes.ASM5, visitMethod) {
    override fun visitCode() {
        val methodConvertFieldName = name.substring(3)
        var fieldName: String? = null
        paramList.forEach {
            println("NullVisitor 注解的字段 $methodConvertFieldName  ${it.key}  ${it.value}")
            if (methodConvertFieldName.equals(it.key, true)) {
                fieldName = it.key
                return@forEach
            }
        }
        println("NullVisitor 注解的字段   $fieldName")
        if (paramList.containsKey(fieldName)) {
            fieldName?.let {
//                defaultVisit(fieldName!!)
                managerVisit(fieldName!!)
            }

        }
        super.visitCode()
    }

    private fun defaultVisit(fieldName:String) {
//            public String getUserName() {
//                if (this.userName == null) {
//                    throw new NullPointerException("用户名不能为null");
//                } else {
//                    return this.userName;
//                }
//            }

        val errorMessage = paramList.get(fieldName)
        visitMethod.visitVarInsn(Opcodes.ALOAD, 0)
        visitMethod.visitFieldInsn(
                Opcodes.GETFIELD,
                className,
                fieldName,
                "Ljava/lang/String;")

        val label = Label()
        visitMethod.visitJumpInsn(Opcodes.IFNONNULL, label)
        visitMethod.visitTypeInsn(Opcodes.NEW,
                "java/lang/NullPointerException")
        visitMethod.visitInsn(Opcodes.DUP)
        visitMethod.visitLdcInsn(errorMessage)
        visitMethod.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                "java/lang/NullPointerException",
                "<init>",
                "(Ljava/lang/String;)V",
                false
        )
        visitMethod.visitInsn(Opcodes.ATHROW)
        visitMethod.visitLabel(label)
    }

    private fun managerVisit(fieldName:String) {
//            public String getUserName() {
//                if (this.userName == null) {
//                    throw CheckNullBeanManager
//                          .getInstance()
//                          .throwNull(className,fieldName,errorMessage);
//                } else {
//                    return this.userName;
//                }
//            }

        val errorMessage = paramList.get(fieldName)
        visitMethod.visitVarInsn(Opcodes.ALOAD, 0)
        visitMethod.visitFieldInsn(
                Opcodes.GETFIELD,
                className,
                fieldName,
                "Ljava/lang/String;")

        val label = Label()
        visitMethod.visitJumpInsn(Opcodes.IFNONNULL, label)

        visitMethod.visitMethodInsn(
                Opcodes.INVOKESTATIC,
                "com/android/asm/bean/manager/CheckNullBeanManager",
                "getInstance",
                "()Lcom/android/asm/bean/manager/CheckNullBeanManager;",
                false
        )
        visitMethod.visitLdcInsn(className)
        visitMethod.visitLdcInsn(fieldName)
        visitMethod.visitLdcInsn(errorMessage)
        visitMethod.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                "com/android/asm/bean/manager/CheckNullBeanManager",
                "throwNull",
                "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/NullPointerException;",
                false
        )
        visitMethod.visitInsn(Opcodes.ATHROW)
        visitMethod.visitLabel(label)
    }
}





