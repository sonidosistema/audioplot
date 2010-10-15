<html>
<head>
<script type="text/javascript" src="/audio.graph/js/jquery/jquery-1.4.1.min.js"></script>
<script type="text/javascript" src="/audio.graph/js/jquery/jquery.form.js"></script>
<g:setProvider library="jquery"/> 
</head>
<body>

<g:form name="loadForm" url="[controller:'graph',action:'load']" update="div_load" method="post" enctype="multipart/form-data">
Please enter a file
    <input type="file" name="data"/>
<br/>
    <input type="submit"/>
</g:form>
<div id="div_load">&nbsp;</div>
<script type="text/javascript">
  $(function(){
    $('#loadForm').ajaxForm({target:'#div_load'});
  });
</script>
</body>
</html>


