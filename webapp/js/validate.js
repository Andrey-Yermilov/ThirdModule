var FormElement = document.forms['loginForm'];
FormElement.onsubmit = ValidateInfoForm;
function ValidateInfoForm() {
    var FormElement = document.forms['loginForm'];
    var NameElement = FormElement.elements['login'];
    var NameValue = NameElement.value;
    var EmailElement = FormElement.elements['password'];
    var EmailValue = EmailElement.value;
    var ErrorCounter = 0;
    if (NameValue.length == 0) {
        ErrorCounter++;
        NameElement.classList.add("error");
    }
    else {
        NameElement.classList.remove("error");
    }
    if (EmailValue.length == 0) {
        ErrorCounter++;
        EmailElement.classList.add("error")
    }
    else {
        EmailElement.classList.remove("error");
    }
    if (ErrorCounter != 0) {
        return false
    }
    else
        return true;
}