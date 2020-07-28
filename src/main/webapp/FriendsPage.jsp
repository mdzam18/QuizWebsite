 <html>
     <head>
         <title>Welcome</title>
     </head>
     <body>
         <ul>
            <c:forEach var="Friend" items= request.getParameter(ContextDataListener.Friends_Dao)>
                       <li> <a href= "ProfilePage.jsp">${Friend.getUserName()}</a> </li>
            </c:forEach>
         </ul>
      </body>
</html>