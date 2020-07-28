<html>
 <head>
        <title>Welcome</title>
 </head>
  <body>
        <h1 style="text-align:center">User Profile</h1>
        <div class "Profile">
                   <h1 <label for="username">User Name: <%= request.getParameter("username")%> </label> </h1>
                   <h2 <label for="name">Name: <%= request.getParameter("name")%> </label> </h2>
                   <h2 <label for="surname">Surname: <%= request.getParameter("surname")%> </label> </h2>
                   <h2 <label for="birthDate">Birth Date: <%= request.getParameter("birthDate")%> </label> </h2>
                   <h2 <label for="birthPlace">Birth Place: <%= request.getParameter("birthPlace")%> </label> </h2>
                   <h2 <label for="status">Status: <%= request.getParameter("status")%> </label> </h2>
         </div>
        <p><button>Friends</button></p>
  </body>
 </html>