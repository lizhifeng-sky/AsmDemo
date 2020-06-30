package com.android.plugin.kotlin.exec

import jdk.internal.org.objectweb.asm.MethodVisitor
import jdk.internal.org.objectweb.asm.Opcodes

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


class ExecThisMethodVisitor(methodVisitor: MethodVisitor)
    : MethodVisitor(Opcodes.ASM5, methodVisitor) {
    override fun visitCode() {
        println(">>>>>> ExecThisMethodVisitor ")

      /*  public void onTestExec(String test){
            Toast.makeText(this,test,Toast.LENGTH_LONG).show();
        }*/

//        mv.visitVarInsn(Opcodes.ALOAD,0)
//        mv.visitLdcInsn("public void onTestExec(String test){\n" +
//                "            Toast.makeText(this,test,Toast.LENGTH_LONG).show();\n" +
//                "        }")
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL,
//                "com/android/asm/exec/ExecThisActivity",
//                "onTestExec",
//                "(Ljava/lang/String)V",
//                false)


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

