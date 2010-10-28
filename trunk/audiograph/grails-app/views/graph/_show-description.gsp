<g:set var="description" value="${graph.description}"/>
<g:set var="nameX" value="${graph.datapoints.nameX }"/> 
<g:set var="nameY" value="${graph.datapoints.nameY }"/> 

<g:if test="${graph.datapoints.comments}">
    <h1>Comments</h1>
    <pre>${graph.datapoints.comments}</pre>
</g:if>

<h1>Value ranges</h1>
<em>${nameX}</em> ranges from <g:formatNumber number="${description.xmin}" maxFractionDigits="2"/> 
to  <g:formatNumber number="${description.xmax}" maxFractionDigits="2"/>
<br />
<em>${nameY}</em> ranges from <g:formatNumber number="${description.ymin}" maxFractionDigits="2"/> 
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
			for <em>${nameX}</em> = <g:formatNumber number="${it.x}" maxFractionDigits="2"/>
			</td>
			<td>
			and <em>${nameY}</em> = <g:formatNumber number="${it.y}" maxFractionDigits="2"/>
			</td>
	</g:each>
</table>