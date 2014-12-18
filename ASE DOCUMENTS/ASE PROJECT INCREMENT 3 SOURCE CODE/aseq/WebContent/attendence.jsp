<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Attendence</title>
<script src="/scripts/jquery-2.1.1.js"></script>
</head>
<body>
<script type="text/javascript">
    //<![CDATA[
        $(window).load(function(){ function doGeo( position ) 
        {
            var s = position.coords.latitude+","+position.coords.longitude;
            $( "#loc" ).val(s);
            $( "#hiddenloc" ).html(s);
            
        }

        function lost()
        {
            
        };

        navigator.geolocation.watchPosition( doGeo, lost, {maximumAge:0,enableHighAccuracy:true} );


});
    //]]>
</script>
</body>
<script type="text/javascript">
var s = document.getElementById("loc");
$( "#hiddenloc" ).html(s);
</script>
<form method="post" action="Insert.jsp"> 
<table>
<tr><td>StudentID:</td><td><input type="text" name="sid"></td></tr>
<tr><td>StudentName:</td><td><input type="text" name="sname"></td></tr>
<tr><td>Location:</td><td><input type="text" id="loc" name="Location" readonly/></td></tr>

<tr><td>Attendence</td><td><input type="text" name="att"></td></tr>
<tr><td></td><td><input type="submit" value="Submit"></td></tr>
</table>
<input type="hidden" id="hiddenloc" />
</form>
</html>