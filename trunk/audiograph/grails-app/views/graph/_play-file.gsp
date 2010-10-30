<g:set var="soundId" value="${toplay.soundFile.id }"/>
<table>
    <tr>
        <td>
		  <g:submitButton name="${toplay.playText?:'play'}" id="play-sound-${soundId}"/>
	  </td>
	  <td>
		<a id="media-${soundId}" href="${createLink(action:'play', id:soundId+'.wav')}"></a>
		</td>
	</tr>
</table>

<jq:jquery>
	$('#media-${soundId}').media({height:20, width:200, autoplay:true, params:{name:'mySound-${soundId}'}});
	$('#play-sound-${soundId}').click(function(){
	   console.log(document['mySound-${soundId}'])
		$('embed[name="mySound-${soundId}"]')[0].Play();
	});
</jq:jquery>
