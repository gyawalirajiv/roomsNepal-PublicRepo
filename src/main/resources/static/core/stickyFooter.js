$(function () {
    var footerHeight = $('footer').height();
    var navbarHeight = $('.navbar').height();
    var windowHeight = $(window).height();
    var bodyHeight = $('body').height();

    if ((navbarHeight + bodyHeight + footerHeight) < windowHeight) {
        var bodyHeight = windowHeight - footerHeight - navbarHeight - 1;
        $('body').css("height", bodyHeight + "px");
    }
});
