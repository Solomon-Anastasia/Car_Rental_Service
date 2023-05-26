function isValidData() {
    const password = document.getElementById("exampleInputPassword").value;
    const repeatPassword = document.getElementById("exampleRepeatPassword").value;

    if (password !== repeatPassword) {
        const toast = document.getElementById("passwordMatchToast");
        const toastInstance = new bootstrap.Toast(toast);
        toastInstance.show();
        return false;
    }
    return true;
}

function closeRegisterToast() {
    const toast = document.getElementById("passwordMatchToast");
    const toastInstance = new bootstrap.Toast(toast);
    toastInstance.hide();
}

function closeLoginToast() {
    const toast = document.getElementById("credentialsMatchToast");
    const toastInstance = new bootstrap.Toast(toast);
    toastInstance.hide();
}

function closeEmailTakenToast() {
    const toast = document.getElementById("emailTakenToast");
    const toastInstance = new bootstrap.Toast(toast);
    toastInstance.hide();
}

window.addEventListener('DOMContentLoaded', function() {
    const showToast = document.getElementById("showToast").value;

    if (showToast === 'true') {
        const toast = document.getElementById("credentialsMatchToast");
        const toastInstance = new bootstrap.Toast(toast);
        toastInstance.show();
    }
});

window.addEventListener('DOMContentLoaded', function() {
    const showToast = document.getElementById("showToast").value;

    if (showToast === 'true') {
        const toast = document.getElementById("emailTakenToast");
        const toastInstance = new bootstrap.Toast(toast);
        toastInstance.show();
    }
});