package com.android.plugin.kotlin

import com.android.plugin.kotlin.exec.ExecFieldMethodVisitor
import com.android.plugin.kotlin.exec.ExecThisMethodVisitor
import com.android.plugin.kotlin.exec.ExecStaticMethodVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * @author lizhifeng
 * @date 2020/4/29 20:24
 */
class ExecClassVisitor (classVisitor: ClassVisitor)
    : ClassVisitor(Opcodes.ASM5, classVisitor){
    private var className:String?=null
    private var isFieldPresent=false
    private val fieldName="addField"
    private var fieldSignature:String?=null
    override fun visit(version: Int,
                       access: Int,
                       name: String?,
                       signature: String?,
                       superName: String?,
                       interfaces: Array<out String>?) {
        super.visit(version, access, name,
                signature, superName, interfaces)
        className=name
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
        println(">>>>>>    "+className  +"  methodName "+name)
        if (className.equals("com/android/asm/exec/ExecStaticActivity")) {
            if (name.equals("onCreate")) {
                println(">>>>>> name   "+name)
                return ExecStaticMethodVisitor(methodVisitor)
            }
        }else if (className.equals("com/android/asm/exec/ExecThisActivity")){
            if (name.equals("onResume")){
                println(">>>>>> name   "+name)
                return ExecThisMethodVisitor(methodVisitor)
            }
        }else if (className.equals("com/android/asm/exec/ExecFieldActivity")){
            if (name.equals("onResume")){
                println(">>>>>> name   "+name)
                return ExecFieldMethodVisitor(methodVisitor)
            }
        } else
            if (className.equals("com/android/asm/add/AddFieldActivity")) {
            if (name.equals("onCreate")) {
                if (!isFieldPresent) {
//                    val visitField = cv.visitField(
//                            Opcodes.ACC_PUBLIC,
//                            fieldName,
//                            "Ljava/lang/String;",
//                            null,
//                            fieldName)

//                ALOAD 0
//                LDC "text"
//                PUTFIELD com/android/asm/add/AddFieldActivity.text
//                : Ljava/lang/String;
//                RETURN

                    //        mv.visitVarInsn(ALOAD, 2);
//        mv.visitFieldInsn(GETSTATIC, "com/lucky/lib/studyapp/Main2Activity", "MESSAGE_KEY", "I");
//        mv.visitFieldInsn(PUTFIELD, "android/os/Message", "what", "I");
                    methodVisitor.visitVarInsn(Opcodes.ALOAD,0)
                    methodVisitor.visitLdcInsn("这是初始化的赋值")
                    methodVisitor.visitFieldInsn(Opcodes.PUTFIELD,
                            "com/android/asm/add/AddFieldActivity",
                            fieldName,
                            "Ljava/lang/String;")
//                    visitField?.let {
//                        visitField.visitEnd()
//                    }
                }
            }
        }


        return methodVisitor
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

//                ALOAD 0
//                LDC "text"
//                PUTFIELD com/android/asm/add/AddFieldActivity.text
//                : Ljava/lang/String;
//                RETURN

                visitField?.let {
                    visitField.visitEnd()
                }
            }
        }
        super.visitEnd()
    }
}





