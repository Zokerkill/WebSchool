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

@WebServlet(name = "AdminController", urlPatterns = {
    "/editPerson",
    "/changePerson",
    "/newSubject",
    "/addSubject",
    "/editSubject",
    "/changeSubject",
    "/newGrade",
    "/addGrade",
    "/editGrade",
    "/changeGrade",
    "/listPersons",
    "/listGrades",
    "/listPersonsJson",
    "/listGradesJson",
})

public class AdminController extends HttpServlet {

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
        if (null == session) {
            request.setAttribute("info", "У вас нет прав доступа, войдите в систему!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        if (null == session.getAttribute("user")) {
            request.setAttribute("info", "У вас нет прав доступа!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        User user = (User) session.getAttribute("user");
        RoleManager rm = new RoleManager();
        if(!rm.isRoleUser("MANAGER", user)){
            request.setAttribute("info", "У вас нет прав доступа!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return; 
        }
        request.setAttribute("userRole", rm.getTopRoleName(user));
        switch (path) {
            case "/newSubject":
                request.getRequestDispatcher("/newSubject.jsp").forward(request, response);
                break;
            case "/newGrade":
                request.getRequestDispatcher("/newGrade.jsp").forward(request, response);
                break;
            case "/addSubject":
                String nameSubject = request.getParameter("name");
                String teacher = request.getParameter("teacher");
                Subject subject = null;
                subject = new Subject(nameSubject, teacher);
                subjectFacade.create(subject);
                request.setAttribute("info", "Предмет \"" + subject.getName() + "\" добавлен");
                request.getRequestDispatcher("/newSubject.jsp").forward(request, response);
                break;
            case "/addGrade":
                String nameGrade = request.getParameter("name");
                String text = request.getParameter("text");
                Grade grade = null;
                grade = new Grade(nameGrade, text);
                gradeFacade.create(grade);
                request.setAttribute("info", "Оценка \"" + grade.getName() + "\" добавлена");
                request.getRequestDispatcher("/newGrade.jsp").forward(request, response);
                break;
            case "/editPerson":
                String objectId = request.getParameter("id");
                Person person = personFacade.find(Long.parseLong(objectId));
                User userId = userFacade.find(Long.parseLong(objectId));
                request.setAttribute("person", person);
                request.setAttribute("userId", userId);
                request.getRequestDispatcher("/editPerson.jsp").forward(request, response);
                break;
            case "/changePerson":
                String objectColumn1 = request.getParameter("id");
                String objectColumn2 = request.getParameter("name");
                String login = request.getParameter("login");
                String password1 = request.getParameter("password1");
                String password2 = request.getParameter("password2");
                if (!(password1 != null && password1.equals(password2))) {
                    request.setAttribute("info",
                            "Пользователя добавить не удалось (некорректные данные");
                    request.getRequestDispatcher("/newPerson.jsp").forward(request, response);
                    break;
                }
                person = personFacade.find(Long.parseLong(objectColumn1));
                userId = userFacade.find(Long.parseLong(objectColumn1));
                person.setName(objectColumn2);
                personFacade.edit(person);
                EncriptPass ep = new EncriptPass();
                String salts = ep.createSalts();
                String password = ep.setEncriptPass(password1, salts);
                userId.setLogin(login);
                userId.setPassword(password);
                userFacade.edit(userId);
                request.setAttribute("person", person);
                request.setAttribute("userId", userId);
                request.setAttribute("info", "Параметры изменены!");
                request.getRequestDispatcher("/editPerson.jsp").forward(request, response);
                break;
            case "/editSubject":
                objectId = request.getParameter("id");
                subject = subjectFacade.find(Long.parseLong(objectId));
                request.setAttribute("subject", subject);
                request.getRequestDispatcher("/editSubject.jsp").forward(request, response);
                break;
            case "/changeSubject":
                objectColumn1 = request.getParameter("id");
                objectColumn2 = request.getParameter("name");
                String objectColumn3 = request.getParameter("teacher");
                subject = subjectFacade.find(Long.parseLong(objectColumn1));
                subject.setName(objectColumn2);
                subject.setTeacher(objectColumn3);
                subjectFacade.edit(subject);
                request.setAttribute("subject", subject);
                request.setAttribute("info", "Параметры изменены!");
                request.getRequestDispatcher("/editSubject.jsp").forward(request, response);
                break;
            case "/editGrade":
                objectId = request.getParameter("id");
                grade = gradeFacade.find(Long.parseLong(objectId));
                request.setAttribute("grade", grade);
                request.getRequestDispatcher("/editGrade.jsp").forward(request, response);
                break;
            case "/changeGrade":
                objectColumn1 = request.getParameter("id");
                objectColumn2 = request.getParameter("name");
                objectColumn3 = request.getParameter("text");
                grade = gradeFacade.find(Long.parseLong(objectColumn1));
                grade.setName(objectColumn2);
                grade.setText(objectColumn3);
                gradeFacade.edit(grade);
                request.setAttribute("grade", grade);
                request.setAttribute("info", "Параметры изменены!");
                request.getRequestDispatcher("/editGrade.jsp").forward(request, response);
                break;
            case "/listPersons":
                List<Person> listPersons = personFacade.findAll();
                request.setAttribute("listPersons", listPersons);
                request.getRequestDispatcher("/listPersons.jsp").forward(request, response);
                break;
            case "/listGrades":
                List<Grade> listGrades = gradeFacade.findAll();
                request.setAttribute("listGrades", listGrades);
                request.getRequestDispatcher("/listGrades.jsp").forward(request, response);
                break;
            case "/showAdmin":
                List<User> listUsers = userFacade.findAll();
                List<Roles> listRoles = rolesFacade.findAll();
                Map<User,String> mapUsers = new HashMap<>();
                listUsers.forEach((u) -> {
                    if(!"admin".equals(u.getLogin())){
                        mapUsers.put(u,rm.getTopRoleName(u));
                    }
                });
                request.setAttribute("listRoles", listRoles);
                request.setAttribute("mapUsers", mapUsers);
                request.getRequestDispatcher("/WEB-INF/showAdmin.jsp")
                        .forward(request, response);
                break;
            case "/changeRole":
                String roleID = request.getParameter("roleId");
                String userID = request.getParameter("userId");
                if(roleID == null || "#".equals(roleID) 
                        || userID == null || "#".equals(userID)){
                    request.setAttribute("info", "Не выбран пользователь или роль");
                    request.getRequestDispatcher("/showAdmin")
                        .forward(request, response);
                    break;
                }
                Roles role = rolesFacade.find(Long.parseLong(roleID));
                user = userFacade.find(Long.parseLong(userID));
                rm.setRoleUser(role,user);
                request.setAttribute("info", 
                        "Роль пользователя \""+user.getLogin()
                        +"\" изменена на \""+role.getRole()+"\"");
                request.getRequestDispatcher("/showAdmin")
                        .forward(request, response);
                break;
            case "/listPersonsJson":
                listPersons = personFacade.findAll();
                PersonJsonBuilder personJsonBuilder = new PersonJsonBuilder();
                JsonArrayBuilder jab = Json.createArrayBuilder();
                for(Person p : listPersons) {
                    jab.add(personJsonBuilder.createJsonPerson(p));
                }
                JsonResponse jr = new JsonResponse();
                String json = jr.getJsonResponse(session, jab.build());
                try (PrintWriter out = response.getWriter()) {
                    out.println(json);        
                }
                break;
            case "/listGradesJson":
                listGrades = gradeFacade.findAll();
                GradeJsonBuilder gradeJsonBuilder = new GradeJsonBuilder();
                jab = Json.createArrayBuilder();
                for(Grade p : listGrades) {
                    jab.add(gradeJsonBuilder.createJsonGrade(p));
                }
                jr = new JsonResponse();
                json = jr.getJsonResponse(session, jab.build());
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
