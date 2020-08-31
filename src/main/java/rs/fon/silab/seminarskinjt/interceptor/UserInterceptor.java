/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.interceptor;

import com.google.gson.Gson;
import java.io.PrintWriter;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;
import rs.fon.silab.seminarskinjt.dto.UserDto;
import rs.fon.silab.seminarskinjt.dto.response.ResponseDataDto;

/**
 *
 * @author Bozidar
 */
public class UserInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private Gson gson;
    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserDto user = (UserDto) request.getSession(true).getAttribute("user");

        if (user == null) {
            if (request.getHeader("accept") != null && !request.getHeader("accept").contains("application/json")) {
                String root = request.getContextPath();
                response.sendRedirect(root + "/login");
            } else {
                Locale locale = getLocale(request);

                String meessage = messageSource.getMessage("userDto.not.loged", null, locale);
                ResponseDataDto responseData = new ResponseDataDto(meessage, null);
                String responseDataJSON = gson.toJson(responseData);

                PrintWriter out = response.getWriter();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                out.print(responseDataJSON);
                out.flush();
            }

            return false;
        }
        return true;
    }

    private Locale getLocale(HttpServletRequest request) {
        String language = "sr";
        String region = "RS";

        if (WebUtils.getCookie(request, "lang") != null) {
            String cookieValue = WebUtils.getCookie(request, "lang").getValue();
            String[] locale = cookieValue.split("-");

            language = locale[0];
            region = locale[1];

            return new Locale.Builder().setLanguage(language).setRegion(region).build();
        }

        return new Locale.Builder().setLanguage(language).setRegion(region).build();
    }

}
