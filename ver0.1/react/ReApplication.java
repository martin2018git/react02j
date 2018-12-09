package react;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReApplication extends ReElement{

	HttpServletRequest request;
	HttpServletResponse response;
	String webContent; //where WebCOntent directory is located

	StringBuilder pageTemplate;
	StringBuilder jsOnLoad;
	StringBuilder jsScript;

	public ReApplication(String appTag, String appTemplate, HttpServletRequest _request, HttpServletResponse _response, String _rootDir) {
		super( appTag, appTemplate );

		pageTemplate = new StringBuilder();
		jsOnLoad = new StringBuilder();
		jsScript = new StringBuilder();

		request = _request;
		response = _response;
		webContent= _rootDir;

	}
	
	public void execute() {
		// TODO Auto-generated method stub
	}//execute
	
	
	public void registerScript( String jsCode ) {
		//debug(1,"ReApplication::render() js register -> "+jsCode);
		jsScript.append( jsCode );
	}

	public void registerOnLoad( String jsCode ) {
		//debug(1,"ReApplication::render() js on load -> "+jsCode);
		//debug(1,"ReApplication::render()");
		jsOnLoad.append( jsCode );
	}

	public StringBuilder render(StringBuilder out) {
		debug(1,"ReApplication::render()");
		load();	
		if( !isRendered ) {
			renderedCode.setLength(0);
			renderedCode.trimToSize(); 
			if( !isTemplateLoaded ) loadTemplate();
			renderedCode.append(templateCode);
		}
		renderChildren(renderedCode);
		
		//renderScripts
		replaceTag("/*{SCRIPT-REGISTER}*/",jsScript );
		replaceTag("/*{SCRIPT-ON-LOAD}*/",jsOnLoad);

		out.append(renderedCode);
		return out;
	}//render


	public void writeHTML(StringBuilder out) {
		// format proper browser HTML response
		response.setContentType("text/html");
		
		PrintWriter pwOutput;
		try {
				pwOutput = response.getWriter();
				pwOutput.print(out);		
		} catch (IOException e) {
				e.printStackTrace();
		}//try	
	}//writeHTML

	
}
