$(document).ready(function () {
    var table = $('#userTable').DataTable({
        "sAjaxSource": "/admin/user/all",
        "sAjaxDataProp": "",
        "order": [[0, "asc"]],
        "aoColumns": [
            {"mData": "id"},
            {"mData": "fullName"},
            {"mData": "email"},
            {"mData": "primaryPhoneNumber"},
            {"mData": "createdDate"},
            {"mData": "active"},
            {
                "render": function (mData, type, JsonResultRow, meta) {
                    return '<a href="/admin/user/viewdetail/' + JsonResultRow.id + '"><i class="fa fa-info-circle"></i></a>';
                }
            },
            {
                "render": function (mData, type, JsonResultRow, meta) {
                    return '<a href="/admin/user/update/' + JsonResultRow.id + '"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>';
                }
            },
            {
                "render": function (mData, type, JsonResultRow, meta) {
                    return '<div>' +
                        '<i class="fa fa-times" aria-hidden="true" onclick="deactivate(' + JsonResultRow.id + ')"></i> ' +
                        '</div>' +
                        '<script>' +
                        function deactivate(id) {
                            var retVal = confirm("Do you want to deactivate?");
                            if (retVal == true) {
                                window.location.replace("http://localhost:8080/admin/user/delete/" + id);
                            } else {
                                window.location.replace("http://localhost:8080/admin/user/viewuserlist");
                            }
                        }

                    '</script>'
                }
            },

        ]
    });
});
