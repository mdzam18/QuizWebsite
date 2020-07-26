<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="styles/createQuiz_style1.css">
    <script src="js_code/createQuiz_js1.js"></script>

    <title>Create Quiz</title>
</head>
<body>
<div id="content">
    <form id="questions" action="/CreateQuizServlet" method="POST">
        <p style="text-align: center;">
            <label for="questionRandomSequence">Random Sequence of Questions</label>
            <input type="checkbox" id="questionRandomSequence" name="questionRandomSequence">

            <label for="oneQuestionPerPage">One Question Per Page</label>
            <input type="checkbox" id="oneQuestionPerPage" name="oneQuestionPerPage">

            <label for="hasPracticeMode">Has Practice Mode</label>
            <input type="checkbox" id="hasPracticeMode" name="hasPracticeMode">
        </p>
        <input id="quiz_name" name="quiz_name" type="text" placeholder="Quiz Name" required>
    </form>

    <select id="questionType">
        <option value="1">Question-Response</option>
        <option value="2">Multiple Choice</option>
        <option value="3">Picture-Response Questions</option>
        <option value="4">Multi-Answer Questions</option>
        <option value="5">Multiple Choice with Multiple Answers</option>
    </select>
    <button id="addButton" onclick="addQuestion()" class="addQuestionButton">Add Question</button>
    <input type="submit" id="createQuiz" form="questions" value="Create Quiz" class="createQuizButton">
</div>
</body>
</html>
