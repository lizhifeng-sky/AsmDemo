package com.android.plugin.kotlin.bean

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.Opcodes

class CheckNullFieldVisitor(
        val fieldName: String,
        val paramList: MutableMap<String, String>,
        fieldVisitor: FieldVisitor)
    : FieldVisitor(Opcodes.ASM5, fieldVisitor) {
    override fun visitAnnotation(descriptor: String?, p1: Boolean): AnnotationVisitor {
        println("NullVisitor    field     visitAnnotation  $descriptor")
        val visitAnnotation = super.visitAnnotation(descriptor, p1)
        if (descriptor == "Lcom/android/asm/bean/CheckNull;") {
            return CheckNullFieldValueVisitor(fieldName, paramList, visitAnnotation)
        }
        return visitAnnotation
    }
}

