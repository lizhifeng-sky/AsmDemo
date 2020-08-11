package com.android.plugin.kotlin.bean

import org.objectweb.asm.*

/**
 * @author lizhifeng
 * @date 2020/4/29 20:24
 */
class CheckNullClassVisitor(classWriter: ClassWriter)
    : ClassVisitor(Opcodes.ASM5, classWriter) {
    private var className: String? = null
    private var isClassAnnnotaiton = false
    var paramList = mutableMapOf<String, String>()
    override fun visit(version: Int,
                       access: Int,
                       name: String?,
                       signature: String?,
                       superName: String?,
                       interfaces: Array<out String>?) {
        println("NullVisitor    class     visit  $name")
        className = name
        super.visit(version, access, name, signature, superName, interfaces)
    }

    override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor {
        println("NullVisitor    class    visitAnnotation")
        val visitAnnotation = super.visitAnnotation(descriptor, visible)
        descriptor?.let {
            println("CheckNullClassVisitor 我要找的 注解啊$descriptor")
            if (descriptor == "Lcom/android/asm.bean/Bean;") {
                isClassAnnnotaiton = true
            }
        }
        return visitAnnotation
    }

    override fun visitField(access: Int, name: String?,
                            descriptor: String?, signature: String?,
                            value: Any?): FieldVisitor {
        println("NullVisitor    class  visitField  $name")
        val visitField = super.visitField(access, name, descriptor, signature, value)
        name?.let {
            return CheckNullFieldVisitor(name, paramList, visitField)
        }
        return super.visitField(access, name, descriptor, signature, value)
    }

    override fun visitMethod(
            access: Int,
            name: String?,
            descriptor: String?,
            signature: String?,
            exceptions: Array<out String>?): MethodVisitor {
        val visitMethod = super.visitMethod(access,
                name,
                descriptor,
                signature,
                exceptions)

        println("NullVisitor     class     visitMethod  $name")
//        paramList.forEach({
//            println("NullVisitor 注解的字段   ${it.key}     ${it.value}")
//        })
        name?.let {
            if (name.startsWith("get")) {
//                val methodConvertFieldName = name.substring(3)
//                var fieldName:String?=null
//                paramList.forEach {
//                    println("NullVisitor 注解的字段 $methodConvertFieldName  ${it.key}  ${it.value}")
//                    if (methodConvertFieldName.equals(it.key,true)){
//                        fieldName=it.key
//                        return@forEach
//                    }
//                }
//                println("NullVisitor 注解的字段   $fieldName")
//                if (paramList.containsKey(fieldName)) {
//                    visitMethod.visitLdcInsn("TAG")
//                    visitMethod.visitLdcInsn("===== 我的插入执行 static.method=====")
//                    visitMethod.visitMethodInsn(
//                            Opcodes.INVOKESTATIC,
//                            "android/util/Log",
//                            "e",
//                            "(Ljava/lang/String;Ljava/lang/String;)I",
//                            false
//                    )
//                    visitMethod.visitInsn(Opcodes.POP)
//
//                    val errorMessage = paramList.get(fieldName)
//                    visitMethod.visitVarInsn(Opcodes.ALOAD, 0)
//                    visitMethod.visitFieldInsn(
//                            Opcodes.GETFIELD,
//                            className,
//                            fieldName,
//                            "Ljava/lang/String;")
//
//                    visitMethod.visitInsn(Opcodes.IFNONNULL)
//                    visitMethod.visitTypeInsn(Opcodes.NEW,
//                            "java/lang/NullPointerException")
//
//                    visitMethod.visitInsn(Opcodes.DUP)
//                    visitMethod.visitLdcInsn(errorMessage)
//                    visitMethod.visitMethodInsn(
//                            Opcodes.INVOKESPECIAL,
//                            "java/lang/NullPointerException",
//                            "<init>",
//                            "(Ljava/lang/String;)V",
//                            false
//                    )
//                    visitMethod.visitInsn(Opcodes.ATHROW)
//
//                    visitMethod.visitVarInsn(Opcodes.ALOAD, 0)
//                    visitMethod.visitFieldInsn(
//                            Opcodes.GETFIELD,
//                            className,
//                            fieldName,
//                            "Ljava/lang/String;")
//                    visitMethod.visitInsn(Opcodes.ARETURN)
//
//                    println("NullVisitor 改造的方法   $name")
//                }
                return CheckNullMethodVisitor(className,name,paramList,visitMethod,signature)
            }
        }
        return visitMethod
    }
}





