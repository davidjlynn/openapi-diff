package org.openapitools.diff.gradle.plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.*
import org.gradle.kotlin.dsl.property
import org.openapitools.openapidiff.core.OpenApiCompare
import org.openapitools.openapidiff.core.compare.OpenApiDiffOptions

open class ValidateTask : DefaultTask() {

    @get:InputFile
    @PathSensitive(PathSensitivity.RELATIVE)
    val oldSpec = project.objects.property<String>()

    @get:InputFile
    @PathSensitive(PathSensitivity.RELATIVE)
    val newSpec = project.objects.property<String>()

    @Optional
    @Input
    val failOnIncompatible = project.objects.property<Boolean>().convention(false)

    @Optional
    @Input
    val failOnChanged = project.objects.property<Boolean>().convention(false)

    @Optional
    @Input
    val skip = project.objects.property<Boolean>().convention(false)

    //    @Optional
    //    @Input
    //    val consoleOutputFileName = project.objects.property<String>()
    //
    //    @Optional
    //    @Input
    //    val jsonOutputFileName = project.objects.property<String>()
    //
    //    @Optional
    //    @Input
    //    val markdownOutputFileName = project.objects.property<String>()
    //
    //    @Optional
    //    @Input
    //    val asciidocOutputFileName = project.objects.property<String>()

    // TODO List<File> configFiles

    // TODO Map<String, String> configProps

    @TaskAction
    fun doWork() {
        val logger = Logging.getLogger(javaClass)

        val inputOldSpec = oldSpec.get()
        val inputNewSpec = newSpec.get()
        val inputFailOnIncompatible = failOnIncompatible.get()
        val inputFailOnChanged = failOnChanged.get()
        val inputSkip = skip.get()

        logger.quiet("Checking OpenAPI Diff for validation, comparing $inputOldSpec and $inputNewSpec")

        if (inputSkip) {
            logger.info("Skipping openapi-diff execution")
            return
        }

        val options = OpenApiDiffOptions.builder().build()

        val diff = OpenApiCompare.fromLocations(inputOldSpec, inputNewSpec, null, options)

        if (inputFailOnIncompatible && diff.isIncompatible()){
            throw GradleException("The API changes broke backward compatibility")
        }

        if (inputFailOnChanged && diff.isDifferent()) {
            throw GradleException("The API changed");
        }
    }
}