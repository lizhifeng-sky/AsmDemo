package com.android.plugin.kotlin.annotation

import org.objectweb.asm.AnnotationVisitor

/**
 * @author lizhifeng
 * @date 2020/7/1 10:54
 */
class TimerAnnotationVisitor(api:Int,
                             av:AnnotationVisitor) :AnnotationVisitor(api,av){

    override fun visit(paramName: String?, paramValue: Any?) {
        super.visit(paramName, paramName)
        println("Timer Annotation 来了啊")
    }
}