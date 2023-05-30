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

function showReserveModal(button) {
    const reserveModal = document.getElementById('reserveModal');
    const modal = new bootstrap.Modal(reserveModal);

    const carName = button.getAttribute('data-car-name');
    const carModelInput = reserveModal.querySelector('#carModel');
    carModelInput.value = carName;

    const carPricePerDay = button.getAttribute('data-car-price');
    const carPricePerDayInput = reserveModal.querySelector('#carPricePerDay');
    carPricePerDayInput.value = carPricePerDay;

    resetReservationForm();
    modal.show();
}

function validateDates() {
    const startDatePicker = document.getElementById('startDatePicker');
    const endDatePicker = document.getElementById('endDatePicker');

    const startDate = new Date(startDatePicker.value);
    const endDate = new Date(endDatePicker.value);

    if (startDate > endDate) {
        const dateErrorModal = new bootstrap.Modal(document.getElementById('dateErrorModal'));
        dateErrorModal.show();
        return false;
    }
    return true;
}

function updateReservationPrice() {
    const carPricePerDay = parseFloat(document.getElementById('carPricePerDay').value);
    const startDate = document.getElementById('startDatePicker').value;
    const endDate = document.getElementById('endDatePicker').value;

    if (startDate && endDate) {
        const start = new Date(startDate);
        const end = new Date(endDate);
        if (start < end) {
            const days = Math.ceil((end - start) / (1000 * 60 * 60 * 24));
            const reservationPrice = carPricePerDay * days;
            document.getElementById('reservationPrice').value = reservationPrice.toFixed(2);
        } else {
            document.getElementById('reservationPrice').value = 0;
        }
    }
}

function resetReservationForm() {
    document.getElementById("startDatePicker").value = "";
    document.getElementById("endDatePicker").value = "";
    document.getElementById("reservationPrice").value = "";
}
