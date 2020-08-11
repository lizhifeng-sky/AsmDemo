package com.android.plugin.kotlin

import com.android.plugin.kotlin.add.AddNewFieldVisitor
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

    override fun visit(version: Int,
                       access: Int,
                       name: String?,
                       signature: String?,
                       superName: String?,
                       interfaces: Array<out String>?) {
        className = name
        super.visit(version, access, name, signature, superName, interfaces)
    }

    override fun visitField(access: Int, name: String?,
                            descriptor: String?, signature: String?,
                            value: Any?): FieldVisitor {
        if (className.equals("com/android/asm/add/AddFieldActivity")){
            if (name.equals(fieldName)){
                isFieldPresent=true
            }
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
        if (className.equals("com/android/asm/add/AddFieldActivity")) {
            if (name.equals("<init>")){
                cv.visitField(
                        Opcodes.ACC_PUBLIC,
                        fieldName,
                        "Ljava/lang/String;",
                        null,
                        null)
                        .visitEnd()
                return AddNewFieldVisitor(visitMethod)
            }
        }
        return visitMethod
    }
}





