/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import rs.fon.silab.seminarskinjt.dto.UserDto;

/**
 *
 * @author Bozidar
 */
public class UserInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserDto user = (UserDto) request.getSession(true).getAttribute("user");

        if (user == null) {
            String root = request.getContextPath();
            response.sendRedirect(root + "/login");
            return false;
        }
        return true;
    }

}
