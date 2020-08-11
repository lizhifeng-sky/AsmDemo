package com.android.plugin.kotlin.bean

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.Opcodes


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

class CheckNullFieldValueVisitor(val fieldName: String,
                                 val paramList: MutableMap<String, String>,
                                 annotationVisitor: AnnotationVisitor)
    : AnnotationVisitor(Opcodes.ASM5, annotationVisitor) {
    override fun visit(name: String?, value: Any?) {
        println("NullVisitor    value     visit   $name  $value")
        paramList[fieldName] = value as String
        super.visit(name, value)
    }
}

