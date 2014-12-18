<%@page import="java.sql.*,java.util.*"%> // import
<%
int sid=Integer.parseInt(request.getParameter("sid"));
String sname=request.getParameter("sname");
String att=request.getParameter("att");
String loc=request.getParameter("Location");
        try{
        	String connectionURL = "jdbc:mysql://192.155.247.248:3307/db156f2a08d364a3ebb6b9de5503293ea";
            Connection connection = null; 
            Class.forName("com.mysql.jdbc.Driver").newInstance(); 
            connection = DriverManager.getConnection(connectionURL, "u8hYSbovJPX5g", "prDzhmswb1fUo");
           Statement st=connection.createStatement();
           int i=st.executeUpdate("insert into Students (studentId,StudentName,Location,Attendence) values('"+sid+"','"+sname+"','"+loc+"','"+att+"')");
        out.println("Data is successfully inserted!");
        }
        catch(Exception e){
        System.out.print(e);
        out.println(e.getMessage());
        e.printStackTrace();
        }
        %>