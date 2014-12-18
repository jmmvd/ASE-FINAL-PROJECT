<%@page import="java.sql.*,java.util.*"%> // import
<%
Connection connection = null;
        try{
        	String connectionURL = "jdbc:mysql://192.155.247.248:3307/db156f2a08d364a3ebb6b9de5503293ea";
             
            Class.forName("com.mysql.jdbc.Driver").newInstance(); 
            connection = DriverManager.getConnection(connectionURL, "u8hYSbovJPX5g", "prDzhmswb1fUo");
            Statement statement = connection.createStatement();
            ResultSet resultset = statement.executeQuery("select * from Students") ; 

            if(!resultset.next()) {
                out.println("Sorry, no table");
            } else {
            	
          %>
          <TABLE BORDER="1">
            <% while(resultset.next()){ %>
            <TR>
               <TH>StudentID</TH>
               <TH>StudentName</TH>
               <TH>Location</TH>
               <TH>Attendence</TH>

           </TR>


           <TR>
               <TD> <%= resultset.getString(1) %> </TD>
               <TD> <%= resultset.getString(2) %> </TD>
               <TD> <%= resultset.getString(3) %> </TD>
               <TD> <%= resultset.getString(4) %> </TD>

           </TR>
			<%} %>
       </TABLE>
       <BR>  	
         <%   	
            }
        }
        catch(Exception e){
        System.out.print(e);
        out.println(e.getMessage());
        e.printStackTrace();
        }
finally{
	connection.close();
}
        %>