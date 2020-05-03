package gta;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerAction extends HttpServlet{

	Map commandMap=new HashMap();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("처음 컨트롤러 액션에 맵핑해서 들어오는 동시에시작  프로퍼티 작업시작!!!");
		String url=config.getInitParameter("properties");
		
		try {
			FileInputStream fis=new FileInputStream(url);
			
			Properties p=new Properties();
			p.load(fis);
			fis.close();
			
			Iterator L=p.keySet().iterator();
			
			while(L.hasNext()) {
				String command=(String)L.next();
				String className=p.getProperty(command);
				Class changeClasstype1=Class.forName(className);
				Object changeClasstype2=changeClasstype1.newInstance();
				commandMap.put(command, changeClasstype2);
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req,resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		execute(req,resp);
	}

	private void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		String view=null;
		CommandAction com=null;
		try {
			String url=req.getRequestURI();
			com=(CommandAction)commandMap.get(url);
			view=com.requestPro(req, resp);
			
			RequestDispatcher D=req.getRequestDispatcher(view);
			D.forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
