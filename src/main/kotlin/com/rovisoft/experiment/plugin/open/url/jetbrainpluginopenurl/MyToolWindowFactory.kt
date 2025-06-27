package com.rovisoft.experiment.plugin.open.url.jetbrainpluginopenurl

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JSeparator

class MyToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val contentFactory = ContentFactory.getInstance()
        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        panel.add(JLabel("This plugin tests the `BrowserUtil.browse(uri)`."))
        panel.add(JSeparator())

        val button = JButton("Show Message")
        button.addActionListener {
            Messages.showMessageDialog(
                project,
                "Hi, from my first Plugin in JetBrains!",
                "Hello World",
                Messages.getInformationIcon()
            )
        }
        panel.add(button)

        val content = contentFactory.createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)
    }
}
