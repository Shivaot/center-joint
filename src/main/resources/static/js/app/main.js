const $sidebar = $('.sidebar');
const $content = $('.content');
var toast = [].slice.call(document.querySelectorAll('.toast')).map(function (toastEl) {
    return new bootstrap.Toast(toastEl, {})
})[0]
const $toast = $(".toast");

$(document).ready(function () {
    __init__();

    $('.imageUpload').on('change', function () {
        var file = new FormData();
        var uuid = $(this).closest("td").find(".image-uuid").val();
        var sheetType = $(this).closest("td").find(".sheetType-id").val();
        if (sheetType === undefined || sheetType == null) {
            sheetType = $(".sheetType-id").val();
        }
        var files = $(this)[0].files[0];
        const $td = $(this).closest("td").next();
        var $that = $td.find("a");
        file.append('file', files);
        $.ajax({
            url: '/image/upload?sheetType=' + sheetType + "&uuid=" + uuid,
            type: 'post',
            data: file,
            contentType: false,
            enctype: 'multipart/form-data',
            processData: false,
            success: function (response) {
                if (response) {
                    let resultUUID = response.data.uuid;
                    $td.find('.image-uuid').val(resultUUID);
                    $that.removeClass("d-none");
                    $that.attr("href", '/image/view?sheetType=' + sheetType + '&uuid=' + resultUUID);
                }
                showSuccessToast(response.message)
            }
        });
    });
});

function ajaxCall(url, type, data, action) {
    $.ajax({
        url: url,
        type: type,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        data: JSON.stringify(data),
        beforeSend: function () {
            $("#tata_hitachi_loader").show();
        },
        success: function (response) {
            if (response) {
                switch (action) {
                    case CONSTANT.INSPECTION_SHEET_CREATION:
                        setTimeout(() => window.location.href = CONSTANT.SHOW_INSPECTION.URL + response.data, 300);
                        break;
                    default:
                        setTimeout(() => location.reload(), 300);
                }
                showSuccessToast("Data save successful");
            } else {
                showErrorToast("Something Went Wrong");
            }
        },
        error: function (response) {
            if (response) {
                showErrorToast(response.responseJSON.message)
                setTimeout(() => location.reload(), 1500)
            } else {
                showErrorToast("Something Went Wrong")
            }
        },
        complete: function (response) {
            $("#tata_hitachi_loader").hide();
        }
    });
}

const toggleSidebar = () => {
    if ($sidebar.is(":visible")) {
        $sidebar.hide();
        $content.css('margin-left', '0%');
    } else {
        $sidebar.show();
    }
};

const __init__ = () => {
    if ($sidebar.is(":visible")) {
        $sidebar.hide();
        $content.css('margin-left', '0%');
    }
}

function decodeText(str) {
    return $("<div/>").html(str).text();
}

const showSuccessToast = (msg) => {
    $toast.removeClass("toast-error").removeClass("toast-success");
    $("#toast-body").text(msg);
    $("#toast-heading").text("Success");
    $toast.addClass('toast-success');
    toast.show()
}

const showErrorToast = (msg) => {
    $toast.removeClass("toast-success").removeClass("toast-error");
    $("#toast-body").text(msg);
    $("#toast-heading").text("Error");
    $toast.addClass('toast-error');
    toast.show()
}