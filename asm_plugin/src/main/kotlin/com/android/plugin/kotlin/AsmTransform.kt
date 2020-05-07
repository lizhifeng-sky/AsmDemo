package com.android.plugin.kotlin

/**
 * @author lizhifeng
 * @date 2020/4/29 20:37
 */

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.AppExtension
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.plugin.kotlin.add.AddNewClassVisitor
import com.android.utils.FileUtils
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import java.io.FileOutputStream


class AsmTransform : Transform(), Plugin<Project> {

    override fun apply(project: Project) {
        println(">>>>>> 1.1.2 this is a log234 just from DemoTransform")
        val appExtension = project.extensions.getByType(AppExtension::class.java)
        appExtension.registerTransform(this)
    }

    override fun getName(): String {
        return "KotlinDemoTransform"
    }

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {
        return TransformManager.CONTENT_CLASS
    }

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    override fun isIncremental(): Boolean {
        return false
    }

    override fun transform(transformInvocation: TransformInvocation?) {

        val inputs = transformInvocation?.inputs
        val outputProvider = transformInvocation?.outputProvider

        if (!isIncremental) {
            outputProvider?.deleteAll()
        }

        inputs?.forEach { it ->
            it.directoryInputs.forEach {
                if (it.file.isDirectory) {
                    FileUtils.getAllFiles(it.file).forEach {
                        val file = it
                        val name = file.name
                        if (name.endsWith(".class")
                                && name != ("R.class")
                                && !name.startsWith("R\$")
                                && name != ("BuildConfig.class")) {

                            val classPath = file.absolutePath
                            println()
                            println()
                            println()
                            println(">>>>>> classPath :$classPath")

                            val cr = ClassReader(file.readBytes())
                            val cw = ClassWriter(cr, ClassWriter.COMPUTE_MAXS)
                            if (classPath.contains("exec")) {
                                val addVisitor = ExecClassVisitor(cw)
                                cr.accept(addVisitor, ClassReader.EXPAND_FRAMES)
                            } else {
                                val addVisitor = AddClassVisitor(cw)
                                cr.accept(addVisitor, ClassReader.EXPAND_FRAMES)
                            }
                            val bytes = cw.toByteArray()

                            try {
                                val fos = FileOutputStream(classPath)
                                fos.write(bytes)
                                fos.close()
                            } catch (e: Exception) {
                                println(">>>>>> classPath :${e.message}")
                            }
                        }
                    }
                }

                val dest = outputProvider?.getContentLocation(
                        it.name,
                        it.contentTypes,
                        it.scopes,
                        Format.DIRECTORY
                )
                FileUtils.copyDirectoryToDirectory(it.file, dest)
            }

            //  !!!!!!!!!! !!!!!!!!!! !!!!!!!!!! !!!!!!!!!! !!!!!!!!!!
            //使用androidx的项目一定也注意jar也需要处理，否则所有的jar都不会最终编译到apk中，千万注意
            //导致出现ClassNotFoundException的崩溃信息，
            //当然主要是因为找不到父类，因为父类AppCompatActivity在jar中
            it.jarInputs.forEach {
                val dest = outputProvider?.getContentLocation(
                        it.name,
                        it.contentTypes,
                        it.scopes,
                        Format.JAR
                )
                FileUtils.copyFile(it.file, dest)
            }
        }

        super.transform(transformInvocation)
    }

}

