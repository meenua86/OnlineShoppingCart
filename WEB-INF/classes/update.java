

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
 * Servlet implementation class update
 */
public class update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public update() {
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
		HttpSession sec = request.getSession(false);
		String ids = (String) sec.getAttribute("idsess");
		String pwrds = (String) sec.getAttribute("pswrdsess");
		ResultSet rs=stmt.executeQuery("select * from tab1 where id = \""+ids+"\" and pwrd = \""+pwrds+"\";");
		rs.next();
		String itemu=request.getParameter("itmno");
		sec.setAttribute("itemnosess", itemu);
		String name = rs.getString("name");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html>");
		out.println("<head>");
		out.println("<h1>");
		out.println("Hello,"+name+" !");
		out.println("</h1>");
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
		out.println("<form method = \"post\" action = \"updateF\">");
		out.println("Enter Quantity :");
		out.println("<input type = \"text\" name = \"quantity\">");
		out.println("<input type = \"submit\" value = \"BUY\">");
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
	
	//	String itemd=rs.getString("item");
	//  itemd = itemd + " " + itemu;
	//  ResultSet rs1=stmt.executeQuery("update tab1 set item = \""+itemd+"\" where id = \""+ids+"\";");
		}
		catch(Exception e)
		{
			System.out.println(e);
			HttpSession sec=request.getSession(false);
			sec.invalidate();
		}
		}
		
}
