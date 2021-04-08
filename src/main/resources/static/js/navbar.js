$(document).ready(function () {

    navbar();

})

function navbar () {
    $("#principalEmail").empty();
    $("#roles").empty();
    $.ajax({
        type: 'GET',
        url: '/api/user',
        dataType: "json",
        success: function (data) {
            $('#principalEmail').text(data.email);
            $('#roles').text(data.roles.map(role => role.role).join(" "));
        }
    });
};