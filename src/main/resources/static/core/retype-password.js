$(function () {
    // $("#retype-password").attr("src", "https://composizionedigitale.files.wordpress.com/2016/11/img-ce-strummed-acoustic-2_overview_01_intro-3826120da4e60d7ad1e471b70a5acb25-d.jpg");
    var password = $("#password");
    var retypePassword = $("#retype-password");
    $("#register-submit-button").click(function (event) {
        if (password.val() != retypePassword.val()) {
            $("#retype-error-message").removeClass("hidden");
        }
    })
});
