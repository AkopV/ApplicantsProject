package org.sourceit.command.impl.subject;

import org.sourceit.command.ICommand;
import org.sourceit.db.SubjectDBProvider;
import org.sourceit.entities.Subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SubjectCommand implements ICommand {

    SubjectDBProvider provider = SubjectDBProvider.INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        List<Subject> subjects = null;

        try {
            subjects = provider.getSubjects();
        } catch (Exception e) {
            request.setAttribute("error", e);
            return "pages/error.jsp";
        }

        request.setAttribute("subjects", subjects);

        return "pages/subject/subjects.jsp";
    }
}
