package org.openapitools.diff.gradle.plugin.extensions

import org.gradle.api.Project
import org.gradle.kotlin.dsl.property

open class OpenApiDiffValidateExtension(project: Project) {

    val oldSpec = project.objects.property<String>()

    val newSpec = project.objects.property<String>()
}