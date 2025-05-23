package misServlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/Productos")
public class Productos extends HttpServlet {

    private Map<String, Integer> golosinas;
    private Integer cantidad;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        golosinas = new LinkedHashMap<>(); // mantener orden
        Enumeration<String> nombres = config.getInitParameterNames();
        while (nombres.hasMoreElements()) {
            String nombre;int precio;
            if(cantidad == null) {
                nombre = nombres.nextElement();
                cantidad = Integer.parseInt(config.getInitParameter(nombre)); // agarro el primer parametro que es la cantidad
            }
            nombre = nombres.nextElement();
            precio = Integer.parseInt(config.getInitParameter(nombre));
            golosinas.put(nombre, precio);
        }
        // Almacenar en el ServletContext
        getServletContext().setAttribute("precios", golosinas);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, IOException {

        HttpSession sesion = request.getSession(false);
        if (sesion == null || sesion.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.html"); // si no tiene sesión creada o hubo error devuelve a login.html
            return;
        }

        response.setContentType("text/html");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html><html><head><title>Productos</title></head><body>");
            out.println("<h1>Seleccione las cantidades que desea comprar</h1>");
            out.println("<form action=\"" + request.getContextPath() + "/Facturar\" method=\"POST\">");
            out.println("<table border='1'>");
            out.println("<tr><th>Producto</th><th>Precio Unitario</th><th>Cantidad</th></tr>");

            for (Map.Entry<String, Integer> entry : golosinas.entrySet()) {
                String producto = entry.getKey();
                int precio = entry.getValue();

                out.println("<tr>");
                out.println("<td>" + producto + "</td>");
                out.println("<td>" + precio + "</td>");
                out.println("<td><input type='number' name='cantidad_" + producto + "' min='0' value='0'></td>");
                out.println("</tr>");
            }

            out.println("</table><br>");
            out.println("<input type='submit' value='Facturar'>");
            out.println("</form>");
            out.println("</body></html>");
        }
    }
        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

            HttpSession sesion = request.getSession(false);
            if (sesion == null || sesion.getAttribute("usuario") == null) {
                response.sendRedirect(request.getContextPath() + "/login.html");
                return;
            }

            Map<String, Integer> cantidades = new LinkedHashMap<>();
            for (String producto : golosinas.keySet()) {
                String param = request.getParameter("cantidad_" + producto);
                int cantidad = 0;
                try {
                    cantidad = Integer.parseInt(param);
                } catch (NumberFormatException ignored) {}
                cantidades.put(producto, cantidad);
            }

            sesion.setAttribute("cantidades", cantidades);

            response.sendRedirect(request.getContextPath() + "/Productos");
        }
    }
