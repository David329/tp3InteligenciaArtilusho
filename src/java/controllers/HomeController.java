/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Services.Clasificador;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author win7
 */
@WebServlet(name = "HomeController", urlPatterns = {"/HomeController"})
public class HomeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet HomeController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        String algoritmo = request.getParameter("algoritmo");

        if (algoritmo.equalsIgnoreCase("1")) {
            String resultado = "";
            try {
                resultado = Clasificador.AlgoritmoJ48();
            } catch (Exception ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
//            out.print(gson.toJson(Double.toString(resultado)));
            out.println("El kappa es: " + resultado);//cuidado con el salto de linea
            out.flush();
            out.close();
        } else if (algoritmo.equalsIgnoreCase("2")) {
            String resultado = "";
            try {
                resultado = Clasificador.AlgoritmoRandomForest();
            } catch (Exception ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
//            out.print(gson.toJson("El kappa es: " + resultado));
            out.println("El kappa es: "+resultado);//cuidado con el salto de linea
out.flush();
            out.close();
        } else if (algoritmo.equalsIgnoreCase("3")) {
            String resultado = "";
            try {
                resultado = Clasificador.AlgoritmoRandomTree();
            } catch (Exception ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
//            out.print(gson.toJson("El kappa es: " + Double.toString(resultado)));
            out.println("El kappa es: " + resultado);//cuidado con el salto de linea
            out.flush();
            out.close();
        } else if (algoritmo.equalsIgnoreCase("4")) {
            String resultado = "";
            try {
                resultado = Clasificador.mejorAlgoritmo();
            } catch (Exception ex) {
                Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }
//            out.print(gson.toJson("El mejor kappa es: " + Double.toString(resultado)));
            out.println("El kappa es: " + resultado);//cuidado con el salto de linea
            out.flush();
            out.close();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
