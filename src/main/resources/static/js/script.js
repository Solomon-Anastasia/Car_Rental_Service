function validateForm() {
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

function closeToast() {
    const toast = document.getElementById("passwordMatchToast");
    const toastInstance = new bootstrap.Toast(toast);
    toastInstance.hide();
}