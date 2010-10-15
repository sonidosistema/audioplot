<script type="text/javascript" src="/audio.graph/js/jquery/jquery-1.4.1.min.js"></script>
<g:setProvider library="jquery"/> 

<a id="play-link" >play sound</a>
<object id="play-embeded" data="${createLink(action:'play', params:[serverFileId:soundFile.id])}" type="audio/midi"
  width="400" height="10" standby=
  "Loading of music file in progress...please be patient" title=
  "audio graph plot">
    <param name="AnimationAtStart" value="true">
    <param name="AutoRewind" value="false">
    <param name="AutoStart" value="false">
    <param name="controls" value="all">

    <param name="EnableContextMenu" value="true">
    <param name="FileName" value=".${createLink(action:'play', params:[serverFileId:soundFile.id])}">
    <param name="ShowAudioControls" value="true">
    <param name="ShowControls" value="true">
    <param name="ShowPositionControls" value="true">
    <param name="ShowStatusBar" value="true">
    <param name="src" value="${createLink(action:'play', params:[serverFileId:soundFile.id])}">
    <param name="xVolume" value="50">
  </object>
  
<script type="text/javascript">
  var embededSound=$('#play-embeded')[0];
  $('#play-link').click(function(){
	  embededSound.Play()
  });
</script>

