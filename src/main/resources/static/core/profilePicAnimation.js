$(function () {
    var profileImage = $("#profileImage");
    var profileImageText = $("#profileImageText");
    profileImageText.hide();

    profileImage.mouseenter(function () {
        $(this).fadeTo(250, 0.7);
        profileImageText.fadeTo(250, 1.0);
    });

    profileImage.mouseleave(function () {
        $(this).fadeTo(250, 1.0);
        profileImageText.fadeTo(250, 0);
    });
});

$(function () {
    var profileImage = $("#profileImage");

    var source = $(this).attr("src");
})
