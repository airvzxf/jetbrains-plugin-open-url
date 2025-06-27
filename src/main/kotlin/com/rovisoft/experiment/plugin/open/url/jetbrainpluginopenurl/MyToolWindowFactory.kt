package com.rovisoft.experiment.plugin.open.url.jetbrainpluginopenurl

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ide.BrowserUtil
import com.intellij.ui.components.JBTextField
import com.intellij.ui.content.ContentFactory
import java.awt.Desktop
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JSeparator
import java.net.URI

class MyToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val contentFactory = ContentFactory.getInstance()
        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)
        panel.add(JLabel("This plugin tests open an URL in a web browser."))
        panel.add(JSeparator())

        // URL input field
        val urlPanel = JPanel()
        urlPanel.layout = BoxLayout(urlPanel, BoxLayout.X_AXIS)
        urlPanel.add(JLabel("URL: "))
        val urlTextField = JBTextField("https://accounts.google.com/o/oauth2/auth?client_id=…")
        urlPanel.add(urlTextField)
        panel.add(urlPanel)

        // "Show Message" button
        val showMessageButton = JButton("Show Message")
        showMessageButton.addActionListener {
            val url = urlTextField.text
            Messages.showMessageDialog(
                project,
                "Hi! It is my first plugin in JetBrains.\n\nURL: $url",
                "Hello World",
                Messages.getInformationIcon()
            )
        }
        panel.add(showMessageButton)

        // "Open in Browser" button
        val openBrowserButton = JButton("Open in browser | BrowserUtil.browse(url)")
        openBrowserButton.addActionListener {
            val url = urlTextField.text
            if (url.isNullOrBlank()) {
                Messages.showMessageDialog(
                    project,
                    "Please enter a URL.",
                    "Error",
                    Messages.getErrorIcon()
                )
            } else {
                BrowserUtil.browse(url)
            }
        }
        panel.add(openBrowserButton)

        // "Open in old Browser" button
        val openBrowserOldButton = JButton("Open in browser | Desktop.getDesktop().browse(uri)")
        openBrowserOldButton.addActionListener {
            val url = urlTextField.text
            if (url.isNullOrBlank()) {
                Messages.showMessageDialog(
                    project,
                    "Please enter a URL.",
                    "Error",
                    Messages.getErrorIcon()
                )
            } else {
                try {
                    val uri = URI(url)
                    Desktop.getDesktop().browse(uri)
                } catch (e: Exception) {
                    Messages.showMessageDialog(
                        project,
                        "Invalid URL: ${e.message}",
                        "Error",
                        Messages.getErrorIcon()
                    )
                }

            }
        }
        panel.add(openBrowserOldButton)

        val content = contentFactory.createContent(panel, "", false)
        toolWindow.contentManager.addContent(content)
    }
}