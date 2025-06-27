package com.rovisoft.experiment.plugin.open.url.jetbrainpluginopenurl

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class MyAction : AnAction("Hello World Action") {
    override fun actionPerformed(e: AnActionEvent) {
        Messages.showMessageDialog(e.project, "Hello from My First JetBrains Plugin!", "Hello World", Messages.getInformationIcon())
    }
}
