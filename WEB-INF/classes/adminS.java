

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
 * Servlet implementation class adminS
 */
public class adminS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminS() {
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
			int i=0;
			while(rs.next())
				i++;
			if(i>0)
			{
			rs.first();
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
			out.println("What you want to do?");
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

			out.println("<center>");
			out.println("<a href = add>"+"Add Items"+"</a>");
			out.println("<br>");
			out.println("<a href = delete>"+"Remove Items"+"</a>");
			out.println("</center>");
			out.println("<div align = \"right\">");
			out.println("<form method = \"post\" action = logout>");
			out.println("<input type = \"submit\" value = \"LogOut\">");
			out.println("</form>");
			out.println("<br>"+"<b>"+"&copy Mitul Shah"+"<br>"+"CE - 103"+"<br>");
			out.println("072140"+"</b>");
			out.println("</div>");
			out.println("</html>");
			}else{
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
		        out.println("<body>");
		        out.println("<h1>"+"Enter Correct ID and Passowrd"+"</h1>");
		        out.println("</body>");
		        out.println("<a href = login.html>"+"Login Again"+"</a>");
		        out.println("</html>");
		        System.out.println("Wrong Password");
		        sec.setAttribute("status","false");
			}

	}
	catch(Exception e)
	{
		HttpSession sec=request.getSession(false);
		sec.invalidate();
		System.out.println(e);
	}

}}
