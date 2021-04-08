$(document).ready(function () {

    fillTable();

})

function fillTable () {
    $("#userInformation").empty();
    $.ajax({
        type: 'GET',
        url: '/api/user',
        dataType: "json",
        success: function (data) {
            $('#userInformation').append($('<tr>').append(
                $('<td>').text(data.id),
                $('<td>').text(data.name),
                $('<td>').text(data.surname),
                $('<td>').text(data.age),
                $('<td>').text(data.email),
                $('<td>').text(data.roles.map(role => role.role).join(" "))
            ))
        }
    });
};
