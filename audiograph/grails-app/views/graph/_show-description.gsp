<h1>Value ranges</h1>
X ranges from <g:formatNumber number="${description.xmin}" maxFractionDigits="2"/> 
to  <g:formatNumber number="${description.xmax}" maxFractionDigits="2"/>
<br />
Y ranges from <g:formatNumber number="${description.ymin}" maxFractionDigits="2"/> 
to  <g:formatNumber number="${description.ymax}" maxFractionDigits="2"/>
<br />

<h1>Local extrema</h1>
<table>
	<g:each in="${description.extrema}">
		<tr>
			<td>
			local ${it.type}
			</td>
			<td>
			x = <g:formatNumber number="${it.x}" maxFractionDigits="2"/>
			</td>
			<td>
			y = <g:formatNumber number="${it.y}" maxFractionDigits="2"/>
			</td>
	</g:each>
</table>