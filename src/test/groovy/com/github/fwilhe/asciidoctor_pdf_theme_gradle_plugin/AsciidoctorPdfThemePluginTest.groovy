package com.github.fwilhe.asciidoctor_pdf_theme_gradle_plugin

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test
import static org.junit.Assert.*

class AsciidoctorPdfThemePluginTest {

    @Test
    public void 'Plugin adds getTheme Task to Project'() {
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'org.asciidoctor.convert'
        project.pluginManager.apply AsciidoctorPdfThemePlugin

        assertNotNull(project.tasks.getTheme)
    }

}
