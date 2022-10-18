package com.emergentes.te_detectar_cliente;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DetectorServlet", urlPatterns = {"/DetectorServlet"})
public class DetectorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int contador = 0;
        // obtener el arreglo de cookies de cliente
        Cookie[] cukis = request.getCookies();
        if (cukis != null) {
            for (Cookie c : cukis) {
                if (c.getName().equals("Visitas")) {
                    
                    contador = Integer.parseInt(c.getValue());
                }
            }
        }
        contador++;
        Cookie c = new Cookie("Visitas", Integer.toString(contador));
        
        //El tiempo estimado de las cookies es de 20 segundos = 20 (puede ser el valos que se designe)
        c.setMaxAge(20);
        response.addCookie(c);
        //generar contenido a partir del servlet
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("Visitante Nro" + contador);
        
        if (contador == 1) {
            response.setContentType("text/html");
            out.print("<html>");
            out.print("<head>");
            out.print("<title>Conteo de Visitas</title>");
            out.print("</head>");
            out.print("<body>");
            out.print("<h1>BIENVENIDO A NUESTRO SITIO WEB</h1>");
            out.print("</body>");
            out.print("</html>");    
        }
         else {
                response.setContentType("text/html");
                out.print("<html>");
                out.print("<head>");
                out.print("<title>Conteo de Visitas</title>");
                out.print("</head>");
                out.print("<body>");
                out.print("<h1>GRACIAS POR VISITAR NUEVAMENTE</h1>");
                out.print("</body>");
                out.print("</html>");
               }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
