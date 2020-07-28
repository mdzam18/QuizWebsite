let questionId = 1;
let questionAnswerCounter = new Map();


function addQuestion() {
    let questionContainer = getById("questions");
    let value = parseInt(getById("questionType").value);
    let newElem = createQuestionDiv(value);
    if(newElem != null) {
        questionContainer.appendChild(newElem);
        questionId++;
    }
}

const QUESTION_RESPONSE = 1;
const MULTIPLE_CHOICE_QUESTION = 2;
const PICTURE_RESPONSE_QUESTION = 3;
const MULTI_ANSWER_QUESTION = 4;
const MULTIPLE_CHOICE_AND_ANSWER_QUESTION = 5;

function createQuestionDiv(value) {
    let result;

    switch(value) {
        case QUESTION_RESPONSE:
            result = getQuestionResponse();
            break;
        case MULTIPLE_CHOICE_QUESTION:
            result = getMultipleChoiceQuestion();
            break;
        case PICTURE_RESPONSE_QUESTION:
            result = getPictureResponseQuestion();
            break;
        case MULTI_ANSWER_QUESTION:
            result = getMultiAnswerQuestion();
            break;
        case MULTIPLE_CHOICE_AND_ANSWER_QUESTION:
            result = getMultiChoiceAndAnswerQuestion();
            break;
        default: result = null;
    }

    return result;
}

/* Question Response */
function getQuestionResponse() {
    let containerId = "ID_" + questionId;
    let questionTextId  = "ID_" + questionId + "_questionText";
    let answerListId = "ID_" + questionId + "_answer_list";

    let elem = createElement("div");
    elem.className = "questionDiv";
    elem.id = containerId;

    let typeValue = createElement("input");
    typeValue.name = "ID_" + questionId + "_type";
    typeValue.type = "hidden";
    typeValue.value = getById("questionType").value

    let questionTextLabel = createElement("label");
    questionTextLabel.innerHTML = "Question:";
    questionTextLabel.setAttribute("for", questionTextId);

    let questionText = createElement("textarea");
    questionText.name = questionTextId;
    questionText.id = questionTextId;
    questionText.className = "questionField";
    questionText.form = "questions";
    questionText.required = true;

    let deleteThis = createElement("button");
    deleteThis.innerHTML = "Delete Question";
    deleteThis.setAttribute("onclick", "removeQuestion('" + containerId + "', " + questionId + ")");

    let scoreInputLabel = createElement("label");
    scoreInputLabel.innerHTML = "Question Score";
    let scoreInput = createElement("input");
    scoreInput.type = "number";
    scoreInput.min = "0";
    scoreInput.value = "0";
    scoreInput.name = "ID_" + questionId + "_score";
    scoreInput.id = scoreInput.name;
    scoreInputLabel.for = scoreInput.id;

    let answerListP = createElement("p");
    answerListP.innerHTML = "Answers";

    let answerList = createElement("ul");
    answerList.id = answerListId;
    answerList.style = "list-style-type: none;";
    questionAnswerCounter.set(questionId, 1);

    let answerRequired = createElement("li");
    answerRequired.id = "ID_" + questionId + "_answerN_1";

    let answerRequiredInput = createElement("input");
    answerRequiredInput.type = "text";
    answerRequiredInput.id = answerRequired.id + "_input";
    answerRequiredInput.name = "ID_" + questionId + "_answers";
    answerRequiredInput.required = true;

    answerRequired.appendChild(answerRequiredInput);
    answerList.appendChild(answerRequired);

    let addAnswer = createElement("input");
    addAnswer.type = "button";
    addAnswer.value = "Add Alternative Answer";
    addAnswer.setAttribute("onclick", "addQuestionResponseAnswer('" + answerListId + "', " + questionId + ")");

    appendElements(elem, typeValue, questionTextLabel, questionText ,deleteThis ,scoreInputLabel, scoreInput, answerListP, answerList, addAnswer);

    /*elem.appendChild(typeValue);
    elem.appendChild(questionTextLabel);
    elem.appendChild(questionText);
    elem.appendChild(deleteThis);
    elem.appendChild(scoreInputLabel);
    elem.appendChild(scoreInput);
    elem.appendChild(answerListP);
    elem.appendChild(answerList);
    elem.appendChild(addAnswer);*/
    return elem;
}
function addQuestionResponseAnswer(elemId, qId) {
    let answerN = questionAnswerCounter.get(qId);
    answerN++;
    questionAnswerCounter.set(qId, answerN);

    let answerNId = "ID_" + qId + "_answerN_" + answerN;

    let result = createElement("li");
    result.id = answerNId;

    let input = createElement("input");
    input.type = "text";
    input.id = answerNId + "_input";
    input.name = "ID_" + qId + "_answers";

    let deleteAnswer = createElement("input");
    deleteAnswer.type = "button";
    deleteAnswer.value = "Remove Answer";
    deleteAnswer.id = answerNId + "_delete";
    deleteAnswer.setAttribute("onclick", "addQuestionResponseDeleteAnswer('" + elemId + "', '" + answerNId + "')");

    result.appendChild(input);
    result.appendChild(deleteAnswer);

    getById(elemId).appendChild(result);
}
function addQuestionResponseDeleteAnswer(parId, answId) {
    let list = getById(parId);
    for(let i = 0; i<list.childNodes.length; i++) {
        if(list.childNodes[i].id == answId) {
            list.removeChild(list.childNodes[i]);
            break;
        }
    }
}


