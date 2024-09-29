package org.openapitools.diff.gradle.plugin.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.api.tasks.TaskAction
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

    @TaskAction
    fun doWork() {
        val logger = Logging.getLogger(javaClass)

        val old = oldSpec.get()
        val new = newSpec.get()

        logger.quiet("Checking OpenAPI Diff for validation, comparing $old and $new")

        val options = OpenApiDiffOptions.builder().build()

        val diff = OpenApiCompare.fromLocations(old, new, null, options)

        if (diff.isIncompatible()){
            throw GradleException("Breaking change.")
        }
    }
}