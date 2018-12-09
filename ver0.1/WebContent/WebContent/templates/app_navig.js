/**
 * 
 */

var lastMenuSelected = null;
 
function menuclick(ref,who,msg){
	if( lastMenuSelected!=null ){
		document.getElementById(lastMenuSelected).className = "app-icon";
	}
	document.getElementById(ref).className = "app-icon-selected";
	lastMenuSelected = ref;
	document.getElementById(who).reListenerEvent(msg); /* call refresh/load function callback */ 
}
