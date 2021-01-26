$("#property-image-button-1").hide();
$(document).on('change', '#property-image-left-button-1', function () {
    var input = $(this),
        numFiles = input.get(0).files ? input.get(0).files.length : 1,
        label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
    input.trigger('fileselect1', [numFiles, label]);
});
$(document).ready(function () {
    $(':file').on('fileselect1', function (event, numFiles, label) {
        $("#button-file-text-1").val(label);
        $("#property-image-button-2").show();
    });
});

$(document).on('change', '#property-image-left-button-2', function () {
    var input = $(this),
        numFiles = input.get(0).files ? input.get(0).files.length : 1,
        label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
    input.trigger('fileselect2', [numFiles, label]);
});
$(document).ready(function () {
    $(':file').on('fileselect2', function (event, numFiles, label) {
        $("#button-file-text-2").val(label);
        $("#property-image-button-3").show();
    });
});

$(document).on('change', '#property-image-left-button-3', function () {
    var input = $(this),
        numFiles = input.get(0).files ? input.get(0).files.length : 1,
        label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
    input.trigger('fileselect3', [numFiles, label]);
});
$(document).ready(function () {
    $(':file').on('fileselect3', function (event, numFiles, label) {
        $("#button-file-text-3").val(label);
        $("#property-image-button-4").show();
    });
});


$(document).on('change', '#property-image-left-button-4', function () {
    var input = $(this),
        numFiles = input.get(0).files ? input.get(0).files.length : 1,
        label = input.val().replace(/\\/g, '/').replace(/.*\//, '');
    input.trigger('fileselect4', [numFiles, label]);
});
$(document).ready(function () {
    $(':file').on('fileselect4', function (event, numFiles, label) {
        $("#button-file-text-4").val(label);
    });
});
