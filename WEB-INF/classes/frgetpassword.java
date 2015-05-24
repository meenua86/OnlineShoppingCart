

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class frgetpassword
 */
public class frgetpassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public frgetpassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{	
			response.setContentType("text/html");
	    	PrintWriter out=response.getWriter();
	    	out.println("<html>");
	    	out.println("<style type=\"text/css\">");
			out.println("body {");
			out.println("background-color: #00aadd;");
			out.println("margin-left: 20%;");
			out.println("margin-right: 20%;");
			out.println("border: 1px dotted gray;");
			out.println("padding: 10px 10px 10px 10px;");
			out.println("font-family: sans-serif;");
			out.println("}");
			out.println("</style>");
			out.println("<center>");
			out.println("<form method = \"post\" action = \"findhint\">");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>");
			out.println("Enter Id:");
			out.println("</td>");
			out.println("<td>");
			out.println("<input type =\"text\" name = \"idhint\">");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>");
			out.println("</td>");
			out.println("<td>");
			out.println("<input type =\"submit\" value = \"GET HINT!\">");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</center>");
			out.println("</form>");
			out.println("<div align = \"right\">");
			out.println("<br>"+"<b>"+"&copy Mitul Shah"+"<br>"+"CE - 103"+"<br>");
			out.println("072140"+"</b>");
			out.println("</div>");
			out.println("</html>");
		
		}
		catch(Exception e)
		{
			System.out.println(e);
			HttpSession sec=request.getSession(false);
			sec.invalidate();
		}
	}
}
