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
    private String timeInit;

    protected void setTimeZone(String timeZone){
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss z").withZone(ZoneId.of(timeZone));
        timeInit = dateFormat.format(new Date().toInstant());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Enumeration<String> values = req.getParameterNames();
        if(values.hasMoreElements()){
            setTimeZone(req.getParameter("timezone").replace(" ", "+"));
        }else{
            setTimeZone("UTC");
        }
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().write("<h1>Поточний час:</h1>");
        resp.getWriter().write("<h3>" + timeInit + "</h3>");
        resp.getWriter().close();
    }

    @Override
    public void destroy(){
        timeInit = null;
    }
}
