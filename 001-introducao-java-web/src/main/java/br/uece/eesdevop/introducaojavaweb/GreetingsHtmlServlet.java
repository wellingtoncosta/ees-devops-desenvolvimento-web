package br.uece.eesdevop.introducaojavaweb;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/greetings/html")
public class GreetingsHtmlServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        String message = "Hello world!";
        PrintWriter writer = res.getWriter();
        writer.println(
                "<html>" +
                    "<body>" +
                        "<h1>" + message + "</h1>" +
                    "</body>" +
                "</html"
        );
    }

}
