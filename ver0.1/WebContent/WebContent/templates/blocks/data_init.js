function _JS_LISTENER_NAME(msg){
	document.getElementById('_JS_ELEMENT_ID').innerHTML = "User clicked "+msg;
	$('#_JS_ELEMENT_ID').addClass("panel-loading");
	/*$('#app-home-canvas').append('<div id="loading-image-canvas" style="position:relative;top:0;left:0;width: 100%;height:100%;z-index:2;opacity:0.4;filter: alpha(opacity = 50)"></div>');
	$('#tab-panel').fadeIn(150);
	$('#loading-image-canvas').css('background','gray');*/
	$('#_JS_ELEMENT_ID').append('<img id="_JS_ELEMENT_ID-loading" class="panel-loading-icon" src="images/loadingDaisy.gif">'); 

			console.log( "Fetching scripts at _JS_ENDPOINT_URI" );
			$.getScript( "_JS_ENDPOINT_URI", 
					function( data, textStatus, jqxhr ) {
					  console.log( "Script executed" );
					  console.log( data ); // Data returned
					  console.log( textStatus ); // Success
					  console.log( jqxhr.status ); // 200
					  console.log( "Load of _JS_ELEMENT_ID was performed." );
					  $('#_JS_ELEMENT_ID').removeClass("panel-loading");
					  $('#_JS_ELEMENT_ID-loading').remove();
					});
		
}
