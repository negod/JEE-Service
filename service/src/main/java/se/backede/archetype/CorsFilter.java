package se.backede.archetype;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Slf4j
@WebFilter("/*")
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("INIT CORS FILTER");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletResponse response = (HttpServletResponse) res;
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:9000");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type, Origin, Accept, X-Requested-With");
            chain.doFilter(req, response);
        } catch (IOException | ServletException e) {
            log.error("CORS FILTER EXCEPTION" + e.getMessage());
        }
    }

    @Override
    public void destroy() {
        log.debug("DESTROY CORS FILTER");
    }
}
