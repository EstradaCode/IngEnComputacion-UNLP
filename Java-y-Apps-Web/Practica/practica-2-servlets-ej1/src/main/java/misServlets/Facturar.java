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
import java.util.Map;

@WebServlet("/Facturar")
public class Facturar extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession(false);
        if (sesion == null || sesion.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login.html");
            return;
        }

        Map<String, Integer> cantidades = (Map<String, Integer>) sesion.getAttribute("cantidades"); // devuelvo el mapa que me mandé
        Map<String, Integer> precios = (Map<String, Integer>) getServletContext().getAttribute("precios");

        if (cantidades == null || precios == null) {
            response.sendRedirect(request.getContextPath() + "/Productos");
            return;
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html><html><head><title>Factura</title></head><body>");
            out.println("<h1>Factura de Compra</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>Producto</th><th>Cantidad</th><th>Precio Total</th></tr>");

            int totalGeneral = 0;

            for (String producto : cantidades.keySet()) {
                int cantidad = cantidades.get(producto);
                int precioUnitario = precios.getOrDefault(producto, 0);

                if (cantidad > 0) {
                    int subtotal = cantidad * precioUnitario;
                    totalGeneral += subtotal;

                    out.println("<tr>");
                    out.println("<td>" + producto + "</td>");
                    out.println("<td>" + cantidad + "</td>");
                    out.println("<td>" + subtotal + "</td>");
                    out.println("</tr>");
                }
            }

            out.println("</table>");
            out.println("<h3>Total General: $" + totalGeneral + "</h3>");
            out.println("<br><a href='" + request.getContextPath() + "/Productos'>Volver a productos</a>");
            out.println("<br><a href='" + request.getContextPath() + "/TerminarSesion'>Salir</a>");
            out.println("</body></html>");
        }
    }
}
