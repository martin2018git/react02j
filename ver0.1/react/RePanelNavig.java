package react;

public class RePanelNavig extends RePanel {

	String[] params = null;
	
	public RePanelNavig(ReApplication app, String tag, String source, String[] _params) {
		/*
		 *  PARAMS structure:
		 *  [0] - name of js for changing navigation buttons
		 *  {[1,2,3]} - multiple pairs for navigation options [:target,:label,:icon]
		 */
		super(app,tag, source);
		params = _params;
		canPreRender = false;
		
		StringBuilder jsCode =  new StringBuilder();
		readFile( jsCode,  _params[0]);
		app.registerScript(jsCode.toString());

	}

	public StringBuilder render(StringBuilder out) {
		//debug(1,"RePanelNavig::render()");
		loadTemplate();

		for( int i=1; i<params.length; i+=3 ) {
			String id="menu_"+i;
			String label=params[i];
			String icon=params[i+1];
			String target=getElementId(params[i+2]);
			
			StringBuilder htmlCode = new StringBuilder();
			htmlCode.append(templateCode);
			
			replaceTag( htmlCode, "{NAVIG-ID}", id );
			replaceTag( htmlCode, "{NAVIG-TARGET}", target );
			replaceTag( htmlCode, "{NAVIG-ICON}", icon );
			replaceTag( htmlCode, "{NAVIG-CMD}",  label.toLowerCase() );
			replaceTag( htmlCode, "{NAVIG-LABEL}", label );
			
			out.append(htmlCode);
			
		}//for

		//debug(1,"RePanelNavig::render() [OUT]="+out+"[/OUT]");
		return out;
	}//render

}
