var ajaxPropertiesData;
var properties_per_page = 8;
var currentPaginationNumber = 1;

$(function () {
    $("#house-property-filter").click(function () {
        $.ajax({
            url: "/propertyListHouse",
            type: "GET",
            success: function (data) {
                ajaxPropertiesData = data;
                $("#pagination").empty();
                $("#pagination").append("<li class=\"active pagination-numbers\" data-id=\"1\"><a>1</a></li>");
                $("#home-property-list").empty();

                $(".property-tabs>li.active").removeClass("active");
                $("#property-tabs-house").addClass("active");

                afterAJAXSucess(data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    });

    function afterAJAXSucess(data) {
        var data_divisor = (data.length - 1) / properties_per_page;
        data_divisor = Math.floor(data_divisor);
        ajaxDataAndDomManipulator(1);

        for (var i = 0; i < data_divisor; i++) {
            $("#pagination").append("<li class='pagination-numbers' data-id='" + (i + 2) + "'><a>" + (i + 2) + "</a></li>");
        }

        var paginationWidth = $("#pagination").width();
        $("#pagination").css("width", paginationWidth + 1);
        $("#pagination").css("margin", "0 auto");
        $("#pagination").css("display", "block");
    }

    $("#pagination").on("click", "li", function () {
        var clickedPaginationNumber = $(this).data().id;

        if (clickedPaginationNumber != currentPaginationNumber) {
            $("#pagination>li.active").removeClass("active");
            $(this).addClass("active");
            currentPaginationNumber = clickedPaginationNumber;

            ajaxDataAndDomManipulator(clickedPaginationNumber);
        }
    });

    function ajaxDataAndDomManipulator(indexId) {
        $("#home-property-list").empty();
        var pageNumber = indexId - 1;
        var loopStartingIndex = pageNumber * 8;
        var loopEndingIndex = loopStartingIndex + 8;

        for (; loopStartingIndex < loopEndingIndex; loopStartingIndex++) {
            var value = ajaxPropertiesData[loopStartingIndex];
            if (value == null) {
                break;
            }
            $("#home-property-list").append("<div class='tab-property col-md-3 col-sm-6 col-xs-6'><a href='/property/" + value.id + "'><img src='" + value.bathroom + "' class='tab-img img-responsive'><div class='tab-property-details'><h5 style='text-align: center'>Rs. " + value.waterSupply + " - " + value.type + "</h5><p style='text-align: center'><b>" + value.location + "</b></p></div></a></div>");
        }
    }
});
