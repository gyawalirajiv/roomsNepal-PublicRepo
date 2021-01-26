$(function () {
    $("<button class=\"btn btn-default\" style='float: left;'>Previous Page</button>").insertBefore($("#pagination")).on('click', function () {
        $("#pagination").find('li.active').prev().trigger("click");
    });
    $("<button class=\"btn btn-default\" style='float: right;'>Next Page</button>").insertAfter($("#pagination")).on('click', function () {
        $("#pagination").find('li.active').next().trigger("click");
    });
});