var FormElement = document.forms['loginForm'];
FormElement.onsubmit = ValidateInfoForm;
function ValidateInfoForm() {
    var FormElement = document.forms['loginForm'];
    var LoginElement = FormElement.elements['login'];
    var LoginValue = LoginElement.value;
    var PasswordElement = FormElement.elements['password'];
    var PasswordValue = PasswordElement.value;
    var ErrorCounter = 0;
    if (LoginValue.length == 0) {
        ErrorCounter++;
        LoginElement.classList.add("error");
    }
    else {
        LoginElement.classList.remove("error");
    }
    if (PasswordValue.length == 0) {
        ErrorCounter++;
        PasswordElement.classList.add("error")
    }
    else {
        PasswordElement.classList.remove("error");
    }
    if (ErrorCounter != 0) {
        return false
    }
    else
        return true;
}