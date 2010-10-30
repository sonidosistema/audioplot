Demo: select an example
<g:form name="form-demo" controller="graph" action="demo" method="get">
    <g:select id="demo-select" name="name" from="${names}" noSelection="${[null:'select a demo file']}"></g:select>
</g:form>

<jq:jquery>
    $('#demo-select').change(function(){
        console.log("submit demo", $('#form-demo'));
        $('#form-demo').submit();
    });
</jq:jquery>