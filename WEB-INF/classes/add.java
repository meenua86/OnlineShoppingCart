

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
 * Servlet implementation class add
 */
public class add extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public add() {
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
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("Jdbc:mysql://localhost:3306/shpcart","root","123456");
			Statement stmt = con.createStatement();
			HttpSession sec=request.getSession(false);
			
			System.out.println(sec);
			String ids=null,pwrds=null;
			if(sec==null)
			{
				sec=request.getSession(true);
				ids=request.getParameter("id");
				pwrds=request.getParameter("pwrd");
				sec.setAttribute("idsess",ids);
				sec.setAttribute("pswrdsess",pwrds);
				System.out.println("For First Time...");
			}
			else
			{
				System.out.println("For Second Time...");
				ids=(String) sec.getAttribute("idsess");
				pwrds=(String) sec.getAttribute("pswrdsess");
			}
			System.out.println(ids + "  "+ pwrds+" Final");
			
			ResultSet rs=stmt.executeQuery("select * from admin where id = \""+ids+"\" and pwrd = \""+pwrds+"\";");
			rs.next();
			String name = rs.getString("name");
			System.out.println(name);
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<html>");
			out.println("<head>");
			out.println("<h1>");
			out.println("Hello,"+name+" !");
			out.println("</h1>");
			out.println("<h2>");
			out.println("Please Choose the catagory...");
			out.println("</h2>");
			out.println("</head>");
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
			out.println("<h1>");
			out.println("<div align=\"center\">");
			out.println("Hello,Administrator!");
			out.println("</div>");
			out.println("</h1>");
			out.println("<h2>");
			out.println("Insert Details");
			out.println("</h2>");
			out.println("<center>");
			out.println("<form action =\"addS\" method = \"post\" >");
			out.println("<table border = \"0\">");
			out.println("<tr>");
			out.println("<td>"+"Item Name:"+"</td>");
			out.println("<td>"+"<input type = \"text\" name = \"name\">"+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>"+"Item Price:"+"</td>");
			out.println("<td>"+"<input type = \"text\" name = \"price\">"+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>Item Type:</td>");
		
			out.println("<td><select name=\"type\">");
			out.println("<option value=\"DVD\">DVD</option>");
			out.println("<option value=\"Fruit\">Fruit</option>");
			out.println("<option value=\"Book\">Book</option>");
			out.println("</select>");
			out.println("</td>");
			/*out.println("<td>"+"<input type = \"text\" name = \"type\">"+"</td>");*/
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td>"+"</td>");
			out.println("<td class=\"right\">"+"<input type = \"submit\" value = \"Add\">"+"</td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");
			out.println("</center>");
			out.println("<div align = \"right\">");
			out.println("<form method = \"post\" action = logout>");
			out.println("<input type = \"submit\" value = \"LogOut\">");
			out.println("</form>");
			out.println("<br>"+"<b>"+"&copy Mitul Shah"+"<br>"+"CE - 103"+"<br>");
			out.println("072140"+"</b>");
			out.println("</div>");
			out.println("</html>");

	}
catch(Exception e)
{
	HttpSession sec=request.getSession(false);
	sec.invalidate();
	System.out.println(e);
}
	}}
