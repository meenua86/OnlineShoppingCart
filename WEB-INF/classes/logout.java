

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
 * Servlet implementation class logout
 */
public class logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logout() {
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
		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("Jdbc:mysql://localhost:3306/shpcart","root","123456");
		Statement stmt = con.createStatement();
		stmt.executeUpdate("update cart set quantity = 0,tprice = 0,bought = 0 where bought = 1;");
		HttpSession sec = request.getSession(false);
	//	String ids = (String) sec.getAttribute("idsess");
	//	String pwrds = (String) sec.getAttribute("pswrdsess");
	//	ResultSet rs=stmt.executeQuery("select * from tab1 where id = \""+ids+"\" and pwrd = \""+pwrds+"\";");
	//	rs.next();
	//	String name = rs.getString("name");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html>");
		out.println("<head>");
		out.println("<h1>");
	//	out.println("Hello,"+name+" !");
		out.println("</h1>");
		out.println("<h2>");
		out.println("You Have Succesfully Logged Out...");
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
		out.println("</html>");
		sec.invalidate();
		out.println("<form method = \"post\" action = login.html>");
		out.println("<input type = \"submit\" value = \"Login Again\">");
		out.println("</form>");
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
