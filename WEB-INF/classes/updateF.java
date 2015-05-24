

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
 * Servlet implementation class updateF
 */
public class updateF extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateF() {
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
		String name = rs.getString("name");
		String itemno=(String) sec.getAttribute("itemnosess");
		String quantity = request.getParameter("quantity");
//		item = item + quantity;
//		String itemd=rs.getString("item");
//		itemd = itemd + " " + item;
		ResultSet rs1 = stmt.executeQuery("select * from cart where itemno = \""+itemno+"\";");
		rs1.next();
		int price = rs1.getInt("price");
		int qu=rs1.getInt("quantity");
		/*ResultSet rs1=*/stmt.executeUpdate("update cart set bought = \"1\" where itemno = \""+itemno+"\";");
		int q = Integer.parseInt(quantity); 
		q=q+qu;
		int tprice=q*price;
		stmt.executeUpdate("update cart set quantity = \""+q+"\" where itemno = \""+itemno+"\";");
		stmt.executeUpdate("update cart set tprice = \""+tprice+"\" where itemno = \""+itemno+"\";");
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
		out.println("Item Added...");
		out.println("<a href = serv1>"+"Click Here"+"</a>");
		out.println("to go to main page.");
		out.println("<div align = \"right\">");
		out.println("<form action = \"showcart\" method = \"post\">");
		out.println("<input type = \"submit\" value = \"Show Cart\">");
		out.println("</form>");
		out.println("</html>");
		RequestDispatcher view = request.getRequestDispatcher("showcart");
		view.forward(request,response);
	}catch(Exception e)
	{
		System.out.println(e);
		HttpSession sec=request.getSession(false);
		sec.invalidate();
	}
	}
}