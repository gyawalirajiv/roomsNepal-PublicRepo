$(function () {
    var type = $("#typeValue").val();
    if (type != null) {
        $("#property-type").val(type);
    }
    $("#tags").val($("#locationValue").val());
    $("#min-price").val($("#minPriceValue").val());
    $("#max-price").val($("#maxPriceValue").val());
});
