package org.sourceit.command.impl.specialitySubject;

import org.sourceit.command.ICommand;
import org.sourceit.db.ProfessionDBProvider;
import org.sourceit.db.SpecialitySubjectDBProvider;
import org.sourceit.db.SubjectDBProvider;
import org.sourceit.entities.Profession;
import org.sourceit.entities.Subject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AddSpecialitySubjectCommand implements ICommand {

    private SpecialitySubjectDBProvider provider = SpecialitySubjectDBProvider.INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {
        request.setAttribute("title", "Add speciality subject");

        List<Profession> professions;
        List<Subject> subjects;

        try {
            professions = ProfessionDBProvider.INSTANCE.getProfessions();
            subjects = SubjectDBProvider.INSTANCE.getSubjects();
        } catch (Exception e) {
            request.setAttribute("error", e);
            return "pages/error.jsp";
        }

        request.setAttribute("professions", professions);
        request.setAttribute("subjects", subjects);

        return "pages/specialitySubject/edit_speciality_subject.jsp";
    }
}
