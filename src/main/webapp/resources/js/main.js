var module = (function () {

    var $newPassword = $('#newPassword');
    var $newPasswordError = $('#newPasswordError');
    var $confirmPassword = $('#confirmPassword');
    var $confirmPasswordError = $('#confirmPasswordError');
    var $form = $('form');
    var $btnSubmit = $('button');

    function init() {
        $btnSubmit.on('click', _submitForm);
    }

    function _submitForm() {
        if (_validateForm()) {
            $form.submit();
        }
    }

    function _validateForm() {
        return _validateNewPassword() & _validateConfirmPassword();
    }

    function _validateNewPassword() {
        if ($newPassword.val().length >= 6) {
            $newPasswordError.hide();
            return true;
        }
        $newPasswordError.show();
        return false;
    }

    function _validateConfirmPassword() {
        if ($confirmPassword.val() === $newPassword.val()) {
            $confirmPasswordError.hide();
            return true;
        }
        $confirmPasswordError.show();
        return false;
    }

    return {
        init: init
    };

})();

$(document).ready(function() {
    module.init();
});
