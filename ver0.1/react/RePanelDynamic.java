package react;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class RePanelDynamic  extends RePanel {

	StringBuilder scriptWriter=null;
	
	public RePanelDynamic(ReApplication app, String tag, String clientSide, String endpoint ) {
		super(app, tag, null);

		String id = getElementId();
		String idListener = getListener();

		if( clientSide!=null ) {
			StringBuilder jsCode = new StringBuilder();
			readFile(jsCode, clientSide );		
			replaceTag( jsCode, "_JS_ELEMENT_ID", id );
			replaceTag( jsCode, "_JS_LISTENER_NAME", idListener );
			replaceTag( jsCode, "_JS_ENDPOINT_URI", endpoint );
			app.registerScript(jsCode.toString());			
		}
		
		StringBuilder jsOnLoad = new StringBuilder();
		jsOnLoad.append("document.getElementById('_JS_ELEMENT_ID').reListenerEvent = _JS_LISTENER_NAME;");
		replaceTag( jsOnLoad, "_JS_LISTENER_NAME", idListener );
		replaceTag( jsOnLoad, "_JS_ELEMENT_ID", id );
		app.registerOnLoad(jsOnLoad.toString());
		
	
	}
	
	public RePanelDynamic(String tag, String serverSide, String[][] params ) {
		super(null, tag, null);

		String id = getElementId();
		
		String sourceType = params[0][0];
		String sourceURI = params[0][1];
		
		String fields = "";
		for( int i=1; i<params.length;i++ ) {
			fields += "{ \n";
			for( int j=0; j<params[i].length; j+=2 ) {	
				if( params[i][j].charAt(0)=='#' ) {
					fields += "   "+params[i][j].substring(1)+": "+params[i][j+1]+",\n";
				}else{
					fields += "   "+params[i][j]+": \""+params[i][j+1]+"\",\n";					
				}
			}//for - j
			fields += "},\n";
		}//for - i

		if( serverSide!=null ) {
			StringBuilder jsCode = new StringBuilder();
			readFile(jsCode, serverSide );		
			replaceTag( jsCode, "_JS_ELEMENT_ID", id );
			replaceTag( jsCode, "_JS_SOURCE_TYPE", sourceType );
			replaceTag( jsCode, "_JS_SOURCE_URI", sourceURI );
			replaceTag( jsCode, "_JS_SOURCE_FIELDS", fields );

			scriptWriter = jsCode;
		}
	}

	public StringBuilder render(StringBuilder out) {
		out.append("{CANVAS - initialized}");
		/*out.append("<div class='demo-container dx-viewport'>\n" + 
				"        <div id='gridContainer'  ='loadGrid1();'></div>\n" + 
				"    </div>");*/
		return out;
	}//render

	public StringBuilder writeJS( StringBuilder  out ) {
		out.append(scriptWriter);
		return out;
	}
}
