

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Servlet implementation class serv2
 */
public class serv2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public serv2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String item = request.getParameter("itm");
		HttpSession sec = request.getSession(false);
		String ids = (String) sec.getAttribute("idsess");
		String pwrds = (String) sec.getAttribute("pswrdsess");
		System.out.print(item+ids+pwrds);
		try{
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection con = DriverManager.getConnection("Jdbc:mysql://localhost:3306/shpcart","root","123456");
		java.sql.Statement stmt = con.createStatement();
		int row = stmt.executeUpdate("update tab1 set item = \""+item+"\" where id = \""+ids+"\";");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		if((row!=0)&&(item.equalsIgnoreCase("books")))
		{
			out.println("<html>"+"<head>"+"You have Selected Books...To See Change Look at Your Databse..."+"</body>"+"</html>");
		}
		else if(item.equals("fruits"))
		{
			out.println("<html>"+"<head>"+"You have Selected Fruits...To See Change Look at Your Databse..."+"</body>"+"</html>");
		}
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

}