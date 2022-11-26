$(document).ready(function () {

    let submit = $("#submit");
    let firstName = $("#firstName");
    let firstNameErr = $("#firstName-err");
    var firstNameIsValid = false;
    let secondName = $("#secondName");
    let secondNameErr = $("#secondName-err");
    var secondNameIdValid = false;
    let email = $("#email");
    let emailErr = $("#email-err");
    var emailIsValid = false;
    let phone = $("#phone");
    let phoneErr = $("#phone-err");
    var phoneIsValid = false;
    let birthDate = $("#birthDate");
    let birthDateErr = $("#birthDate-err");
    var birthDateIsValid = false;
    let pass = $("#password");
    let passErr = $("#password-err");
    var passIsValid = false;
    let passRepeat = $("#repeatPass");
    let passRepeatErr = $("#repeatPass-err");
    var passRepeatIsValid = false;

    submit.click(function (e) {
        firstNameIsValid = firstName.val().length > 1;
        if (firstNameIsValid) {
            firstName.css('border', '1px solid green');
            firstNameErr.attr('style', 'display:none');
        } else {
            firstName.css('border', '1px solid red');
            firstNameErr.attr('style', 'display:block; color:red');
        }
        secondNameIdValid = secondName.val().length > 1;
        if (secondNameIdValid) {
            secondName.css('border', '1px solid green');
            secondNameErr.attr('style', 'display:none');
        } else {
            secondName.css('border', '1px solid red');
            secondNameErr.attr('style', 'display:block; color:red');
        }
        emailIsValid = isEmailValid(email.val());
        $("#email-err-server").remove();
        if (emailIsValid) {
            email.css('border', '1px solid green');
            emailErr.attr('style', 'display:none');
        } else {
            email.css('border', '1px solid red');
            emailErr.attr('style', 'display:block; color:red');
        }
        phoneIsValid = isPhoneValid(phone.val());
        if (phoneIsValid) {
            phone.css('border', '1px solid green');
            phoneErr.attr('style', 'display:none');
        } else {
            phone.css('border', '1px solid red');
            phoneErr.attr('style', 'display:block; color:red');
        }
        birthDateIsValid = isDateValid(birthDate.val());
        if (birthDateIsValid) {
            birthDate.css('border', '1px solid green');
            birthDateErr.attr('style', 'display:none');
        } else {
            birthDate.css('border', '1px solid red');
            birthDateErr.attr('style', 'display:block; color:red');
        }
        passIsValid = isPasswordValid(pass.val());
        if (passIsValid) {
            pass.css('border', '1px solid green');
            passErr.attr('style', 'display:none');
        } else {
            pass.css('border', '1px solid red');
            passErr.attr('style', 'display:block; color:red');
        }
        passRepeatIsValid = passRepeat.val() === pass.val() && passRepeat.val().length > 0;
        if (passRepeatIsValid) {
            passRepeat.css('border', '1px solid green');
            passRepeatErr.attr('style', 'display:none');
        } else {
            passRepeat.css('border', '1px solid red');
            passRepeatErr.attr('style', 'display:block; color:red');
        }
        if (!(firstNameIsValid && secondNameIdValid && emailIsValid && phoneIsValid
            && birthDateIsValid && passIsValid && passRepeatIsValid)) {
            e.preventDefault();
        }
    });


    function isEmailValid(email) {
        let regexEmail = /^([A-Za-z0-9_.,#$"'!?\\/*\-+%&^;:()])+@([A-Za-z-_]{2,15})\.([A-Za-z]{2,3})$/;
        return regexEmail.test(email);
    }

    function isPhoneValid(phone) {
        let regexPhone = /^\+?([0-9]{2})[- ]?([0-9]{3})[- ]?([0-9]{3})[- ]?([0-9]{2})[- ]?([0-9]{2})$/;
        return regexPhone.test(phone);
    }

    function isPasswordValid(pass) {
        let regexPass = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
        return regexPass.test(pass);
    }

    function isDateValid(date) {
        let regexDate = /^[\d]{2}[.][\d]{2}[.][\d]{4}$/;
        if (regexDate.test(date)) {
            let year = date.substr(6, 4);
            if (year < 2004 && year > 1920) {
                return true;
            }
        } else {
            return false;
        }
    }
});