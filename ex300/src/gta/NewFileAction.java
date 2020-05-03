package gta;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NewFileAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest res, HttpServletResponse resp) throws Exception {
	
		
		res.setAttribute("wow", "55");
		
		return "NewFile.jsp";
	}

}
