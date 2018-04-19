package com.Konovalov;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "question", urlPatterns = "/question")
public class Questionnaire extends HttpServlet {
    private static final String TEMPLATE;

    static {
        TEMPLATE = "<html><head><title>Konovalov.com</title></head>" +
                "<body>%s</body></html>";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession hs = req.getSession();
        Statistic st = (Statistic) hs.getAttribute("state");
        if (st == null) {
            st = Statistic.getInstance();
            hs.setAttribute("state", st);
        }
        String name = req.getParameter("name");
        String lastName = req.getParameter("lastName");
        PrintWriter pw = resp.getWriter();
        if (st.addPerson(name, lastName)) {
            String text = "<b>You already voted</b><br>";
            pw.println(String.format(TEMPLATE, text + st.toString()));
            pw.close();
        } else {
            String launguage = req.getParameter("launguage");
            String compiler = req.getParameter("compiler");
            st.addVoice(launguage);
            st.addVoice(compiler);
            pw.println(String.format(TEMPLATE, st.toString()));
            pw.close();
        }


    }
}
