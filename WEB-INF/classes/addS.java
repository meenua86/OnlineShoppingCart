

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
 * Servlet implementation class addS
 */
public class addS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addS() {
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
			String uname = rs.getString("name");
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<html>");
			out.println("<head>");
			out.println("<h1>");
			out.println("Hello,"+uname+" !");
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
			out.println("<center>");
			String name = request.getParameter("name");
			String pri=request.getParameter("price");
			int price=Integer.parseInt(pri);
			String type=request.getParameter("type");
			System.out.println(type);
			stmt.executeUpdate("insert into cart values (\""+name+"\",\"0\",\""+price+"\",\"0\",\"0\",\"0\",\""+type+"\");");
			RequestDispatcher view = request.getRequestDispatcher("adminS");
			view.forward(request,response);
	}catch(Exception e)
	{
		HttpSession sec=request.getSession(false);
		sec.invalidate();
		System.out.println(e);
	}
	}

}