/* Multiple Choice Question */
function getMultipleChoiceQuestion() {
    let containerId = "ID_" + questionId;
    let questionTextId  = "ID_" + questionId + "_questionText";
    let answerListId = "ID_" + questionId + "_answer_list";

    let elem = createElement("div");
    elem.className = "questionDiv";
    elem.id = containerId;

    let typeValue = createElement("input");
    typeValue.name = "ID_" + questionId + "_type";
    typeValue.type = "hidden";
    typeValue.value = getById("questionType").value

    let questionTextLabel = createElement("label");
    questionTextLabel.innerHTML = "Question:";
    questionTextLabel.setAttribute("for", questionTextId);
    let questionText = createElement("textarea");
    questionText.name = questionTextId;
    questionText.id = questionTextId;
    questionText.className = "questionField";
    questionText.form = "questions";
    questionText.required = true;
    let deleteThis = createElement("button");
    deleteThis.innerHTML = "Delete Question";
    deleteThis.setAttribute("onclick", "removeQuestion('" + containerId + "', " + questionId + ")");

    let scoreInputLabel = createElement("label");
    scoreInputLabel.innerHTML = "Question Score";
    let scoreInput = createElement("input");
    scoreInput.type = "number";
    scoreInput.min = "0";
    scoreInput.value = "0";
    scoreInput.name = "ID_" + questionId + "_score";
    scoreInput.id = scoreInput.name;
    scoreInputLabel.for = scoreInput.id;

    let answerListP = createElement("p");
    answerListP.innerHTML = "Answers";

    let answerList = createElement("ul");
    answerList.id = answerListId;
    answerList.style = "list-style-type: none;";
    questionAnswerCounter.set(questionId, 2);

    let answerRequiredLi = createElement("li");
    answerRequiredLi.id = "ID_" + questionId + "_answerN_1";

    let answerRequired2Li = createElement("li");
    answerRequired2Li.id = "ID_" + questionId + "_answerN_2";

    let answerRequiredRadio = createElement("input");
    answerRequiredRadio.type = "radio";
    answerRequiredRadio.name = "ID_" + questionId + "_answer_true";
    answerRequiredRadio.required = true;

    let answerRequired = createElement("input");
    answerRequired.type = "text";
    answerRequired.name = "ID_" + questionId + "_answerN_1_input";
    answerRequired.required = true;
    answerRequiredRadio.value = answerRequired.name;

    let answerRequired2Radio = createElement("input");
    answerRequired2Radio.type = "radio";
    answerRequired2Radio.name = "ID_" + questionId + "_answer_true";

    let answerRequired2 = createElement("input");
    answerRequired2.type = "text";
    answerRequired2.name = "ID_" + questionId + "_answerN_2_input";
    answerRequired2.required = true;
    answerRequired2Radio.value = answerRequired2.name;

    answerRequiredLi.appendChild(answerRequiredRadio);
    answerRequiredLi.appendChild(answerRequired);

    answerRequired2Li.appendChild(answerRequired2Radio);
    answerRequired2Li.appendChild(answerRequired2);

    answerList.appendChild(answerRequiredLi);
    answerList.appendChild(answerRequired2Li);

    let addAnswer = createElement("input");
    addAnswer.type = "button";
    addAnswer.value = "Add Answer";
    addAnswer.setAttribute("onclick", "addMultipleChoiceQuestionAnswer('" + answerListId + "', " + questionId + ")");

    // appendElements()
    elem.appendChild(typeValue);
    elem.appendChild(questionTextLabel);
    elem.appendChild(questionText);
    elem.appendChild(deleteThis);
    elem.appendChild(scoreInputLabel);
    elem.appendChild(scoreInput);
    elem.appendChild(answerListP);
    elem.appendChild(answerList);
    elem.appendChild(addAnswer);
    return elem;
}
function addMultipleChoiceQuestionAnswer(elemId, qId) {
    let answerN = questionAnswerCounter.get(qId);
    answerN++;
    questionAnswerCounter.set(qId, answerN);

    let answerNId = "ID_" + qId + "_answerN_" + answerN;

    let result = createElement("li");
    result.id = answerNId;

    let radio = createElement("input");
    radio.type = "radio";
    radio.id = "ID_" + qId + "_answer_true";
    radio.name = "ID_" + qId + "_answer_true";

    let input = createElement("input");
    input.type = "text";
    input.id = answerNId + "_input";
    input.name = answerNId + "_input";
    radio.value = input.name;

    let deleteAnswer = createElement("input");
    deleteAnswer.type = "button";
    deleteAnswer.value = "Remove Answer";
    deleteAnswer.id = answerNId + "_delete";
    deleteAnswer.setAttribute("onclick", "addMultipleChoiceQuestionDeleteAnswer('" + elemId + "', '" + answerNId + "')");

    result.appendChild(radio);
    result.appendChild(input);
    result.appendChild(deleteAnswer);

    getById(elemId).appendChild(result);
}
function addMultipleChoiceQuestionDeleteAnswer(parId, answId) {
    addQuestionResponseDeleteAnswer(parId, answId);
}


