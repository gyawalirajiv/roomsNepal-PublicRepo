$(function () {
    $(".home-filter-tab li").click(function () {
        $(".home-filter-tab li.active").removeClass("active");
        $(this).addClass("active");
    });
});
