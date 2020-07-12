/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.silab.seminarskinjt;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import rs.fon.silab.seminarskinjt.config.ApplicationConfiguration;

/**
 *
 * @author Bozidar
 */
public class ApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("======================================================");
        System.out.println("============= ApplicationInitializer =================");
        System.out.println("======================================================");

        AnnotationConfigWebApplicationContext webAppContext = new AnnotationConfigWebApplicationContext();
        webAppContext.register(ApplicationConfiguration.class);
        webAppContext.setServletContext(servletContext);
        
        FilterRegistration.Dynamic hiddenHttpMethodFilter = servletContext
                .addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter());
        hiddenHttpMethodFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

        ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(webAppContext));
        dispatcherServlet.addMapping("/");
        dispatcherServlet.setLoadOnStartup(1);
    }

}
