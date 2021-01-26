var img_arr = [];
var j = 0;

function onFileSelected(event) {
    var canvas = document.createElement("canvas");
    canvas.id = "myCanvas";
    canvas.width = 100;
    canvas.height = 59;
    var ctx = canvas.getContext("2d");

    var img = new Image;
    img_arr.push(event.target.files[0]);
    img.src = URL.createObjectURL(event.target.files[0]);

    img.onload = function () {
        ctx.drawImage(img, 0, 0, 100, 59);
    }
    j++;
    for (var i = 0; i < event.target.files.length; i++) {
        $("#insert_image").append("<li class=\"upload_image\" style=\"width: 115px\" onclick='addthumbnail(" + j + ")'>\n" +
            "                            <div class=\"image_thumpfile\" style=\"width: 100px; height: 60px\" id='image" + j + "'></div>\n" +
            "                            <hr />\n" +
            "                            <div class=\"file_name\" title=\"\">\n" +
            "                                <span class=\"file_name_wrapper\" id='thumbnail_image" + j + "'></span>\n" +
            "                            </div>\n" +
            "                            <div class=\"icon\">\n" +
            "                                <i class=\"fa fa fa-minus-circle\" id=\"delete\" onclick='remove()'></i>\n" +
            "                            </div>\n" +
            "                        </li>");
    }
    $('#thumbnail_image1').html('thumbnail');
    $("#insert_image li #image" + j).append(canvas);
    $('#upload_button').removeClass('disabled');

}

function remove() {
    var parent = document.getElementById("insert_image");
    var list = parent.children;

    for (var i = 0; i < list.length; i++) {
        parent.removeChild(list[i]);
    }
}

function addthumbnail(j) {
    var parent = document.getElementById("insert_image");
    var list = parent.children;

    console.log(list[j]);

    $('#thumbnail_image' + j).html('thumbnail');
}

$(document).ready(function () {

    $("#btnSubmit").click(function (event) {
        fire_ajax_submit();

    });

});


function fire_ajax_submit() {
    var xhr = new XMLHttpRequest();
    var fd = new FormData();
    for (var i = 0; i <= img_arr.length; i++) {
        fd.append("image[]", img_arr[i])
        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/user/property/upload",
            data: fd,
            processData: false, //prevent jQuery from automatically transforming the data into a query string
            contentType: false,
            cache: false,
            success: function (data) {
                console.log("Success");
            },
            error: function (e) {
                console.log("Error");
            }
        });
    }
}
