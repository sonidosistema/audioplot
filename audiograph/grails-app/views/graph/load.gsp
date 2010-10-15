x values range from ${description.xmin} to ${description.xmax}
<br/>
y values range from ${description.ymin}
to ${description.ymax}
<br/>







<html>
<head>
    <title>extrema</title>
</head>
<body>
<h1>extrema</h1>
<table>
    <tr>
        <th>x</th>
         <th>y</th>
         <th>type</th>
    </tr>    <g:each in="${description.extrema}">
        <tr>
             <td>${it.x}</td>
                          <td>${it.y}</td>
             <td>${it.type}</td>
                         </g:each>
</table>
</body>
</html>




<br/>



<g:render template="play-file" bean="soundFile"/>
<p/>
<g:render template="plot-datapoints"/>