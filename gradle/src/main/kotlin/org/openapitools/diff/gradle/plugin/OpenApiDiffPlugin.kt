package org.openapitools.diff.gradle.plugin

import org.gradle.api.Project
import org.gradle.api.Plugin
import org.openapitools.diff.gradle.plugin.extensions.OpenApiDiffExtension
import org.openapitools.diff.gradle.plugin.tasks.ValidateTask

class OpenApiDiffPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.run {
            val validate = extensions.create(
                "openApiDiff",
                OpenApiDiffExtension::class.java,
                project

            )

            tasks.apply {
                register("openApiDiffValidate", ValidateTask::class.java).configure {
                    group = PLUGIN_GROUP
                    description = "Validate there are no breaking changes in the new specification."

                    oldSpec.set(validate.oldSpec)
                    newSpec.set(validate.newSpec)
                    failOnIncompatible.set(validate.failOnIncompatible)
                    failOnChanged.set(validate.failOnChanged)
                    skip.set(validate.skip)
                    //                    consoleOutputFileName.set(validate.consoleOutputFileName)
                    //                    jsonOutputFileName.set(validate.jsonOutputFileName)
                    //                    markdownOutputFileName.set(validate.markdownOutputFileName)
                    //                    asciidocOutputFileName.set(validate.asciidocOutputFileName)
                }
            }
        }
    }

    companion object {
        const val PLUGIN_GROUP = "OpenAPI Tools"
    }
}
