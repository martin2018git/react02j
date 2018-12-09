package bocks;

import react.ReApplication;
import react.RePanel;

public class BlockDataGrid extends RePanel {

	public BlockDataGrid( ReApplication app, String tag, String jsInit, String jsRender) {
		super( app, tag, null );

//		String id = getElementId();
//		String idListener = getListener();

		StringBuilder jsCode = new StringBuilder();
		readFile(jsCode, jsInit );		
		app.registerScript(jsCode.toString());

		StringBuilder jsOnLoad = new StringBuilder();
		readFile(jsCode, jsRender );		
//		replaceTag( jsOnLoad, "_JS_ELEMENT_ID", id );
		app.registerOnLoad(jsOnLoad.toString());

	}

	public StringBuilder render(StringBuilder out) {
		debug(1,"BlockHandler::render()");
		out.append("<div class='demo-container dx-viewport'>\n" + 
				"        <div id='gridContainer'></div>\n" ); 
				//"    <button  onClick='loadGrid1();'>Load</button></div>");
		return out;
	}//render

}
