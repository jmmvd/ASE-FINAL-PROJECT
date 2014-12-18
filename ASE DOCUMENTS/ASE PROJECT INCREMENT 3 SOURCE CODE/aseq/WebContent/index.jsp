<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %> 
<%@ page import="java.io.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="/scripts/knockout-2.2.1.js"></script>
<script src="/scripts/underscore-min.js"></script>
<script src="/scripts/jquery-2.1.1.js"></script>
<title>Insert title here</title>
</head>
<body onload="getMessages();">

<a href="./attendence.jsp">
    <button>attendance</button>
    </a>
<a href="./page3.html">
    <button>web chat</button>
</a>
<a href="./page4.html">
    <button>services</button>
</a>
<a href="./geo.html">
    <button>quiz</button>
</a>
<a href="./class.html">
    <button>lecture</button>
</a>
<a href="./EmailForm.jsp">
    <button>email</button>
</a>



<a href="./Result.jsp">
    <button>attendance view</button>
    </a>
    <a href="./datatab.jsp">
    <button>rooster</button>
</a>
 <a href="./paper.jsp">
    <button>paper</button>
</a>
    
    <h1>Notifications!</h1>
        <form>
            <table>
                <tr>
                    <td>instructor name:</td>
                    <td><input type="text" id="name" name="name" value ="Dr.Lee" readonly/></td>
                </tr>
                <tr>
                    <td>Your notification:</td>
                    <td><input type="text" id="message" name="message" /></td>
                </tr>
                <tr>
                    <td><input type="button" onclick="postMessage();" value="NOTIFY" /></td>
                </tr>
            </table>
        </form>
        <h2> Current notifications </h2>
        <div id="content">
            <% if (application.getAttribute("messages") != null) {%>
            <%= application.getAttribute("messages")%>
            <% }%>
        </div>
        <script>
            function postMessage() {
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("POST", "shoutServlet?t="+new Date(), false);
                xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                var nameText = escape(document.getElementById("name").value);
                var messageText = escape(document.getElementById("message").value);
                document.getElementById("message").value = "";
                xmlhttp.send("name="+nameText+"&message="+messageText);
            }
            var messagesWaiting = false;
            function getMessages(){
                if(!messagesWaiting){
                    messagesWaiting = true;
                    var xmlhttp = new XMLHttpRequest();
                    xmlhttp.onreadystatechange=function(){
                        if (xmlhttp.readyState==4 && xmlhttp.status==200) {
                            messagesWaiting = false;
                            var contentElement = document.getElementById("content");
                            contentElement.innerHTML = xmlhttp.responseText + contentElement.innerHTML;
                        }
                    }
                    xmlhttp.open("GET", "shoutServlet?t="+new Date(), true);
                    xmlhttp.send();
                }
            }
            setInterval(getMessages, 1000);
        </script>
        
        <div id="container">
    <div>
        <input id="txtSearch" type="text" data-bind="value:searchInput" />
        <input id="btnSearch" type="button" data-bind="click:search" value="Search Books" />
    </div>
    <ul data-bind="foreach:items">
        <li><a data-bind="attr: { href: link, title: description}, text: title"></a>
            <br/><span data-bind="text:description" />
        </li>
    </ul>
</div>
<script>

function validator(message, fun){
    var f = function(){
        return fun.apply(fun, arguments);
    };
    f['message'] = message;
    return f;
};

function checker(){
    var validators = _.toArray(arguments);
    return function(obj){
        return _.reduce(validators, function(errs, check){
            if(check(obj))
                return errs;
            else
                return _.chain(errs).push(check.message).value();                
        }, []);
    };
};

function isValidResourceItem(){
    var validators = checker(
  validator('obj can not be nul',function(obj){return !_.isNull(obj);}),
  validator('obj must contain title',function(obj){return !_.isNull(obj.title);}),
  validator('obj must contain canonicalVolumeLink',function(obj){return !_.isNull(obj.canonicalVolumeLink);}),
  validator('obj must contain description',function(obj){return !_.isNull(obj.description);})
                           );
        return validators;
};



$(document).ready(function () {
    
    function resultItem(title, link, description) {
        var self = this;
        self.title = ko.observable(title);
        self.link = ko.observable(link);
        self.description = ko.observable(description);
    }

    function viewModel() {
        var self = this;

        self.searchInput = ko.observable("G.R.R. Martin");
        self.items = ko.observableArray();

        self.mapResults = function (data) {
            var chk = isValidResourceItem();
            _.each(data.items, function (item) {
                
                var chkResult = chk(item.volumeInfo);
                if(chkResult.length == 0){                
                    var resultI = new resultItem(item.volumeInfo.title,
                                             item.volumeInfo.canonicalVolumeLink,
                                             item.volumeInfo.description);
                    self.items.push(resultI); // add to our observable array
                }
                else{
                    console.log(chkResult);
                }
            });            
        };

        self.search = function () {
            self.items.removeAll();
            var url = "https://www.googleapis.com/books/v1/volumes?q=" + self.searchInput();
            $.ajax({
                url: url,
                type: "GET",
                success: function (data) {
                    self.mapResults(data);
                }
            });
        };
    }

    var vm = new viewModel();
    ko.applyBindings(vm);
});
</script>
        
    <a class="muut" href="https://muut.com/i/ase-fall-2014">ase-fall-2014 forum</a>
<script src="//cdn.muut.com/1/moot.min.js"></script>
</body>
</html>