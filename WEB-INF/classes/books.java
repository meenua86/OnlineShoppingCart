

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
 * Servlet implementation class books
 */
public class books extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public books() {
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
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				out.println("<html>");
				out.println("<head>");
				out.println("<h1>");
				out.println("Hello,"+name+" !");
				out.println("</h1>");
				out.println("<h2>");
				out.println("The Books Available are...Click any one to Buy");
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
				
				ResultSet rs1 = stmt.executeQuery("select * from cart where type = \"Book\";");
				int  i=0;
				while(rs1.next())
					i++;
				if(i==0)
					out.println("<b>"+"Catagory Empty"+"</b>");
				else
				{
				rs1.first();
				out.println("<table>");
				out.println("<tr>");
				out.println("<th>");
				out.println("Item No.");
				out.println("</th>");
				out.println("<th>");
				out.println("Item Name");
				out.println("</th>");
				out.println("<th>");
				out.println("Item Price");
				out.println("</th>");			
				out.println("</tr>");
				rs1.previous();
				while(!rs1.isLast())
				{
					rs1.next();
					String itemname = rs1.getString("item");
					int itemn=rs1.getInt("itemno");
					int pri=rs1.getInt("price");
					out.println("<tr>");
					out.println("<td>");
					out.println(itemn);
					out.println("</td>");
					out.println("<td>");
					out.println("<a href = update?itmno="+itemn+">"+itemname+"</a>");
					out.println("</td>");
					out.println("<td>");
					out.println(pri);
					out.println("</td>");
					out.println("</tr>");
					
				}
				out.println("</table>");
								
				/*out.println("<table>");
				
				out.println("<tr>");
				out.println("<td>");
				out.println("<a href = update?itmno=3>"+"Andrew S. Tanenbaum"+"</a>");			
				out.println("</td>");
				out.println("<td>");
				out.println("Computer Networks");
				out.println("</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td>");
				out.println("<a href = update?itmno=9>"+"Head First (Servlets & JSPs)"+"</a>");
				out.println("</td>");
				out.println("<td>");
				out.println("J2EE");
				out.println("</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td>");
				out.println("<a href = update?itmno=8>"+"Head First (EJB)"+"</a>");
				out.println("</td>");
				out.println("<td>");
				out.println("J2EE");
				out.println("</td>");
				out.println("</tr>");
							
				out.println("<tr>");
				out.println("<td>");
				out.println("<a href = update?itmno=7>"+"Galvin,Silberschatz and Gagne"+"</a>");
				out.println("</td>");
				out.println("<td>");
				out.println("Operating Systems");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td>");
				out.println("<a href = update?itmno=10>"+"John C. Martin"+"</a>");
				out.println("</td>");
				out.println("<td>");
				out.println("TAFL");
				out.println("</td>");
				out.println("</tr>");

				out.println("<tr>");
				out.println("<td>");
				out.println("<a href = update?itm=13>"+"Rajib Mall"+"</a>");
				out.println("</td>");
				out.println("<td>");
				out.println("Software Engineering");
				out.println("</td>");
				out.println("</tr>");
				
				out.println("</table>");*/
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
				}
		}	
		catch(Exception e)
		{
			HttpSession sec=request.getSession(false);
			sec.invalidate();
			System.out.println(e);
		}
	}

}
