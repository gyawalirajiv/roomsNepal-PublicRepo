function onFileSelected(event) {
    var fd = new FormData();
    fd.append("image", event.target.files[0]);
    $("#spinner-location").show();
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/user/profileImageUpload",
        data: fd,
        processData: false,
        contentType: false,
        cache: false,
        success: function () {
            console.log("Success");
            location.reload();
        },
        error: function (e) {
            console.log(e);
        }
    });
}

$(function () {
    $("#spinner-location").hide();
});