

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class serv1
 */
public class serv1 extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			/*
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
			
			ResultSet rs=stmt.executeQuery("select * from tab1 where id = \""+ids+"\" and pwrd = \""+pwrds+"\";");
			*/
			System.out.println("IN SERVLET");
			HttpSession sec=request.getSession();
			String status = (String) sec.getAttribute("status");
			ResultSet rs = (ResultSet)sec.getAttribute("rs");
			rs.first();
	//		rs.next();
			String name = rs.getString("name");
			System.out.println(name);
		//	if(name!="Administrator")
		//	{	
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
			out.println("<div align = \"right\">");
			out.println("<form method = \"post\" action = showcart>");
			out.println("<input type = \"submit\" value = \"Show Cart\">");
			out.println("</form>");
			out.println("</div>");
			out.println("<center>");
			out.println("<a href = books>"+"Buy Books"+"</a>");
			out.println("<br>");
			out.println("<a href = fruits>"+"Buy Fruits"+"</a>");
			out.println("<br>");
			out.println("<a href = dvds>"+"Buy DVDs"+"</a>");
			out.println("<br>");
			out.println("</center>");
			out.println("<div align = \"right\">");
			out.println("<a href = serv1>"+"Click Here"+"</a>");
			out.println("to go to main page.");
			out.println("<form method = \"post\" action = logout>");
			out.println("<input type = \"submit\" value = \"LogOut\">");
			out.println("</form>");
			out.println("<br>"+"<b>"+"&copy Mitul Shah"+"<br>"+"CE - 103"+"<br>");
			out.println("072140"+"</b>");
			out.println("</div>");
			out.println("</html>");
		//	}
		//	else{
		//		RequestDispatcher view = request.getRequestDispatcher("admin.html");
		//		view.forward(request,response);
		//	}
			
		}catch(Exception e)
		{
			HttpSession sec=request.getSession(false);
			sec.invalidate();
			System.out.println(e);
			PrintWriter out=response.getWriter();
	        out.println("<html>");
	        out.println("<style type=\"text/css\">");
			out.println("body {");
			out.println("background-color: #d2b48c;");
			out.println("margin-left: 20%;");
			out.println("margin-right: 20%;");
			out.println("border: 1px dotted gray;");
			out.println("padding: 10px 10px 10px 10px;");
			out.println("font-family: sans-serif;");
			out.println("}");
			out.println("</style>");
			out.println("<center>");
	        out.println("<head>");
	        out.println("<title>Servlet MyServlet</title>");  
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<h1>"+"Enter Correct ID and Passowrd"+"</h1>");
	        out.println("</body>");
	        out.println("<a href = login.html>"+"Login Again"+"</a>");
	        out.println("</center>");
	        out.println("</html>");
		}
	}
}
