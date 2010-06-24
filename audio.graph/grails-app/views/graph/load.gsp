x values range from ${description.xmin}
to ${description.xmax}

y values range from ${description.ymin}
to ${description.ymax}

Local extrema


      <% ${description.extremaX}.each{ extrema -> %>
         <p><%=${extrema} %></p>
      <%}%>
