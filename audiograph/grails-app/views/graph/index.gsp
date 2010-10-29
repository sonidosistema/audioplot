<g:applyLayout name="main.gsp">
	<html>
		<head>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js"></script>
		<script type="text/javascript" src="${resource(dir:'js/jquery', file:'jquery.media.js') }"></script>
		
		</head>
		<body>
	       <g:if test="${flash.message }">
	           <div class="errors">
	               ${flash.message }
	           </div>
	       </g:if>
	
			<g:form name="loadForm" url="[controller:'graph',action:'load']"
				update="div_load" method="post" enctype="multipart/form-data">
		Enter a data file
		    	<input id="input-data-file" type="file" name="data" />
				<g:submitButton id="submit-load-file" name="submit" value="load file" />
				
			</g:form>
			
			<g:if test="${loadedOk }">
				<div id="div_load">
					<tmpl:show-description graph="${graph}" />
    
                    <tmpl:play-file toplay="${[soundFile:soundFile]}"/>

                    <tmpl:plot-datapoints graph="${graph}" />

					<g:each in="${instrumentSounds.values() }">
	                    <tmpl:play-file toplay="${it}"/>
					</g:each>
	                <g:render template="file-description" bean="${fileDescription }"/>
                </div>
                
			</g:if>
	
<jq:jquery>
    $('#input-data-file').change(function(){
        $('#submit-load-file').focus();
    });
    $('#submit-load-file').click(function(){
        $('#div_load').empty();
    });
</jq:jquery>
		</body>
	</html>


</g:applyLayout>