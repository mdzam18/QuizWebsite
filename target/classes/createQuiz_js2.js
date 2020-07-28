/* JQuery/AJAX */
$(document).ready(function () {
    $("#quiz_name").on("change", function () {
        let quizName = $("#quiz_name").val();
        const SUCCESS = "SUCCESS";
        $.ajax({
            type: 'GET',
            data: {
                quizName: quizName
            },
            url: 'CreateQuizServlet',
            success: function (res) {
                let submitButton = document.getElementById("createQuiz");
                if(res == SUCCESS) {
                    submitButton.disabled = false;
                } else {
                    submitButton.disabled = true;
                }
            }
        });
    });
});
