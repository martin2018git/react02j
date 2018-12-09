package react;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class ReElement {
	
	private static final int INIT_LEN = 100;
	
	String idCode;
	String templateFile;
	Vector<ReElement> children;
	boolean canPreloadTemplate;
	boolean isTemplateLoaded;
	StringBuilder templateCode;
	boolean canPreRender;
	boolean isRendered;
	StringBuilder renderedCode;
	
	public ReElement(String tag, String source) {
		idCode = tag;
		templateFile = source;
		isTemplateLoaded = false;
		isRendered = false;
		templateCode = new StringBuilder();
		renderedCode = new StringBuilder();
		children = new Vector<ReElement>(INIT_LEN);
		canPreloadTemplate = true;
		canPreRender = true;
	}
	
	public String getTag() { return idCode; }
	
	public String getElementId(String attr) {
		String id = attr.toLowerCase();
		return( id.substring(1,id.length()-1) );
	}
	
	public String getElementId() { return( getElementId(idCode) ); }
	
	public String getListener() {
		String id = "listener_";
		int n=idCode.length();
		for( int i=0; i<n; i++ ) {
			if( idCode.charAt(i)=='-' ) id += "_"; 
			if( idCode.charAt(i)>='A' && idCode.charAt(i)<='Z' ) id += idCode.charAt(i)-'A'+'a'; 
			if( idCode.charAt(i)>='0' && idCode.charAt(i)<='1' ) id += idCode.charAt(i); 
			if( idCode.charAt(i)>='a' && idCode.charAt(i)<='z' ) id += idCode.charAt(i); 
		}
		return( id );		
	}

	
	public void debug(int level, String text) {
		if(level<10) System.out.println("REACT@"+idCode+">"+text);
	}
	
	public void addChild( ReElement childElement ) {
		children.add(childElement);
	}//addChild
	
	
	public void readFile(StringBuilder sb, String fileName) {
	       BufferedReader in;
			try {
				in = new BufferedReader(new FileReader (fileName));
	         String line;
				while((line = in.readLine()) != null)
				   {
				         sb.append(line+"\n");
				   }
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}//readFile
	
	
	public void preloadTemplate() {
			debug(1,"ReElement::preloadTemplate()");
			if( canPreloadTemplate ) loadTemplate();
	}//preloadTemplate

	public void loadTemplate() {
		debug(1,"ReElement::loadTemplate()");
		if( !isTemplateLoaded ) {
			if( templateFile!=null ) {
				readFile( templateCode, templateFile );
				isTemplateLoaded = true;
			}//if
		}//if
	}//loadTemplate

	
	public void preload() {
		debug(1,"ReElement::preload()");
		if( canPreRender && !isRendered ) {
			renderedCode.setLength(0);
			renderedCode.trimToSize(); 
			if( !isTemplateLoaded ) preloadTemplate();
			renderedCode.append(templateCode);
			preRenderChildren( renderedCode );
			isRendered = true;
		}//if		
	}//preload

	public void load() {
		loadTemplate();
		debug(1,"ReElement::load()");
		if( !isRendered ) {
			renderedCode.setLength(0);
			renderedCode.trimToSize(); 
			if( !isTemplateLoaded ) preloadTemplate();
			renderedCode.append(templateCode);
			renderChildren( renderedCode );
			isRendered = true;
		}//if		
	}//preload

	
	public void replaceTag( String tag, StringBuilder code ) {
		replaceTag(renderedCode, tag,code.toString());
	}

	public void replaceTag( String tag, String code ) {
		replaceTag(renderedCode, tag,code.toString());
	}

	public void replaceTag( StringBuilder buf, String tag, StringBuilder code ) {
		replaceTag(renderedCode, tag,code.toString());
	}

	public void replaceTag( StringBuilder buf, String tag, String code ) {
		int index = buf.indexOf(tag);
		int length = tag.length();
		while( index>0 ) {
			buf.replace(index, index+length, code );
			index = buf.indexOf(tag);
		}		
	}

	
	public void preRenderChildren(StringBuilder parentCode) {
		debug(1,"ReElement::preRenderChildren()");
		for( ReElement child : children ) {
			if( child.canPreRender ) {
				String tag = child.getTag();
				child.preload();
				debug(21,"ReElement::preRenderChildren()/child-> TAG="+tag+"  HTML="+child.renderedCode);
				replaceTag(tag,child.renderedCode);
			}
		}//for
	}

	public void renderChildren(StringBuilder parentCode) {
		debug(1,"ReElement::renderChildren()");
			for( ReElement child : children ) {
				if( !child.isRendered ) {
					String tag = child.getTag();
					StringBuilder childCode = new StringBuilder();
					child.render(childCode);
					debug(21,"ReElement::renderChildren()/child-> TAG="+tag+"  HTML="+childCode.toString());
					//debug(1,"[CODE]"+renderedCode+"[/CODE]" );
					replaceTag(tag,childCode);
				}//if
			}//for
		}
	
	public StringBuilder render(StringBuilder out) {
		debug(1,"ReElement::render()");
		load();	
		if( !isRendered ) {
			renderedCode.setLength(0);
			renderedCode.trimToSize(); 
			if( !isTemplateLoaded ) loadTemplate();
			debug(21,"ReElement::render() -> "+templateCode);
			renderedCode.append(templateCode);
			debug(21,"ReElement::render() -> "+renderedCode);
		}
		renderChildren(renderedCode);
		out.append(renderedCode);
		return out;
	}//render
	
	public StringBuilder render() {
		StringBuilder out = new StringBuilder();
		render(out);
		return out;
	}

}