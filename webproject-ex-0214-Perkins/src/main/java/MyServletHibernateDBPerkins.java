import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datamodel.EmployeePerkins;
import util.UtilDBPerkins;

@WebServlet("/MyServletHibernateDBPerkins")
public class MyServletHibernateDBPerkins extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MyServletHibernateDBPerkins() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      response.setContentType("text/html");

      // #1
      UtilDBPerkins.createEmployees("user1", "11", "712-111-1111");
      UtilDBPerkins.createEmployees("user2", "22", "712-222-2222");
      UtilDBPerkins.createEmployees("user3", "33", "712-333-3333");
      UtilDBPerkins.createEmployees("user4", "44", "712-444-4444");
      UtilDBPerkins.createEmployees("user5", "55", "712-555-5555");
      
      // #2
      retrieveDisplayData(response.getWriter());
   }

   void retrieveDisplayData(PrintWriter out) {
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");
      out.println("<ul>");
      List<EmployeePerkins> listEmployees = UtilDBPerkins.listEmployees();
      for (EmployeePerkins employee : listEmployees) {
         System.out.println("[DBG] " + employee.getId() + ", " //
               + employee.getName() + ", " //
               + employee.getAge() + ", "
               + employee.getPhone());

         out.println("<li>" + employee.getId() + ", " //
               + employee.getName() + ", " //
               + employee.getAge() + ", "
               + employee.getPhone() + "</li>");
      }
      out.println("</ul>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}
