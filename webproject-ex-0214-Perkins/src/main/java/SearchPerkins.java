import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SearchPerkins")
public class SearchPerkins extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public SearchPerkins() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String theLastName = request.getParameter("theLastName");
      String theEmail = request.getParameter("theEmail");
      String theZIP = request.getParameter("theZIP");
      search(theLastName, theEmail, theZIP, response);
   }

   void search(String theLastName, String theEmail, String theZIP, HttpServletResponse response) throws IOException {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Database Result";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
            "transitional//en\">\n"; //
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h1 align=\"center\">" + title + "</h1>\n");

      Connection connection = null;
      PreparedStatement preparedStatement = null;
      try {
         DBConnectionPerkins.getDBConnection(getServletContext());
         connection = DBConnectionPerkins.connection;

         if (theLastName.isEmpty() && theEmail.isEmpty() && theZIP.isEmpty()) {
            String selectSQL = "SELECT * FROM user";
            preparedStatement = connection.prepareStatement(selectSQL);
         }
         else {
            String selectSQL = "SELECT * FROM user WHERE (LAST_NAME LIKE ?) AND (EMAIL LIKE ?) AND (ZIP LIKE ?)";
            String theLastNameSQL = theLastName + "%";
            String theEmailSQL = theEmail + "%";
            String theZIPSQL = theZIP + "%";
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, theLastNameSQL);
            preparedStatement.setString(2, theEmailSQL);
            preparedStatement.setString(3, theZIPSQL);
         }
         ResultSet rs = preparedStatement.executeQuery();

         while (rs.next()) {
            String zip = rs.getString("zip").trim();
            String first_name = rs.getString("first_name").trim();
            String last_name = rs.getString("last_name").trim();
            String email = rs.getString("email").trim();
            String city = rs.getString("city").trim();
            String state = rs.getString("state").trim();

            if (theLastName.isEmpty() || last_name.contains(theLastName)) {
               out.println("First Name: " + first_name + "<br>");
               out.println("Last Name: " + last_name + "<br>");
               out.println("Email: " + email + "<br>");
               out.println("City: " + city + "<br>");
               out.println("State: " + state + "<br>");
               out.println("ZIP Code: " + zip + "<br><br>");
            }
         }
         out.println("<a href=/webproject-ex-0214-Perkins/search_perkins.html>Search Data</a> <br>");
         out.println("</body></html>");
         rs.close();
         preparedStatement.close();
         connection.close();
      } catch (SQLException se) {
         se.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (preparedStatement != null)
               preparedStatement.close();
         } catch (SQLException se2) {
         }
         try {
            if (connection != null)
               connection.close();
         } catch (SQLException se) {
            se.printStackTrace();
         }
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
