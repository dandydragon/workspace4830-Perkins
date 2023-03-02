import java.util.*;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.UtilFilePerkins;

@WebServlet("/MyServletPerkins")
public class MyServletPerkins extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public MyServletPerkins() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) //
         throws ServletException, IOException {
	  Integer[] arr = {-1};
      response.setContentType("text/html");
      response.getWriter().append("Hello World, this is Nathan Perkins!!");
      /*String filename = "/WEB-INF/numbers.csv";
      List<String> contents = UtilFilePerkins.readFile(getServletContext(), filename);
      for (String iLine : contents)
      {
    	  if (Integer.parseInt(iLine) % 2 == 0)
    	  {
    		  arr = push(arr, Integer.parseInt(iLine));
    	  }
      }
      Arrays.sort(arr, Collections.reverseOrder());
      if (arr.length < 10)
      {
    	  for (int i = 0; i < arr.length; i++)
          {
    		  if (i == arr.length - 1) 
    			  System.out.print(arr[i] + ".");
    		  else
    			  System.out.print(arr[i] + ", ");
          }
      }
      else
      {
    	  System.out.print("Top ten unique even numbers: ");
    	  for (int i = 0; i < 10; i++)
          {
    		  if (i == 9) 
    			  System.out.print(arr[i] + ".");
    		  else
    			  System.out.print(arr[i] + ", ");
          }
      }*/
   }

   //i forgot that java arrays are fixed lengths that blows hard.
   //anyways that calls for a push function
   /*private static Integer[] push(Integer[] array, int push) {
	    Integer[] temp = new Integer[array.length + 1];
	    for (int i = 0; i < array.length; i++)
	        temp[i] = array[i];
	    temp[array.length] = push;
	    return temp;
	}*/
  
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }
}