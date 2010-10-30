<g:applyLayout name="main.gsp">
	<html>
		<head>
		<script type="text/javascript" src="${resource(dir:'js/jquery', file:'jquery.media.js') }"></script>
		
		</head>
		<body>
	       <g:if test="${flash.message }">
	           <div class="errors">
	               ${flash.message }
	           </div>
	       </g:if>
	
           <div style="position:relative; padding:3px; height:2.5em;">
               <div style="float:left; border:gray 1px solid;">
                    <g:form name="loadForm" url="[controller:'graph',action:'load']"
                        update="div_load" method="post" enctype="multipart/form-data">
                        Enter a data file
                        <input id="input-data-file" type="file" name="data" />
                        <g:submitButton id="submit-load-file" name="submit" value="submit file" />
                    </g:form>
                </div>

                <div id="div-demo" style="float:left; margin-left:20px; border:gray 1px solid; padding:3px">
                    <g:include controller="graph" action="demoWidget"/>
                </div>
            </div>
			<g:if test="${loadedOk }">
				<div id="div_load" style="clear:both">
					<tmpl:show-description graph="${graph}" />
    
                    <tmpl:play-file toplay="${[soundFile:soundFile]}"/>

                    <tmpl:plot-datapoints graph="${graph}" />

	                <g:render template="file-description" bean="${fileDescription }"/>
                </div>
                
			</g:if>
	
<jq:jquery>
    $('#input-data-file').change(function(){
        $('#submit-load-file').focus();
    });
    $('form').submit(function(){
        $('#div_load').empty();
    });
</jq:jquery>
		</body>
	</html>


</g:applyLayout>