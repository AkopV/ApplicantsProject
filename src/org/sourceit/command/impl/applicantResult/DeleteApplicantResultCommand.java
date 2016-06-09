package org.sourceit.command.impl.applicantResult;

import org.sourceit.command.ICommand;
import org.sourceit.db.ApplicantResultDBProvider;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteApplicantResultCommand implements ICommand {

    private ApplicantResultDBProvider provider = ApplicantResultDBProvider.INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {

        try {
            Long applicantResultId = Long.parseLong(request.getParameter("id"));
            provider.deleteApplicantResult(applicantResultId);
        } catch (Exception e) {
            request.setAttribute("error", e);
            return "pages/error.jsp";
        }

        return "controller?command=applicantResults";
    }
}