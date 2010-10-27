<g:setProvider library="jquery" />


<table>
    <tr>
        <td>
		  <g:submitButton name="play" id="play-sound">play</g:submitButton>
	  </td>
	  <td>
		<a class="media" href="${createLink(action:'play', id:soundFile.id+'.wav')}"></a>
		</td>
	</tr>
</table>

<jq:jquery>
	$('.media').media({height:20, width:200, autoplay:false, params:{name:'mySound'}});
	$('#play-sound').click(function(){
		document.mySound.Play();
	});
</jq:jquery>

