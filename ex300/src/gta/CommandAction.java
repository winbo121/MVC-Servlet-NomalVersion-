package gta;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandAction {

	public String requestPro(HttpServletRequest res,HttpServletResponse resp) throws Exception;
}
