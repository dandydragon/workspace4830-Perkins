
/**
 * @file InsertPerkins.java
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertPerkins")
public class InsertPerkins extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public InsertPerkins() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String zip = request.getParameter("zip");
      String first_name = request.getParameter("first_name");
      String last_name = request.getParameter("last_name");
      String email = request.getParameter("email");
      String city = request.getParameter("city");
      String state = request.getParameter("state");

      String falseInsert = "";
      String insertSql = " INSERT INTO user (zip, first_name, last_name, email, city, state) values (?, ?, ?, ?, ?, ?)";
      
      Connection connection = null;
      Connection connection2 = null;
      PreparedStatement preparedStatement = null;
      try {

         DBConnectionPerkins.getDBConnection(getServletContext());
         connection = DBConnectionPerkins.connection;
         connection2 = DBConnectionPerkins.connection;
         
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, zip);
         preparedStmt.setString(2, first_name);
         preparedStmt.setString(3, last_name);
         preparedStmt.setString(4, email);
         preparedStmt.setString(5, city);
         preparedStmt.setString(6, state);
         
         String selectSQL = "SELECT * FROM zip WHERE ZIP LIKE ?";
         String confirmZIP = zip + "%";
         preparedStatement = connection2.prepareStatement(selectSQL);
         preparedStatement.setString(1, confirmZIP);
         
         ResultSet rs = preparedStatement.executeQuery();
         
         while (rs.next()) {
        	 if (state.equals(rs.getString("state").trim())) {
        		 if (city.equals(rs.getString("primary_city").trim())) {
        			 preparedStmt.execute();
            	 }
        		 else {
        			 if (!(city.equals("") || state.equals("") || zip.equals(""))) {
        				 falseInsert = (city + ", " + state + " does not exist at ZIP Code " + zip + ". Please input a different city.");
        			 }
        		 }
        	 }
        	 else {
        		 if (!(city.equals("") || state.equals("") || zip.equals(""))) {
    				 falseInsert = (city + ", " + state + " does not exist at ZIP Code " + zip + ". Please input a different state.");
    			 }
        	 }
         }
         
         //preparedStmt.execute();
         rs.close();
         preparedStatement.close();
         connection.close();
         connection2.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //
            falseInsert + 
            "  <li><b>First Name</b>: " + first_name + "\n" + //
            "  <li><b>Last Name</b>: " + last_name + "\n" +
            "  <li><b>Email</b>: " + email + "\n" + //
            "  <li><b>City</b>: " + city + "\n" +
            "  <li><b>State</b>: " + state + "\n" +
            "  <li><b>ZIP Code</b>: " + zip + "\n" +

            "</ul>\n");

      out.println("<a href=/webproject-ex-0214-Perkins/insert_perkins.html>Insert Data</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
