/**
 * 
 */
package apps;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import react.ReApplication;
import react.ReElement;
import react.RePanel;
import react.RePanelCanvas;
import react.RePanelDynamic;
import react.RePanelHeader;
import react.RePanelNavig;

/**
 * @author vega
 *
 */
public class AppHome extends ReApplication {

	public AppHome(HttpServletRequest _request, HttpServletResponse _response, String _rootDir) {
			super( "{APP-HOME}", _rootDir+"/templates/app_home.html", _request, _response, _rootDir );
			
			//RePanel header = new RePanel("{APP-HOME-HEADER}",_rootDir+"templates/app_header.html");
			String[] params = { "Opportunistic Media Exchange", "ludwig" };
			RePanelHeader header = new RePanelHeader(this,"{APP-HOME-HEADER}", _rootDir+"templates/app_header.html", params  );
			
			//RePanel navigation = new RePanel("{APP-HOME-NAVIG}",_rootDir+"templates/app_navig.html");	
			String[] paramsNav = { 
					_rootDir+"templates/app_navig.js",
					"Watch", "images/notes.png","{APP-HOME-CANVAS-2}",
					"Orders", "images/articles.png","{APP-HOME-DYNAMIC}",
					"Auction", "images/genes.png","{APP-HOME-CANVAS-2}"
					};
			RePanelNavig navigation = new RePanelNavig(this,"{APP-HOME-NAVIG}", _rootDir+"templates/app_navig.html", paramsNav);

			RePanelDynamic canvas0 = new RePanelDynamic(this,
											"{APP-HOME-DYNAMIC}", _rootDir+"templates/blocks/data_init.js",
											"http://localhost:8080/opx2/JavascriptLoader?cmd=app-home-dynamic");
			
			//RePanelCanvas canvas1 = new RePanelCanvas(this,"{APP-HOME-CANVAS}",_rootDir+"templates/app_canvas.js");
			RePanelCanvas canvas2 = new RePanelCanvas(this,"{APP-HOME-CANVAS-2}",_rootDir+"templates/app_canvas.js");
			RePanel footer = new RePanel(this,"{APP-HOME-FOOTER}",_rootDir+"templates/app_footer.html");
			
			addChild(header);
			addChild(navigation);
			addChild(canvas0);
			//addChild(canvas1);
			//addChild(canvas2);
			addChild(footer);

	}//AppHome
	
	
}//AppHome
