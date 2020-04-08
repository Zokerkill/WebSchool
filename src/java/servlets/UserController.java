package servlets;

import entity.Person;
import entity.Subject;
import entity.Grade;
import entity.History;
import entity.Roles;
import entity.User;
import entity.UserRoles;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.PersonFacade;
import session.SubjectFacade;
import session.GradeFacade;
import session.HistoryFacade;
import session.RolesFacade;
import session.UserFacade;
import session.UserRolesFacade;
import util.EncriptPass;
import util.RoleManager;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import jsonbuilders.SubjectJsonBuilder;
import jsonbuilders.PersonJsonBuilder;
import jsonbuilders.GradeJsonBuilder;
import jsonbuilders.UserJsonBuilder;
import util.JsonResponse;

@WebServlet(name = "UserController", urlPatterns = {
    "/listSubjects",
    "/listSubjectsJson",
})

public class UserController extends HttpServlet {

    @EJB
    PersonFacade personFacade;
    @EJB
    SubjectFacade subjectFacade;
    @EJB
    GradeFacade gradeFacade;
    @EJB
    HistoryFacade historyFacade;
    @EJB
    UserFacade userFacade;
    @EJB 
    RolesFacade rolesFacade;
    @EJB
    UserRolesFacade userRolesFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        HttpSession session = request.getSession(false);
        if (null == session){
            request.setAttribute("info", "У вас нет прав доступа, войдите в систему!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;   
        }
        if(null == session.getAttribute("user")){
            request.setAttribute("info", "У вас нет прав доступа!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return; 
        }
        User user = (User) session.getAttribute("user");
        Person person = null;
        request.setAttribute("user", user);
        
        switch (path) {
            case "/listSubjects":
                List<Subject> listSubjects = subjectFacade.findAll();
                request.setAttribute("listSubjects", listSubjects);
                request.getRequestDispatcher("/listSubjects.jsp").forward(request, response);
                break;
            case "/listSubjectsJson":
                listSubjects = subjectFacade.findAll();
                SubjectJsonBuilder subjectJsonBuilder = new SubjectJsonBuilder();
                JsonArrayBuilder jab = Json.createArrayBuilder();
                for(Subject s : listSubjects) {
                    jab.add(subjectJsonBuilder.createJsonSubject(s));
                }
                JsonResponse jr = new JsonResponse();
                String json = jr.getJsonResponse(session, jab.build());
                try (PrintWriter out = response.getWriter()) {
                    out.println(json);        
                }
                break;
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
        processRequest(request, response);
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
