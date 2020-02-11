

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DemoServlet")
public class DemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DemoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        try {
        	Class.forName("org.postgresql.Driver");
            Connection con= DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/jso", "postgres", "vki31d97");
            	if(con!=null){
            		System.out.println("CONNECTED");
            	java.sql.Statement tofetch=con.createStatement();
            	ResultSet rs=tofetch.executeQuery("SELECT * FROM \"registerUserSrvUrl\"");
                while(rs.next()){
                    String clientContext=rs.getString(1);
                    String expirationMillis=String.valueOf(rs.getLong(2));
                    String status=String.valueOf(rs.getInt(3));
                    String userId=rs.getString(4);
                    System.out.println(clientContext);
                    String user=String.valueOf(rs.getObject(5));
                    String location=String.valueOf(rs.getObject(6));                    
                    out.print("<h1>"+clientContext+"</h1><br>");
                    out.print("<h1>"+expirationMillis+"</h1><br>");
                    out.print("<h1>"+status+"</h1><br>");
                    out.print("<h1>"+userId+"</h1><br>");
                    out.print("<h1>"+user+"</h1><br>");
                    out.print("<h1>"+location+"</h1><br>");
                    
                }
                con.close();
            	}

        } catch (SQLException e) {
            out.println("ERROR");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
