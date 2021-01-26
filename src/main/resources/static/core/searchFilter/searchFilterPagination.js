$(function () {
    var propertiesCount = $("#propertiesCount").val();
    var divisor = Math.ceil(propertiesCount / 10);
    var paniationPages = divisor;


    for (var i = 0; i < paniationPages; i++) {
        $("#pagination").append("<li class=\"pagination-numbers\"><a href='/searchFilter?page=" + i + "'>" + (i + 1) + "</a></li>");
    }

    var pageNumber = parseInt($("#page").val()) + 1;
    $("#pagination li:nth-child(" + pageNumber + ")").addClass("active");

    var paginationWidth = $("#pagination").width();
    $("#pagination").css("width", paginationWidth + 1);
    $("#pagination").css("margin", "0 auto");
    $("#pagination").css("display", "block");
    // $("<button class=\"btn btn-default\" style='float: left;'>Previous Page</button>").insertBefore($("#pagination")).on('click', function () {
    //     $("#pagination").find('li.active').prev().trigger("click");
    // });
    // $("<button class=\"btn btn-default\" style='float: right;'>Next Page</button>").insertAfter($("#pagination")).on('click', function () {
    //     $("#pagination li.active").trigger("click");
    // });
});
