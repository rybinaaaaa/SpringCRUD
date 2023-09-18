package rybina.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class mySpringMvcDispatcherInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {SpringConfig.class}; // мы возвращаем массив с 1 элементом типа class
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