/* Picture Response Question */
function getPictureResponseQuestion() {
    let containerId = "ID_" + questionId;
    let questionTextId  = "ID_" + questionId + "_questionText";
    let answerListId = "ID_" + questionId + "_answer_list";

    let elem = createElement("div");
    elem.className = "questionDiv";
    elem.id = containerId;

    let typeValue = createElement("input");
    typeValue.name = "ID_" + questionId + "_type";
    typeValue.type = "hidden";
    typeValue.value = getById("questionType").value

    let questionTextLabel = createElement("label");
    questionTextLabel.innerHTML = "Question:";
    questionTextLabel.setAttribute("for", questionTextId);
    let questionText = createElement("textarea");
    questionText.name = questionTextId;
    questionText.id = questionTextId;
    questionText.className = "questionField";
    questionText.form = "questions";
    questionText.required = true;
    let deleteThis = createElement("button");
    deleteThis.innerHTML = "Delete Question";
    deleteThis.setAttribute("onclick", "removeQuestion('" + containerId + "', " + questionId + ")");

    let scoreInputLabel = createElement("label");
    scoreInputLabel.innerHTML = "Question Score";
    let scoreInput = createElement("input");
    scoreInput.type = "number";
    scoreInput.min = "0";
    scoreInput.value = "0";
    scoreInput.name = "ID_" + questionId + "_score";
    scoreInput.id = scoreInput.name;
    scoreInputLabel.for = scoreInput.id;

    let imgUrl = createElement("input");
    imgUrl.type = "URL";
    imgUrl.id = "ID_" + questionId + "_imgURL";
    imgUrl.placeholder = "Enter Valid Image Url";
    imgUrl.className = "urlImageInput";
    imgUrl.name = "ID_" + questionId + "_imgURL";
    imgUrl.required = true;

    let imgUrlTag = createElement("img");
    imgUrlTag.id = "ID_" + questionId + "_imgURL_img";
    imgUrlTag.style = "max-width: 800px;";
    imgUrl.setAttribute("onchange", "addPictureResponseQuestionUpdateImg('" + imgUrl.id + "', '" + imgUrlTag.id + "')");

    let answerListP = createElement("p");
    answerListP.innerHTML = "Answers";

    let answerList = createElement("ul");
    answerList.id = answerListId;
    answerList.style = "list-style-type: none;";
    questionAnswerCounter.set(questionId, 1);

    let answerRequired = createElement("li");
    answerRequired.id = "ID_" + questionId + "_answerN_1";

    let answerRequiredInput = createElement("input");
    answerRequiredInput.type = "text";
    answerRequiredInput.id = answerRequired.id + "_input";
    answerRequiredInput.name = "ID_" + questionId + "_answers";
    answerRequiredInput.required = true;

    answerRequired.appendChild(answerRequiredInput);
    answerList.appendChild(answerRequired);

    let addAnswer = createElement("input");
    addAnswer.type = "button";
    addAnswer.value = "Add Alternative Answer";
    addAnswer.setAttribute("onclick", "addPictureResponseQuestionAnswer('" + answerListId + "', " + questionId + ")");

    // appendElements()
    elem.appendChild(typeValue);
    elem.appendChild(questionTextLabel);
    elem.appendChild(questionText);
    elem.appendChild(deleteThis);
    elem.appendChild(scoreInputLabel);
    elem.appendChild(scoreInput);
    elem.appendChild(imgUrl);
    elem.appendChild(imgUrlTag);
    elem.appendChild(answerListP);
    elem.appendChild(answerList);
    elem.appendChild(addAnswer);

    return elem;
}
function addPictureResponseQuestionAnswer(elemId, qId) {
    addQuestionResponseAnswer(elemId, qId);
}
function addPictureResponseQuestionDeleteAnswer(parId, answId) {
    addQuestionResponseDeleteAnswer(parId, answId);
}
function addPictureResponseQuestionUpdateImg(fromUrl, toImg) {
    getById(toImg).src = getById(fromUrl).value;
}

