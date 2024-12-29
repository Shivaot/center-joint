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
        searching: false,
        autoWidth: false,
        columns: [
            { width : '35px' },
            { width : '80px' },
            { width : '140px' },
            { width : '400px' },
            { width : '75px' },
            { width : '180px' },
            { width : '160px' },
            { width : '85px' },
            { width : '75px' }
        ]
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

const saveInspectionSheet = (isFinalSubmit) => {
    let inspectionSheetForm = {};
    let finalSubmitValidationFail = false;
    let finalSubmitBlankRows = [];

    inspectionSheetForm.modelName = $('#modelName').val();
    inspectionSheetForm.msn = $("#msn").val();
    inspectionSheetForm.isFinalSubmit = isFinalSubmit;

    if (context === "UPDATE") {
        inspectionSheetForm.id = sheetId;
    }

    let dataSet = [];

    inspectionTable.rows().every(function (index) {
        let rowNode = $(this.node());
        let data = this.data();
        let inspectionContent = {};
        if (context === "UPDATE") inspectionContent.id = rowNode.find('.content-Id').val();
        inspectionContent.seqSerialNumber = decodeText(data[1]);
        inspectionContent.assemblyFunction = decodeText(data[2]);
        inspectionContent.checkPoint = decodeText(data[3]);
        inspectionContent.comment = decodeText(rowNode.find('.comment').val());
        if (context === 'CREATE') {
            inspectionContent.status = $('input[name=options-outlined' + index + ']:checked').val();
        } else {
            inspectionContent.status = $('input[name=options-outlined' + data[0] + ']:checked').val();
        }
        if (isFinalSubmit === true) {
            if (data[0].trim() === '' || data[2].trim() === '' || data[3].trim() === ''
                || inspectionContent.status === undefined || inspectionContent.status.trim() === '') {
                finalSubmitValidationFail = true;
                finalSubmitBlankRows.push(data[0])
            }
        }
        dataSet.push(inspectionContent);
    });

    if (finalSubmitValidationFail) {
        showErrorToast("Please fill all the field before final submission " + finalSubmitBlankRows.join(", "));
        $("#toast-message").toast('show');
        return;
    }
    inspectionSheetForm.sheetContents = dataSet;
    ajaxCall(CONSTANT.SAVE_INSPECTION.URL, CONSTANT.SAVE_INSPECTION.TYPE, inspectionSheetForm, CONSTANT.INSPECTION_SHEET_CREATION);
}