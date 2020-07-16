window.onload = function() {
    /*let imgNames = ["back", "back2", "back3", "back4"];
    let imgName = imgNames[Math.floor(Math.random() * imgNames.length)];

    let mainElem = document.getElementsByTagName("body")[0].style
    mainElem.background = "url(\"styles/images/" + imgName + ".jpg\")";
    mainElem.backgroundSize = "cover";*/

    document.getElementById("scrollRegistrationButton").addEventListener("click", globalVariables());
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

function checkPasswords() {
    let defaultStyle = "1px solid rgb(80, 80, 80)";
    let errorStyle = "2px solid red";
    let goodStyle = "2px solid rgb(0, 200, 0)";

    let pass = document.getElementById("passwordReg");
    let passrep = document.getElementById("passwordRepReg");

    if(passrep == ""){
        pass.style.border = defaultStyle;
        passrep.style.border = defaultStyle;
    }

    if(pass.value == passrep.value){
        pass.style.border = goodStyle;
        passrep.style.border = goodStyle;
    }else{
        pass.style.border = errorStyle;
        passrep.style.border = errorStyle;
    }
}

/* AJAX */
function loginAction() {
    let username = document.getElementById("username_input").value;
    let password = document.getElementById("password_input").value;

    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            alert(this.responseText);
        }
    };
    xhttp.open("POST", "/LoginServlet?uname=" + username + "&password=" + password, true);
    xhttp.send();
}
function signUpAction() {

}
function checkUserName() {

}
