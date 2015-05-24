

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class filter1
 */
public class filter1 implements Filter {

    /**
     * Default constructor. 
     */
    public filter1() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

try{
			HttpServletRequest req = (HttpServletRequest)request;
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("Jdbc:mysql://localhost:3306/shpcart","root","123456");
			Statement stmt = con.createStatement();
			HttpSession sec=req.getSession(false);
			
			System.out.println(sec);
			String ids=null,pwrds=null;
			if(sec==null)
			{
				sec=req.getSession(true);
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
			sec.setAttribute("rs",rs);
		// pass the request along the filter chain
			int i=0;
			while(rs.next())
				i++;
			if(i>0)
				chain.doFilter(request, response);
			else
			{
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
	}catch(Exception e)
	{
		PrintWriter out=response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet MyServlet</title>");  
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>"+"Enter Correct ID and Passowrd"+"</h1>");
        out.println("</body>");
        out.println("</html>");
	}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
