<html>
<head>
<script type="text/javascript" src="/audio.graph/js/jquery/jquery-1.4.1.min.js"></script>
<g:setProvider library="jquery"/> 
</head>
<body>
<g:formRemote name="loadForm" url="[controller:'graph',action:'load']" update="div_load" method="post" enctype="multipart/form-data">
Enter file
    <input type="file" name="data"/>
<br/>
    <input type="submit"/>
</g:formRemote>
<div id="div_load">&nbsp;</div>
</body>
</html>