<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Quiz</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Styles -->
    <link rel="stylesheet" type="text/css" href="styles/createQuiz_style1.css">
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
    <!-- JQuery -->
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- JavaScript -->
    <script src="js_code/createQuiz_js1.js"></script>
    <script src="js_code/createQuiz_js2.js"></script>
</head>
<body>
<div id="content">
    <form id="questions" action="/CreateQuizServlet" method="POST">
        <input id="quiz_name" name="quiz_name" type="text" placeholder="Quiz Name" required>
        <table class="tableContent">
            <tr>
                <th>
                    <label for="questionRandomSequence">Random Sequence of Questions</label>
                    <input type="checkbox" id="questionRandomSequence" name="questionRandomSequence">
                </th>
                <th>
                    <label for="oneQuestionPerPage">One Question Per Page</label>
                    <input type="checkbox" id="oneQuestionPerPage" name="oneQuestionPerPage">
                </th>
            </tr>
            <tr>
                <th>
                    <label for="hasPracticeMode">Has Practice Mode</label>
                    <input type="checkbox" id="hasPracticeMode" name="hasPracticeMode">
                </th>
                <th>
                    <label for="ImmediateAnswer">Immediate response on an answer</label>
                    <input type="checkbox" id="ImmediateAnswer" name="ImmediateAnswer">
                </th>
            </tr>
        </table>
        <p style="text-align: center;">
            <label for="category">Set Category For Your Quiz</label>
            <input type="text" id="category" name="category">
        </p>
    </form>

    <select id="questionType">
        <option value="1">Question-Response</option>
        <option value="2">Multiple Choice</option>
        <option value="3">Picture-Response Questions</option>
        <option value="4">Multi-Answer Questions</option>
        <option value="5">Multiple Choice with Multiple Answers</option>
    </select>
    <button id="addButton" onclick="addQuestion()" class="addQuestionButton">Add Question</button>
    <input type="submit" id="createQuiz" form="questions" value="Create Quiz" class="createQuizButton" disabled>
</div>
</body>
</html>
