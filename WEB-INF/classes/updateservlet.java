

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class updateservlet
 */
public class updateservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateservlet() {
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
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("Jdbc:mysql://localhost:3306/shpcart","root","123456");
			Statement st=con.createStatement();
			String id = request.getParameter("id");
			String pwrd = request.getParameter("pwrd");
			String hint = request.getParameter("hint");
			String name = request.getParameter("name");
/*			int rs = */st.executeUpdate("insert into tab1 values (\""+id+"\",\""+pwrd+",\""+hint+"\",\""+name+"\");");
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
			out.println("You Have Been Registered...");
			out.println("<a href = login.html>"+"Click Here"+"</a>");
			out.println("to go to home page.");
		

		// TODO Auto-generated method stub
	}
	catch(Exception e)
	{
		HttpSession sec=request.getSession(false);
		sec.invalidate();
		System.out.println(e);
	}
	}

}
