<g:applyLayout name="main.gsp">
	<html>
		<head>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js"></script>
		<script type="text/javascript" src="${resource(dir:'js/jquery', file:'jquery.media.js') }"></script>
		
		</head>
		<body>
	
			<g:form name="loadForm" url="[controller:'graph',action:'load']"
				update="div_load" method="post" enctype="multipart/form-data">
		Enter a data file
		    	<input id="input-data-file" type="file" name="data" />
				<g:submitButton id="submit-load-file" name="submit" value="load file" />
				
			</g:form>
			
			<g:if test="${params.action == 'load' }">
				<div id="div_load">
					<g:render template="show-description" model="${[description:description]}"/>
    
					<g:render template="play-file" bean="soundFile" />

					<g:render template="plot-datapoints" />			
				</div>
                <g:render template="file-description" bean="${fileDescription }"/>
			</g:if>
	
<jq:jquery>
    $('#input-data-file').change(function(){
        $('#submit-load-file').focus();
    });
</jq:jquery>
		</body>
	</html>


</g:applyLayout>