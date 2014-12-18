<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <script>
  doc = new ActiveXObject("Word.Application"); // creates the word object
  doc.Visible=true; // display Word window
 
  doc.Documents.Open("./Architecture Based Self-adaptation.docx"); // specify path to document
 
  var sink = factory.NewEventSink(doc);
  sink("DocumentBeforeSave") = onDocumentBeforeSave;
  sink("DocumentBeforeClose") = onDocumentBeforeClose;
 </script>
</body>
</html>