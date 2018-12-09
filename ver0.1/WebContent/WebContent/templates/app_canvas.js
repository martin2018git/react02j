function _JS_LISTENER_NAME(msg){
	document.getElementById('_JS_ELEMENT_ID').innerHTML = "User clicked "+msg;
	$('#_JS_ELEMENT_ID').addClass("panel-loading");
	/*$('#app-home-canvas').append('<div id="loading-image-canvas" style="position:relative;top:0;left:0;width: 100%;height:100%;z-index:2;opacity:0.4;filter: alpha(opacity = 50)"></div>');
	$('#tab-panel').fadeIn(150);
	$('#loading-image-canvas').css('background','gray');*/
	$('#_JS_ELEMENT_ID').append('<img id="_JS_ELEMENT_ID-loading" class="panel-loading-icon" src="images/loadingDaisy.gif">'); 

	//setTimeout( function(){
		$('#_JS_ELEMENT_ID').load('ContentLoader?cmd='+msg,function(data){
			//loadGrid1();
			
			$.getScript( "http://localhost:8080/opx2/JavascriptLoader?cmd=grid1", 
					function( data, textStatus, jqxhr ) {
					  console.log( data ); // Data returned
					  console.log( textStatus ); // Success
					  console.log( jqxhr.status ); // 200
					  console.log( "Load of grid1 was performed." );
					});
			
			$('#_JS_ELEMENT_ID').removeClass("panel-loading");
			$('#_JS_ELEMENT_ID-loading').remove();
			/*$('#app-home-canvas').fadeIn(150);*/
			/*$.getScript( "templates/blocks/data_render.js", function( data, textStatus, jqxhr ) {
				  console.log( data ); // Data returned
				  console.log( textStatus ); // Success
				  console.log( jqxhr.status ); // 200
				  console.log( "Load was performed." );
				});
				*/			
			//document.getElementById('gridContainer').runscript();
		});
    //},2000);
}
