package org.example;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.Enumeration;


@WebFilter(value = "/time")
public class TimezoneValidateFilter  extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        Enumeration<String> values = req.getParameterNames();

        if(!values.hasMoreElements() || isValidTimezone(req.getParameter("timezone").replace(" ", "+"))){
            chain.doFilter(req, resp);
        }else{
            resp.setStatus(400);
            resp.getWriter().write("Invalid timezone");
            resp.getWriter().close();
        }
    }

    private boolean isValidTimezone(String timezone) {
        try {
            ZoneId.of(timezone);  // перевірка валідності
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }
}
