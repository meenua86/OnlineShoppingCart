

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
 * Servlet implementation class showcart
 */
public class showcart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showcart() {
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
		int fprice = 0;
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("Jdbc:mysql://localhost:3306/shpcart","root","123456");
		Statement stmt = con.createStatement();
		HttpSession sec = request.getSession(false);
		String ids = (String) sec.getAttribute("idsess");
		String pwrds = (String) sec.getAttribute("pswrdsess");
		ResultSet rs=stmt.executeQuery("select * from tab1 where id = \""+ids+"\" and pwrd = \""+pwrds+"\";");
		rs.next();
		String name = rs.getString("name");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html>");
		out.println("<head>");
		out.println("<h1>");
		out.println("Hello,"+name+" !");
		out.println("</h1>");
		out.println("<h2>");
		out.println("Cart Summary...");
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
		ResultSet rs1 = stmt.executeQuery("select * from cart where bought = 1;");
		int  i=0;
		while(rs1.next())
			i++;
		if(i==0)
			out.println("<b>"+"Cart Empty"+"</b>");
		else
		{
		rs1.first();
		out.println("<table>");
		out.println("<tr>");
		out.println("<th>");
		out.println("Item Catagory");
		out.println("</th>");
		out.println("<th>");
		out.println("Item Name");
		out.println("</th>");
		out.println("<th>");
		out.println("Quantity");
		out.println("</th>");
		out.println("<th>");
		out.println("Price");
		out.println("</th>");
		out.println("<th>");
		out.println("Total Price");
		out.println("</td>");			
		out.println("</tr>");
		rs1.previous();
		while(!rs1.isLast())
		{
			rs1.next();
			String itemname = rs1.getString("item");
			int itemn=rs1.getInt("itemno"); 
			int quan=rs1.getInt("quantity");
			int pri=rs1.getInt("price");
			int ttlprice = rs1.getInt("tprice");
			fprice+=ttlprice;
			String type = rs1.getString("type");
			out.println("<tr>");
			out.println("<td>");
			out.println(type);
			out.println("</td>");
			out.println("<td>");
			out.println(itemname);
			out.println("</td>");
			out.println("<td>");
			out.println(quan);
			out.println("</td>");
			out.println("<td>");
			out.println(pri);
			out.println("</td>");
			out.println("<td>");
			out.println(ttlprice);
			out.println("</td>");
			out.println("<td>");
			out.println("<a href = remove?itmno="+itemn+">"+"Remove"+"</a>");
			out.println("</td>");			
			out.println("</tr>");
			
		}
		out.println("</table>");
		out.println("<br>");
		out.println("<br>");
		out.println("<br>");
		out.println("<b>"+"Total Amount To Pay :: "+fprice);
		}
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
	}
		catch(Exception e)
		{
			System.out.println(e);
			HttpSession sec=request.getSession(false);
			sec.invalidate();
		}
		}
	}
