package react;

public class RePanelHeader extends RePanel {

	String[] params = null;
	
	public RePanelHeader(ReApplication app, String tag, String source, String[] _params) {
		super(app, tag, source);
		params = _params;
		canPreRender = false;
	}

	public StringBuilder render(StringBuilder out) {
		// first cast the template and prepare HTML code template for rendering
		load();
		replaceTag("{PANEL-HEADER-TITLE}",params[0]);
		replaceTag("{PANEL-HEADER-USER}",params[1]);

		// now render it and apply rendering to any child objects
		super.render(out);
		return out;
	}//render
	

}
