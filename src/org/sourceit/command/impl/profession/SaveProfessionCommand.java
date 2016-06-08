package org.sourceit.command.impl.profession;

import org.sourceit.command.ICommand;
import org.sourceit.db.ProfessionDBProvider;
import org.sourceit.entities.Profession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveProfessionCommand implements ICommand {

    private ProfessionDBProvider provider = ProfessionDBProvider.INSTANCE;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse resp) {

        Profession profession = new Profession();

        profession.setProfessionName(request.getParameter("profession_name"));
        if (request.getParameter("profession_id") != null) {
            profession.setId(Long.parseLong(request.getParameter("profession_id")));
        }

        try {
            provider.saveProfession(profession);
        } catch (Exception e) {
            request.setAttribute("error", e);
            return "pages/error.jsp";
        }

        return "controller?command=professions";
    }
}
