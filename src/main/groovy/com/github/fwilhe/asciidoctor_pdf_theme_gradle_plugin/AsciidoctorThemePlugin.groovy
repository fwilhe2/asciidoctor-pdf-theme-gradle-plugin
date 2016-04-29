package com.github.fwilhe.asciidoctor_pdf_theme_gradle_plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class AsciidoctorThemePlugin implements Plugin<Project> {
    void apply(Project project) {
        project.apply(plugin: 'org.asciidoctor.convert')

        project.extensions.create("theme", AsciidoctorThemePluginExtension)

        project.task('getTheme') << {

            def path = System.getProperty("java.io.tmpdir")

            if (new File("${path}/${project.theme.name}").isDirectory()) {
                println "Theme is installed, updating."
                "git -C ${path}/${project.theme.name} pull".execute()
            } else {
                println "Theme not installed, getting it from remote."
                "git clone ${project.theme.url} ${path}/${project.theme.name}".execute()
            }

            project.tasks.asciidoctor.property('attributes')['pdf-stylesdir'] = "${path}/${project.theme.name}/resources/themes/"
            project.tasks.asciidoctor.property('attributes')['pdf-style'] = project.theme.name
            project.tasks.asciidoctor.property('attributes')['pdf-fontsdir'] = "${path}/${project.theme.name}/resources/fonts/"
        }

        project.tasks.asciidoctor.dependsOn project.tasks.getTheme
    }
}
