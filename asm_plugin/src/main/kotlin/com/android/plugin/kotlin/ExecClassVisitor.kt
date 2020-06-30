package com.android.plugin.kotlin

import com.android.plugin.kotlin.exec.ExecFieldMethodVisitor
import com.android.plugin.kotlin.exec.ExecThisMethodVisitor
import com.android.plugin.kotlin.exec.ExecStaticMethodVisitor
import jdk.internal.org.objectweb.asm.ClassVisitor
import jdk.internal.org.objectweb.asm.MethodVisitor
import jdk.internal.org.objectweb.asm.Opcodes


/**
 * @author lizhifeng
 * @date 2020/4/29 20:24
 */
class ExecClassVisitor(classVisitor: ClassVisitor)
    : ClassVisitor(Opcodes.ASM5, classVisitor) {
    private var className: String? = null
    override fun visit(version: Int,
                       access: Int,
                       name: String?,
                       signature: String?,
                       superName: String?,
                       interfaces: Array<out String>?) {
        super.visit(version, access, name,
                signature, superName, interfaces)
        className = name
    }

    override fun visitMethod(
            access: Int,
            name: String?,
            descriptor: String?,
            signature: String?,
            exceptions: Array<out String>?
    ): MethodVisitor {
        val methodVisitor = super.visitMethod(access,
                name, descriptor,
                signature, exceptions)
        println(">>>>>>    " + className + "  methodName " + name)
        if (className.equals("com/android/asm/exec/ExecStaticActivity")) {
            println(">>>>>> name   " + name)
            if (name.equals("onCreate")) {
                return ExecStaticMethodVisitor(methodVisitor)
            }
            if (name.equals("onTestCatch")) {
                return TryCatch(Opcodes.ASM5,methodVisitor,access,name,descriptor)
            }
        } else if (className.equals("com/android/asm/exec/ExecThisActivity")) {
            if (name.equals("onResume")) {
                println(">>>>>> name   " + name)
                return ExecThisMethodVisitor(methodVisitor)
            }
        } else if (className.equals("com/android/asm/exec/ExecFieldActivity")) {
            if (name.equals("onResume")) {
                println(">>>>>> name   " + name)
                return ExecFieldMethodVisitor(methodVisitor)
            }
        }
        return methodVisitor
    }
}





