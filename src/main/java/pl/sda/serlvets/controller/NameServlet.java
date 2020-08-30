package pl.sda.serlvets.controller;


import pl.sda.serlvets.utilis.ResponseUtilis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(name = "nameServlet", urlPatterns = {"/name"})
public class NameServlet extends HttpServlet {

    private static final String NAME_REGEX = "[A-Z][a-z]";

    protected void doGet(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException {
        ResponseUtilis.setWriter(response);
        response.getWriter().println("<h1> style=\"color:red\" metoda GET nie istnieje</h1>");
        request.getRequestDispatcher("/index.jsp").include(request, response);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResponseUtilis.setWriter(response);
        PrintWriter writer = response.getWriter();

        Optional<String> giveName = Optional.ofNullable(request.getParameter("name"));
        Runnable handleMissingName = () -> writer.println("<h4>Nie podałeś imienia</h4>");
        giveName.ifPresentOrElse(name -> processNameParameter(name, writer), handleMissingName);

        request.getRequestDispatcher("/index.jsp").include(request, response);

    }

    private void processNameParameter(String name, PrintWriter writer) {
        try {
            if (name.matches(NAME_REGEX)) {
                writer.println("<h4>Twoje imie to: " + name + "</h4>");
            } else {
                writer.println("<h4>Imie jest w złym formacie</h4>");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
