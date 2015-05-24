

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class findhint
 */
public class findhint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public findhint() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("idhint");
		try{
			System.out.println("ID = "+id);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("Jdbc:mysql://localhost:3306/shpcart","root","123456");
			Statement st=con.createStatement();
			System.out.println("After Connection...in findhint");
			ResultSet rs=st.executeQuery("select * from tab1 where id = \""+id+"\";");
			rs.next();
			String hint = rs.getString("hint");
			System.out.println("Hint = "+hint);
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
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
	//		ResultSet rs=st.executeQuery("select * from tab1 where id = \""+id+"\";");
	//		String hint = rs.getString("hint");
			out.println("<form method = \"post\" action = login.html>");
			out.println("<table>");
			out.println("<tr>");
			out.println("<td>");
			out.println("Your Id:");
			out.println("</td>");
			out.println("<td>");
			out.println(id);
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>");
			out.println("Your Hint : ");
			out.println("</td>");
			out.println("<td>");
			out.println(hint);
			out.println("</td>");
			out.println("</tr>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>");
			out.println("</td>");
			out.println("<td>");
			out.println("<input type = \"submit\" value = \"Login Again\">");
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");
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
