<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome!</title>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Login/Registraton Idea Rights: https://codepen.io/FlorinPop17/pen/vPKWjd -->

    <!-- Styles -->
    <link rel="stylesheet" type="text/css" href="styles/welcome_style1.css">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
    <!-- JQuery -->
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- JavaScript -->
    <script src="js_code/welcome_js1.js"></script>
</head>
<body>
<!--<div id="mainOver"></div>-->

<div id="contentPlace">
    <div id="loginSpace">
        <span class="logicSpace_login">Log In</span>

        <!-- Login Form -->
        <form id="login_success" method="POST" action="/LoginServlet" style="display: none">
            <input type="hidden" id="login_success_username" name="username" value="">
            <input type="hidden" id="login_success_password" name="password" value="">
            <input type="hidden" id="login_success_remember" name="remember" value="">
        </form>
        <p id="username_input_p">
            <input id="username_input" name="username" type="text" placeholder="User Name">
        </p>
        <p id="password_input_p">
            <input id="password_input" name="password" type="password" placeholder="Password">
        </p>
        <p id="rememberUser_p">
            <div id="rememberUser_div">
                <label for="rememberUser">Remember Me</label>
                <input id="rememberUser" name="rememberUser" type="checkbox">
            </div>
        </p>
        <p id="login_error_output"><!-- Error Output Here --></p>
        <p id="login_button_p">
            <button id="login_button">Log In</button>
        </p>
        <!--<p id="forgotpassword_p">
            Concept
            <span class="forgotpassword_span">Forgot Password?</span>
        </p>-->
        <!-- Login Form -->
    </div>

    <div id="registerSpace">
        <p class="registerSpaceAppName">QuizWebsite</p>
        <p class="registerSpaceText"><!-- Empty --></p>
        <p class="registerSpaceTextAbove">
            Don't have an account? <strong>Just Create it!</strong>
        </p>
        <button onclick="scrollContentLeft()" id="scrollRegistrationButton">Sign Up</button>
    </div>

    <div id="registrationContent">
        <p class="registrationContent_p">Create An Account </br> <strong>In Few Steps</strong></p>

        <!-- Registration Form -->
        <form id="registration_success" method="POST" action="/RegistrationServlet" style="display: none">
            <input type="hidden" id="registration_success_username" name="username" value="">
            <input type="hidden" id="registration_success_password" name="password" value="">
        </form>
        <p id="usernameReg_p">
            <input id="usernameReg_input" name="username" type="text" placeholder="User Name">
        </p>
        <p id="passwordReg_p">
            <input id="passwordReg_input" name="password" type="password" placeholder="Password">
        </p>
        <p id="passwordRepReg_p">
            <input id="passwordRepReg_input" type="password" placeholder="Confirm Password">
        </p>
        <p id="registration_error_output"><!-- Error Output Here --></p>
        <p id="signup_button_p">
            <button id="signup_button">Sign Up</button>
        </p>
        <!-- Registration Form -->
    </div>

</div>
</body>
</html>
