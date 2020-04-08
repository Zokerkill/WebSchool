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

@WebServlet(name = "LoginController",loadOnStartup = 1, urlPatterns = {
    "/showLogin",
    "/login",
    "/logout",
    "/newPerson",
    "/addPerson",
})

public class LoginController extends HttpServlet {

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
    
    @Override
    public void init() throws ServletException {
        
        List<User> listUsers = null;
        try {
            listUsers = userFacade.findAll();
        } catch (Exception e) {
            listUsers= new ArrayList<>();
        }
        
        if(listUsers != null && !listUsers.isEmpty()) return;
        Person person = new Person(null, "Ivan Ivanoff");
        personFacade.create(person);
        EncriptPass ep = new EncriptPass();
        String salts = ep.createSalts();
        String password = ep.setEncriptPass("123", salts);
        User user = new User("admin", salts, password, person);
        userFacade.create(user);
        UserRoles userRoles = new UserRoles();
        userRoles.setUser(user);
        
        Roles role = new Roles();
        
        role.setRole("ADMIN");
        rolesFacade.create(role);
        userRoles.setRole(role);
        userRolesFacade.create(userRoles);
        role.setRole("MANAGER");
        rolesFacade.create(role);
        userRoles.setRole(role);
        userRolesFacade.create(userRoles);
        role.setRole("USER");
        rolesFacade.create(role);
        userRoles.setRole(role);
        userRolesFacade.create(userRoles);
        
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session  = request.getSession();
        String json = "";
        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonResponse jsonResponse = new JsonResponse();
        EncriptPass ep = new EncriptPass();
        String path = request.getServletPath();
        switch (path) {
            case "/showLogin":
                request.getRequestDispatcher("/showLogin.jsp")
                        .forward(request, response);
                break;
            case "/login":
                String login = request.getParameter("login");
                String password = request.getParameter("password");
                User user = userFacade.findByLogin(login);
                if (user == null) {
                    request.setAttribute("info", "Неправильный логин или пароль");
                    request.getRequestDispatcher("/showLogin.jsp")
                        .forward(request, response);
                }
                password = ep.setEncriptPass(password, user.getSalts());
                if (!password.equals(user.getPassword())) {
                    request.setAttribute("info", "Неправильный логин или пароль");
                    request.getRequestDispatcher("/showLogin.jsp")
                        .forward(request, response);
                }
                session = request.getSession(true);
                session.setAttribute("user", user);
                request.setAttribute("info", "Вы вошли в систему как " + login);
                request.getRequestDispatcher("/index.jsp")
                        .forward(request, response);
                break;
            case "/loginJson":
                JsonReader jsonReader = Json.createReader(request.getReader()); // Этот reader из Javax, генерируется
                JsonObject jsonObject = jsonReader.readObject();
                login = jsonObject.getString("login","");
                password = jsonObject.getString("password","");
                user = userFacade.findByLogin(login);
                if(user == null){
                  json = jsonResponse.getJsonResponse(session);
                  break;
                }
                password = ep.setEncriptPass(password,user.getSalts());
                if(!password.equals(user.getPassword())){
                  json = jsonResponse.getJsonResponse(session);
                  break;
                }
                session = request.getSession(true);
                session.setAttribute("user", user);
                json = jsonResponse.getJsonResponse(session);
                break;
            case "/logout":
                session = request.getSession(false);
                if (null != session) {
                    session.invalidate();
                }
                request.setAttribute("info", "Вы вышли из системы");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                break;
            case "/logoutJson":
                session = request.getSession(false);
                if(null != session){
                    session.invalidate();
                }
                json = jsonResponse.getJsonResponse(session);
                break;
            case "/newPerson":
                request.getRequestDispatcher("/newPerson.jsp").forward(request, response);
                break;
            case "/addPerson":
                String namePerson = request.getParameter("name");
                login = request.getParameter("login");
                String password1 = request.getParameter("password1");
                String password2 = request.getParameter("password2");
                if (!(password1 != null && password1.equals(password2))) {
                    request.setAttribute("info",
                        "Пользователя добавить не удалось (некорректные данные");
                    request.getRequestDispatcher("/newPerson.jsp").forward(request, response);
                    break;
                }
                Person person = null;
                try {
                    if (!"".equals(namePerson) && namePerson != null
                            && !"".equals(login) && login != null
                            && !"".equals(password1) && password1 != null) {
                        person = new Person(null, namePerson);
                        personFacade.create(person);
                        String salts = ep.createSalts();
                        password = ep.setEncriptPass(password1, salts);
                        user = new User(login, salts, password, person);
                        try {
                            userFacade.create(user);
                        } catch (Exception e) {
                            personFacade.remove(person);
                            throw new Exception(e);
                        }
                        request.setAttribute("info", "Пользователь " + person.getName() + " " + " добавлен."
                        );
                    } else {
                        request.setAttribute("info",
                            "Пользователя добавить не удалось (некорректные данные)");
                        request.getRequestDispatcher("/newPerson.jsp").forward(request, response);
                        break;
                    }
                } catch (Exception e) {
                    request.setAttribute("info",
                            "Пользователя добавить не удалось (некорректные данные)");
                    request.getRequestDispatcher("/newPerson.jsp").forward(request, response);
                    break;
                }
                request.getRequestDispatcher("/newPerson.jsp").forward(request, response);
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
