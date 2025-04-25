package org.example;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Enumeration;

@WebServlet(value = "/time")
public class TimeServlet extends HttpServlet {
    protected static String setTimeZone(String timeZone){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss z").withZone(ZoneId.of(timeZone));
        return dateFormat.format(new Date().toInstant());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String timeInit;
        String timezone = req.getParameter("timezone");

        if(timezone == null || timezone.isBlank()){
            timeInit = setTimeZone("UTC");
        }else{
            timeInit = setTimeZone(req.getParameter("timezone").replace(" ", "+"));
        }
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write("<h1>Поточний час:</h1>");
        resp.getWriter().write("<h3>" + timeInit + "</h3>");
        resp.getWriter().close();
    }

}
