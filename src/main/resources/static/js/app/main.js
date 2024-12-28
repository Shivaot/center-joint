const $sidebar = $('.sidebar');
const $content = $('.content');

$(document).ready(function () {
    __init__();
});

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