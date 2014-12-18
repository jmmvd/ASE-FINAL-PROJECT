<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="js/data_table/reset-min.css">
<link rel="stylesheet" type="text/css" href="js/data_table/complete.css">

<script type="text/javascript" language="javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" language="javascript" src="js/data_table/jquery.dataTables.min.js"></script>
<script type="text/javascript" language="javascript" src="js/data_table/jquery.dataTables.plugins.js"></script>

<script language="javascript" type="text/javascript">
	var oTable;
	$(document).ready(function(){
		$.ajax({
			dataType: "text",//force ajax return text in order to parse json latter.
			url: "DataTableCtrl", 
			success: function(data){
				//custome parse Json
				var jsonTbl = JSON.parse(data, applyCallback);
		
				//create DOM table in memory
				var DOMTable = $("<table>").attr("class", "pretty");
				
				//add dom table into body
				$("body").append(DOMTable);
				
				//init datatables
				oTable = DOMTable.dataTable(jsonTbl); 
			}
		});
	});
</script>
</head>
	<body>
	</body>
</html>
