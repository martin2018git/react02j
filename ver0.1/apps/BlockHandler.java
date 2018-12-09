package apps;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import react.ReApplication;

public class BlockHandler  extends ReApplication {

	public BlockHandler(HttpServletRequest _request, HttpServletResponse _response, String _rootDir) {
			super( "{BLOCK-HANDLER}", null, _request, _response, _rootDir );
	}


}
