package com.android.plugin.kotlin.add


import org.objectweb.asm.*

/**
 * @author lizhifeng
 * @date 2020/5/6 10:54
 * 创建 一个变量
 * 基本数据类型 变量
 * 引用类型 变量
 * 静态常量
 *
 */
class AddNewFieldVisitor(classWriter: ClassWriter)
    : ClassVisitor(Opcodes.ASM5, classWriter) {
    private var isFieldPresent=false
    private val fieldName="addField"
    override fun visitField(access: Int,
                            name: String?,
                            descriptor: String?,
                            signature: String?,
                            value: Any?): FieldVisitor {
        println("+++++++++AddNewFieldVisitor "+name)
        if (name.equals(fieldName)){
            isFieldPresent=true
        }
        return super.visitField(access, name, descriptor, signature, value)
    }

    override fun visitEnd() {
        if (!isFieldPresent){
            val visitField = cv.visitField(
                    Opcodes.ACC_PUBLIC,
                    fieldName,
                    "Ljava/lang/String",
                    null,
                    "我是fieldValue")
            visitField?.let {
                visitField.visitEnd()
            }
        }
        super.visitEnd()
    }
}