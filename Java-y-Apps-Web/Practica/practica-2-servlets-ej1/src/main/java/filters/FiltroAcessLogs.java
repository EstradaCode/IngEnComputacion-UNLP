package filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
public class FiltroAcessLogs implements Filter {

    private AccesoDAO dao;

    @Override
    public void init(FilterConfig config) {
        dao = new AccesoDAO(); // Usa DataSource
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        String ip = request.getRemoteAddr();
        String recurso = request.getRequestURI();
        String navegador = request.getHeader("User-Agent");
        LocalDateTime fechaHora = LocalDateTime.now();

        dao.insertar(new Acceso(fechaHora, ip, recurso, navegador));

        chain.doFilter(req, res);
    }
}
