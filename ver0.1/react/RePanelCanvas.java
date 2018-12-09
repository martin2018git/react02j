package react;

public class RePanelCanvas  extends RePanel {

	String[] params = null;
	
	public RePanelCanvas(ReApplication app, String tag, String jsListener) {
		super(app,tag, null);

		String id = getElementId();
		String idListener = getListener();

		StringBuilder jsCode = new StringBuilder();
		readFile(jsCode, jsListener );		
		replaceTag( jsCode, "_JS_LISTENER_NAME", idListener );
		replaceTag( jsCode, "_JS_ELEMENT_ID", id );
		app.registerScript(jsCode.toString());

		StringBuilder jsOnLoad = new StringBuilder();
		jsOnLoad.append("document.getElementById('_JS_ELEMENT_ID').reListenerEvent = _JS_LISTENER_NAME;");
		replaceTag( jsOnLoad, "_JS_LISTENER_NAME", idListener );
		replaceTag( jsOnLoad, "_JS_ELEMENT_ID", id );
		app.registerOnLoad(jsOnLoad.toString());

	}
	
	public StringBuilder render(StringBuilder out) {
		out.append("{CANVAS - initialized}");
		/*out.append("<div class='demo-container dx-viewport'>\n" + 
				"        <div id='gridContainer'  ='loadGrid1();'></div>\n" + 
				"    </div>");*/

		return out;
	}//render


}
