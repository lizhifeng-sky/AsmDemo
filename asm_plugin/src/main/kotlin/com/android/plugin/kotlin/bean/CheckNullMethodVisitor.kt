package com.android.plugin.kotlin.bean

import org.objectweb.asm.*

/**
 * @author lizhifeng
 * @date 2020/4/29 20:24
 */
class CheckNullMethodVisitor(
        val className: String?,
        val name: String,
        val paramList: MutableMap<String, String>,
        val visitMethod: MethodVisitor,
        val signature: String?)
    : MethodVisitor(Opcodes.ASM5, visitMethod) {
    private val l0 = Label()
    private val l1 = Label()
    private val l2 = Label()
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

    private fun defaultVisit(fieldName: String) {
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

    private fun managerVisit(fieldName: String) {
//        try {
//            if (this.userAge == null) {
//                throw new NullPointerException();
//            }
//        } catch (Exception var2) {
//            var2.printStackTrace();
//        }
//
//        return this.userAge;

        val errorMessage = paramList.get(fieldName)
        mv.visitTryCatchBlock(l0, l1, l2, "java/lang/Exception")

        mv.visitLabel(l0)
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                className,
                fieldName,
                "Ljava/lang/String;")
        mv.visitJumpInsn(Opcodes.IFNONNULL, l1)

        val l3 = Label()
        mv.visitLabel(l3)
//        mv.visitMethodInsn(
//                Opcodes.INVOKESTATIC,
//                "com/android/asm/bean/manager/CheckNullBeanManager",
//                "getInstance",
//                "()Lcom/android/asm/bean/manager/CheckNullBeanManager;",
//                false
//        )
//        mv.visitLdcInsn(className)
//        mv.visitLdcInsn(fieldName)
//        mv.visitLdcInsn(errorMessage)
//        mv.visitMethodInsn(
//                Opcodes.INVOKESPECIAL,
//                "com/android/asm/bean/manager/CheckNullBeanManager",
//                "throwNull",
//                "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/android/asm/bean/manager/CheckNullException;",
//                false
//        )
        mv.visitTypeInsn(Opcodes.NEW, "com/android/asm/bean/manager/CheckNullException")
        mv.visitInsn(Opcodes.DUP)
        mv.visitLdcInsn(className+"_${fieldName}_${errorMessage}")
        mv.visitLdcInsn(errorMessage)
        mv.visitMethodInsn(
                Opcodes.INVOKESPECIAL,
                "com/android/asm/bean/manager/CheckNullException",
                "<init>",
                "(Ljava/lang/String;Ljava/lang/String;)V",
                false
        )
        mv.visitInsn(Opcodes.ATHROW)

        mv.visitLabel(l1)
//        mv.visitFrame(Opcodes.F_SAME, 0, null, 1,
//                arrayOf("java/lang/Exception"))

        val l4 = Label()
        mv.visitJumpInsn(Opcodes.GOTO, l4)
        mv.visitLabel(l2)
//        mv.visitFrame(Opcodes.F_SAME1, 0, null, 1,
//                arrayOf("java/lang/Exception"))
        mv.visitVarInsn(Opcodes.ASTORE, 1)

        val l5 = Label()
        mv.visitLabel(l5)
        mv.visitVarInsn(Opcodes.ALOAD, 1)
        mv.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                "java/lang/Exception",
                "printStackTrace",
                "()V",
                false
        )

        mv.visitLabel(l4)
//        mv.visitFrame(Opcodes.F_SAME, 0, null, 1,
//                arrayOf("java/lang/Exception"))
        mv.visitVarInsn(Opcodes.ALOAD, 0)
        mv.visitFieldInsn(
                Opcodes.GETFIELD,
                className,
                fieldName,
                "Ljava/lang/String;"
        )
        mv.visitInsn(Opcodes.ARETURN)

        val l6 = Label()
        mv.visitLabel(l6)
        mv.visitLocalVariable("e",
                "Ljava/lang/Exception;",
                signature,
                l5,
                l4,
                1)
        mv.visitLocalVariable("this",
                "L$className;",
                signature,
                l0,
                l6,
                0)
        mv.visitMaxs(20, 20)
    }
}





