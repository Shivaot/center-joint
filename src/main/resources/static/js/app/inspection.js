const modelPopup = new bootstrap.Modal(document.getElementById('staticBackdrop'), {
    keyboard: true
})

var inspectionTable = {};

$(document).ready(function () {
    showModalPopup();

    inspectionTable = new DataTable('#inspection-table', {
        paging: false,
        info: false,
        ordering: false,
        searching: false
    });
});

const showModalPopup = () => {
    if (isInspectionSheet && (!modelName || modelName === "")) {
        modelPopup.show()
    }
}

const loadSheet = () => {
    let modelName = $('#model-name-selector').find(":selected").text();
    $("#model-name").text(modelName);
    window.location.href = "/checksheet/inspection/create?modelName=" + encodeURIComponent(modelName);
}
