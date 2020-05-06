package com.android.plugin.kotlin

import org.objectweb.asm.*

/**
 * @author lizhifeng
 * @date 2020/4/29 20:24
 */
class AddClassVisitor(classWriter: ClassWriter)
    : ClassVisitor(Opcodes.ASM5, classWriter) {
    private var className: String? = null
    private var isFieldPresent=false
    private val fieldName="addField"
    private var fieldSignature:String?=null
    override fun visit(version: Int,
                       access: Int,
                       name: String?,
                       signature: String?,
                       superName: String?,
                       interfaces: Array<out String>?) {

        className = name

        println(">>>>>> addVisitor  " + className + "    " + name)
//        if (className.equals("com/android/asm/add/AddClassActivity")) {
////            if (name.equals("onCreate")) {
////                println(">>>>>> name   "+name)
////                return ExecStaticMethodVisitor(methodVisitor)
////            }
//        } else
//        if (className.equals("com/android/asm/add/AddFieldActivity")) {
//            val addNewFieldVisitor = AddNewFieldVisitor(classWriter)
//            addNewFieldVisitor.visit(version, access, name,
//                    signature, superName, interfaces)
//            return
//        }else{
            super.visit(version, access, name,
                    signature, superName, interfaces)
//        }
//        else if (className.equals("com/android/asm/add/AddMethodActivity")) {
//            if (name.equals("onResume")) {
//                println(">>>>>> name   " + name)
//                return ExecFieldMethodVisitor(methodVisitor)
//            }
//        }

    }

    override fun visitField(access: Int,
                            name: String?,
                            descriptor: String?,
                            signature: String?,
                            value: Any?): FieldVisitor {
        if (className.equals("com/android/asm/add/AddFieldActivity")) {
            println("+++++++++AddNewFieldVisitor " + name)
            if (name.equals(fieldName)) {
                isFieldPresent = true
            }else{
                fieldSignature=signature
            }
        }
        return super.visitField(access, name, descriptor, signature, value)
    }

    override fun visitEnd() {
        if (className.equals("com/android/asm/add/AddFieldActivity")) {
            if (!isFieldPresent) {
                val visitField = cv.visitField(
                        Opcodes.ACC_PUBLIC,
                        fieldName,
                        "Ljava/lang/String;",
                        null,
                        fieldName)
                visitField?.let {
                    visitField.visitEnd()
                }
            }
        }
        super.visitEnd()
    }

}





