package com.error_search;

import com.intellij.analysis.problemsView.Problem;
import com.intellij.analysis.problemsView.ProblemsCollector;
import com.intellij.analysis.problemsView.toolWindow.ProblemNode;
import com.intellij.analysis.problemsView.toolWindow.ProblemsViewPanel;
import com.intellij.ide.BrowserUtil;
import com.intellij.ide.ProjectGroupActionGroup;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;

import java.util.Collection;
import java.util.Objects;

import static com.intellij.openapi.actionSystem.PlatformCoreDataKeys.SELECTED_ITEM;

public class SearchErrorAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent event) {
        Project project = event.getProject();
        ProblemNode node = (ProblemNode) event.getData(SELECTED_ITEM);
        if (project == null || node == null) return;

        PsiFile file = event.getData(CommonDataKeys.PSI_FILE);
        FileViewProvider provider = PsiManager.getInstance(project).findViewProvider(node.getVirtualFile());
        if (provider == null) return;

        BrowserUtil.browse("https://www.google.com/search?q=%s+%s".formatted(
                provider.getBaseLanguage(),
                node.getProblem().getText()
        ));
    }
}