/* Multi Answer Question */
function getMultiAnswerQuestion() {
    let containerId = "ID_" + questionId;
    let questionTextId  = "ID_" + questionId + "_questionText";
    let answerListId = "ID_" + questionId + "_answer_list";

    let elem = createElement("div");
    elem.className = "questionDiv";
    elem.id = containerId;

    let typeValue = createElement("input");
    typeValue.name = "ID_" + questionId + "_type";
    typeValue.type = "hidden";
    typeValue.value = getById("questionType").value;

    let questionTextLabel = createElement("label");
    questionTextLabel.innerHTML = "Question:";
    questionTextLabel.setAttribute("for", questionTextId);
    let questionText = createElement("textarea");
    questionText.name = questionTextId;
    questionText.id = questionTextId;
    questionText.className = "questionField";
    questionText.form = "questions";
    questionText.required = true;
    let deleteThis = createElement("button");
    deleteThis.innerHTML = "Delete Question";
    deleteThis.setAttribute("onclick", "removeQuestion('" + containerId + "', " + questionId + ")");

    let scoreInputLabel = createElement("label");
    scoreInputLabel.innerHTML = "Question Score";
    let scoreInput = createElement("input");
    scoreInput.type = "number";
    scoreInput.min = "0";
    scoreInput.value = "0";
    scoreInput.name = "ID_" + questionId + "_score";
    scoreInput.id = scoreInput.name;
    scoreInputLabel.for = scoreInput.id;

    let keepOrderLabel = createElement("label");
    keepOrderLabel.innerHTML = "Keep Order";

    let keepOrderCheckbox = createElement("input");
    keepOrderCheckbox.type = "checkbox";
    keepOrderCheckbox.name = "ID_" + questionId + "_keepOrder";
    keepOrderCheckbox.id = keepOrderCheckbox.name;
    keepOrderLabel.for = keepOrderCheckbox.id;

    let answerListP = createElement("p");
    answerListP.innerHTML = "Answers";

    let answerList = createElement("ul");
    answerList.id = answerListId;
    answerList.style = "list-style-type: none;";

    let answerRequired = createElement("li");
    answerRequired.id = "ID_" + questionId + "_answerN_1";

    let answerRequiredInput = createElement("input");
    answerRequiredInput.type = "text";
    answerRequiredInput.name = "ID_" + questionId + "_answers";
    answerRequiredInput.id = answerRequired.id + "_input";
    answerRequiredInput.required = true;

    let answerRequired2 = createElement("li");
    answerRequired2.id = "ID_" + questionId + "_answerN_2";

    let answerRequired2Input = createElement("input");
    answerRequired2Input.type = "text";
    answerRequired2Input.name = "ID_" + questionId + "_answers";
    answerRequired2Input.id = answerRequired2.id + "_input";
    answerRequired2Input.required = true;

    answerRequired.appendChild(answerRequiredInput);
    answerRequired2.appendChild(answerRequired2Input);

    answerList.appendChild(answerRequired);
    answerList.appendChild(answerRequired2);

    questionAnswerCounter.set(questionId, 2);

    let addAnswer = createElement("input");
    addAnswer.type = "button";
    addAnswer.value = "Add Answer";
    addAnswer.setAttribute("onclick", "addMultiAnswerQuestionAnswer('" + answerListId + "', " + questionId + ")");

    // appendElements()
    elem.appendChild(typeValue);
    elem.appendChild(questionTextLabel);
    elem.appendChild(questionText);
    elem.appendChild(deleteThis);
    elem.appendChild(scoreInputLabel);
    elem.appendChild(scoreInput);
    elem.appendChild(keepOrderLabel);
    elem.appendChild(keepOrderCheckbox);
    elem.appendChild(answerListP);
    elem.appendChild(answerList);
    elem.appendChild(addAnswer);
    return elem;
}
function addMultiAnswerQuestionAnswer(elemId, qId) {
    addQuestionResponseAnswer(elemId, qId)
}

