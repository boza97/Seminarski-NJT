/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.interceptor;

import com.google.gson.Gson;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import rs.fon.silab.seminarskinjt.dto.UserDto;
import rs.fon.silab.seminarskinjt.dto.response.ResponseDataDto;

/**
 *
 * @author Bozidar
 */
public class UserInterceptor extends HandlerInterceptorAdapter {

    private final Gson gson;

    @Autowired
    public UserInterceptor(Gson gson) {
        this.gson = gson;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserDto user = (UserDto) request.getSession(true).getAttribute("user");

        if (user == null) {
            if (request.getHeader("accept") != null && !request.getHeader("accept").contains("application/json")) {
                String root = request.getContextPath();
                response.sendRedirect(root + "/login");
            } else {
                ResponseDataDto responseData = new ResponseDataDto("Morate se prijaviti na sistem.", null);
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

}
