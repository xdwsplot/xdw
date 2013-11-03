
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import java.io.*;

import org.hibernate.*;
import org.hibernate.cfg.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class userServlet
 */
@WebServlet("/userServlet")
public class userServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		if(username == null){
			username = "";	
		}
		String password = request.getParameter("password");
		if(password == null){
			password ="";
		}
		String age1 = request.getParameter("age");
		if(age1 == null){
			age1 = "0";
		}
		
		String action = request.getParameter("action");
		System.out.println("action=" + action);
		if(action == null){
			action = "";
		}
		int age = Integer.parseInt(age1);
		
		response.setContentType("text/html;charset=GB2312");
		//PrintWriter out = response.getWriter();
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setAge(age);
		
		//Query query;
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		Transaction tx = session.beginTransaction();
		
		if(action.equals("save")){
			try{
				session.save(user);
				tx.commit();
				session.close();
				response.sendRedirect("reply.jsp");
			}catch (HibernateException e){
				e.printStackTrace();
				tx.rollback();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
