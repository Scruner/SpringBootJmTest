// $("myModal").on('show.bs.modal', function(e) {
//     var userId = $(e.relatedTarget).data('user-id');
//
//     var cols = $('#edit' + userId + ' td');
//     var firstName = $(cols[0]).text();
//     var lastName = $(cols[1]).text();
//     var age = $(cols[2]).text();
//     var email = $(cols[3]).text();
//     var password = $(cols[4]).text();
//     var role = $(cols[5]).text();
//
//     $('#firstNameInput').val(firstName);
//     $('#lastNameInput').val(lastName);
//     $('#ageInput').val(age);
//     $('#emailInput').val(email);
//     $('#passwordInput').val(password);
//     $('#roleInput').val(role);
// });
//
// $("#myModal").on('hidden.bs.modal', function() {
//     var form = $(this).find('form');
//     form[0].reset();
// });