/* Multi Choice And Answer Question */
function getMultiChoiceAndAnswerQuestion() {
    let containerId = "ID_" + questionId;
    let questionTextId  = "ID_" + questionId + "_questionText";
    let answerListId = "ID_" + questionId + "_answer_list";

    let elem = createElement("div");
    elem.className = "questionDiv";
    elem.id = containerId;

    let typeValue = createElement("input");
    typeValue.name = "ID_" + questionId + "_type";
    typeValue.type = "hidden";
    typeValue.value = getById("questionType").value;

    let questionTextLabel = createElement("label");
    questionTextLabel.innerHTML = "Question:";
    questionTextLabel.setAttribute("for", questionTextId);
    let questionText = createElement("textarea");
    questionText.name = questionTextId;
    questionText.id = questionTextId;
    questionText.className = "questionField";
    questionText.form = "questions";
    questionText.required = true;
    let deleteThis = createElement("button");
    deleteThis.innerHTML = "Delete Question";
    deleteThis.setAttribute("onclick", "removeQuestion('" + containerId + "', " + questionId + ")");

    let scoreInputLabel = createElement("label");
    scoreInputLabel.innerHTML = "Question Score";
    let scoreInput = createElement("input");
    scoreInput.type = "number";
    scoreInput.min = "0";
    scoreInput.value = "0";
    scoreInput.name = "ID_" + questionId + "_score";
    scoreInput.id = scoreInput.name;
    scoreInputLabel.for = scoreInput.id;

    let answerListP = createElement("p");
    answerListP.innerHTML = "Answers";

    let answerList = createElement("ul");
    answerList.id = answerListId;
    answerList.style = "list-style-type: none;";

    let requiredLi = createElement("li");
    requiredLi.id = "ID_" + questionId + "_answerN_1_li";

    let requiredName = "ID_" + questionId + "_answerN_1";

    let requiredCheckbox = createElement("input");
    requiredCheckbox.type = "checkbox";
    requiredCheckbox.name = requiredName;
    requiredCheckbox.checked = true;

    let requiredInput = createElement("input");
    requiredInput.type = "text";
    requiredInput.name = requiredName;
    requiredInput.required = true;

    requiredLi.appendChild(requiredCheckbox);
    requiredLi.appendChild(requiredInput);

    let required2Li = createElement("li");
    required2Li.id = "ID_" + questionId + "_answerN_2_li";

    let required2Name = "ID_" + questionId + "_answerN_2";

    let required2Checkbox = createElement("input");
    required2Checkbox.type = "checkbox";
    required2Checkbox.name = required2Name;

    let required2Input = createElement("input");
    required2Input.type = "text";
    required2Input.name = required2Name;
    required2Input.required = true;

    required2Li.appendChild(required2Checkbox);
    required2Li.appendChild(required2Input);

    questionAnswerCounter.set(questionId, 2);

    answerList.appendChild(requiredLi);
    answerList.appendChild(required2Li);

    let addAnswer = createElement("input");
    addAnswer.type = "button";
    addAnswer.value = "Add Alternative Answer";
    addAnswer.setAttribute("onclick", "addMultiChoiceAndAnswerQuestionAnswer('" + answerListId + "', " + questionId + ")");

    // appendElements()
    elem.appendChild(typeValue);
    elem.appendChild(questionTextLabel);
    elem.appendChild(questionText);
    elem.appendChild(deleteThis);
    elem.appendChild(scoreInputLabel);
    elem.appendChild(scoreInput);
    elem.appendChild(answerListP);
    elem.appendChild(answerList);
    elem.appendChild(addAnswer);
    return elem;
}
function addMultiChoiceAndAnswerQuestionAnswer(elemId, qId) {
    let answerN = questionAnswerCounter.get(qId);
    answerN++;
    questionAnswerCounter.set(qId, answerN);

    let answerNId = "ID_" + qId + "_answerN_" + answerN;

    let result = createElement("li");
    result.id = answerNId;

    let inputName = "ID_" + qId + "_answerN_" + answerN;

    let inputCheck = createElement("input");
    inputCheck.type = "checkbox";
    inputCheck.name = inputName;

    let input = createElement("input");
    input.type = "text";
    input.name = inputName;

    let deleteAnswer = createElement("input");
    deleteAnswer.type = "button";
    deleteAnswer.value = "Remove Answer";
    deleteAnswer.id = answerNId + "_delete";
    deleteAnswer.setAttribute("onclick", "addMultiChoiceAndAnswerQuestionDeleteAnswer('" + elemId + "', '" + answerNId + "')");

    result.appendChild(inputCheck);
    result.appendChild(input);
    result.appendChild(deleteAnswer);

    getById(elemId).appendChild(result);
}
function addMultiChoiceAndAnswerQuestionDeleteAnswer(parOd, answId) {
    addMultipleChoiceQuestionDeleteAnswer(parOd, answId);
    for(let i = 0; i<parOd.childNodes.length; i++) {
        alert(parOd.childNodes[i].id)
    }
}

/* Other Functions */
function removeQuestion(elemId, qId) {
    let par = getById(elemId);
    par.parentNode.removeChild(par);
    questionAnswerCounter.delete(qId);
}

function getById(id) {
    return document.getElementById(id);
}
function createElement(tag) {
    return document.createElement(tag);
}
function createInputByType(type) {
    let result = createElement("input");
    result.type = type;
    return result;
}

function appendElements() {
    let argLen = arguments.length;
    if(argLen >= 2) {
        let toAppend = arguments[0];
        for(let i = 1; i<argLen; i++) {
            toAppend.appendChild(arguments[i]);
        }
    }
}
