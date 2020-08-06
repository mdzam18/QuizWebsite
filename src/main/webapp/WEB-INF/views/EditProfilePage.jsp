<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="FriendsPackage.FriendsSqlDao" %>
<%@ page import="UserPackage.UserSqlDao" %>
<%@ page import="UserPackage.User" %>
<%@ page import="Quiz.Quiz" %>
<%@ page import="Quiz.QuizSqlDao" %>
<%@ page import= "java.sql.SQLException" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored = "false" %>


<html>
    <head>
        <title>Profile</title>

        <link href="style.css" rel="stylesheet" type="text/css"><!-- Don't delete this link -->
        <style>
            html, body {
                margin: 0;
                width: 100%;
                height: 100%;
                background: whitesmoke;
            }
            .Inputs{
                margin: 40px auto;
                width: 600px;
                height: 420px;
                border: 1px solid #a1a1a1;
                border-radius: 4px;
                box-sizing: border-box;
                background: #f2f2f2;
                font-family: 'Roboto', sans-serif;
            }
            .HeaderText {
                width: 100%;
                text-align: center;
                font-size: 25px;
            }
            .InputsFields {
                width: 300px;
                margin-left: 150px;
                display: block;
            }
            .InputsFields p {
                margin: 0;
                width: 100%;
                text-align: right;
            }
            .InputsFields input[type=text] {
                width: 205px;
            }
            .editButton {
                width: 100%;
            }
            .editButton input[type=submit] {
                width: 100px;
                margin-top: 20px;
                margin-left: 250px;
                background-color: #4CAF50;
                color: white;
                padding: 12px 18px;
                font-size: 18px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
        </style>

        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
    </head>


    <body>
        <div class="Inputs">
            <form action="EditProfile" method="post">
                <p class="HeaderText">Edit Profile</p>
                <div class="InputsFields">
                    <p>
                        <label for="name">Name:</label>
                        <input type="text" id="name" name="name" value= "${name}" ><br/>
                    </p>
                    <p>
                        <label for="surname">Surname:</label>
                        <input type="text" id="surname" name="surname" value= "${surname}"><br/>
                    </p>
                    <p>
                        <label for="birthPlace">Birth Place:</label>
                        <input type="text" id="birthPlace" name="birthPlace" value= "${birthPlace}"><br/>
                    </p>
                    <p>
                        <label for="status">Status:</label>
                        <input type="text" id="status" name="status" value= "${status}"><br/>
                    </p>
                </div>
                <div class="editButton">
                    <input type="submit" name="button" value="edit">
                    <input type="hidden" name="username" VALUE= <%=request.getParameter("username")%>>
                </div>
            </form>
        </div>
    </body>
</html>
