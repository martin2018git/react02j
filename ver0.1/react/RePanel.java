package react;

public class RePanel extends ReElement {
	
	ReApplication appRoot;
	
	public RePanel(ReApplication app, String tag, String source) {
		super(tag,source);
		appRoot = app;
	}

}
