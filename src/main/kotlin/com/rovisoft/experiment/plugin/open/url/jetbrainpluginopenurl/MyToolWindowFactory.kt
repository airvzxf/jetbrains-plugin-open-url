package com.rovisoft.experiment.plugin.open.url.jetbrainpluginopenurl

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel

class MyToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val contentFactory = ContentFactory.getInstance()
        val panel = JPanel()
        panel.add(JLabel("Hello, world"))

        val button = JButton("Show Message")
        button.addActionListener {
            Messages.showMessageDialog(
                project,
                "¡Hola desde Mi Primer Plugin para RustRover!",
                "Hello World",
                Messages.getInformationIcon()
            )
        }
        panel.add(button)

        val content = contentFactory.createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)
    }
}
