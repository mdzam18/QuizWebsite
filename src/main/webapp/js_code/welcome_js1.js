window.onload = function() {
    /*let imgNames = ["back", "back2", "back3", "back4"];
    let imgName = imgNames[Math.floor(Math.random() * imgNames.length)];
    let mainElem = document.getElementsByTagName("body")[0].style
    mainElem.background = "url(\"styles/images/" + imgName + ".jpg\")";
    mainElem.backgroundSize = "cover";*/

    globalVariables();
}

let loginSpace,
    registerSpace,
    scrollButton,
    registerSpaceAppName,
    registerSpaceText,
    registerSpaceTextAbove,
    registrationContent;

let registerSpaceAppName_value,
    registerSpaceTextAbove_value;

function globalVariables(){
    loginSpace = document.getElementById("loginSpace");
    registerSpace =  document.getElementById("registerSpace");
    scrollButton = document.getElementById("scrollRegistrationButton");
    registerSpaceAppName = document.getElementsByClassName("registerSpaceAppName")[0];
    registerSpaceText = document.getElementsByClassName("registerSpaceText")[0];
    registerSpaceTextAbove = document.getElementsByClassName("registerSpaceTextAbove")[0];
    registrationContent = document.getElementById("registrationContent");

    registerSpaceAppName_value = registerSpaceAppName.innerHTML;
    registerSpaceTextAbove_value = registerSpaceTextAbove.innerHTML;
}

function scrollContentLeft(){
    loginSpace.style.marginLeft = "-500px";
    loginSpace.style.opacity = "0.4";
    scrollButton.innerHTML = "Log In";
    scrollButton.setAttribute("onclick", "scrollContentRight()");
    scrollButton.style.marginLeft = "95px";

    registerSpaceAppName.innerHTML = "Sign Up";
    registerSpaceText.style.display = "none";
    registerSpaceTextAbove.innerHTML = "Have an account?";

    registerSpace.style.width = "350px";
    registerSpace.style.marginLeft = "0px";

    registrationContent.style.marginLeft = "350px";
}

function scrollContentRight(){
    loginSpace.style.marginLeft = "0px";
    loginSpace.style.opacity = "1";
    scrollButton.innerHTML = "Sign Up";
    scrollButton.setAttribute("onclick", "scrollContentLeft()");
    scrollButton.style.marginLeft = "145px";

    registerSpaceAppName.innerHTML = registerSpaceAppName_value;
    registerSpaceText.style.display = "block";
    registerSpaceTextAbove.innerHTML = registerSpaceTextAbove_value;

    registerSpace.style.width = "450px";
    registerSpace.style.marginLeft = "450px";

    registrationContent.style.marginLeft = "900px";
}

/* JQuery/AJAX */
$(document).ready(function () {
    /* Constants */
    const NO_USERNAME = "USERNAME_NOT_EXISTS";
    const NO_PASSWORD = "INCORRECT_PASSWORD";
    const USERNAME_BUSY = "USERNAME_IS_BUSY";
    const SUCCESS = "SUCCESS";

    const NO_USERNAME_STR = "Username doesn't exist";
    const NO_PASSWORD_STR = "Password is Incorrect";
    const USERNAME_BUSY_STR = "Username is already used";
    const RESPONSE_PROBLEM_STR = "Problem With Response";

    const EMPTY_FIELD = "Field is empty!";
    const PASSWORD_CONFIRM_ERROR = "Password mismatch";

    /* Ajax */
    $('#login_button').on('click', function () {
        let username = $("#username_input").val();
        let password = $("#password_input").val();
        if(username == "" || password == "") {
            $("#login_error_output").html(EMPTY_FIELD);
            return;
        }

        $.ajax({
            type: 'GET',
            data: {
                username: username,
                password: password
            },
            url: 'LoginServlet',
            success: function (res) {
                if(res == NO_USERNAME) {
                    $("#login_error_output").html(NO_USERNAME_STR);
                } else if(res == NO_PASSWORD) {
                    $("#login_error_output").html(NO_PASSWORD_STR);
                } else if(res == SUCCESS) {
                    $("#login_success_username").val(username);
                    $("#login_success_password").val(password);
                    $("#login_success_remember").val(document.getElementById("rememberUser").checked);
                    $('#login_success').submit();
                } else {
                    alert(RESPONSE_PROBLEM_STR);
                }
            }
        });
    });

    $('#signup_button').on('click', function () {
        let username = $('#usernameReg_input').val();
        let password = $('#passwordReg_input').val();
        if(username == "" || password == "") {
            $("#registration_error_output").html(EMPTY_FIELD);
            return;
        }
        if($('#passwordRepReg_input').val() != password) {
            $('#registration_error_output').html(PASSWORD_CONFIRM_ERROR);
            return;
        }

        $.ajax({
            type: 'GET',
            data: {
                username: username
            },
            url: 'RegistrationServlet',
            success: function (res) {
                if(res == USERNAME_BUSY) {
                    $('#registration_error_output').html(USERNAME_BUSY_STR);
                } else if(res == SUCCESS) {
                    $("#registration_success_username").val(username);
                    $("#registration_success_password").val(password);
                    $('#registration_success').submit();
                } else {
                    alert(RESPONSE_PROBLEM_STR);
                }
            }
        });
    });

    /* Other Functions */
    $('#username_input').on('change', function () {
        $("#login_error_output").html("");
    });
    $('#password_input').on('change', function () {
        $("#login_error_output").html("");
    });
    $('#usernameReg_input').on('change', function () {
        $('#registration_error_output').html("");
    });
    $('#passwordReg_input').on('change', function () {
        $('#registration_error_output').html("");
    });
    $('#passwordRepReg_input').on('change', function () {
        $('#registration_error_output').html("");
        if(this.val() != $('#passwordReg_input').val()) {
            $('#registration_error_output').html(PASSWORD_CONFIRM_ERROR);
        }
    });
});
