package org.openapitools.diff.gradle.plugin.extensions

import org.gradle.api.Project
import org.gradle.kotlin.dsl.property

open class OpenApiDiffExtension(project: Project) {

    val oldSpec = project.objects.property<String>()

    val newSpec = project.objects.property<String>()

    val failOnIncompatible = project.objects.property<Boolean>().convention(false)

    val failOnChanged = project.objects.property<Boolean>().convention(false)

    val skip = project.objects.property<Boolean>().convention(false)

//    val consoleOutputFileName = project.objects.property<String>()
//
//    val jsonOutputFileName = project.objects.property<String>()
//
//    val markdownOutputFileName = project.objects.property<String>()
//
//    val asciidocOutputFileName = project.objects.property<String>()

    // TODO List<File> configFiles

    // TODO Map<String, String> configProps
}