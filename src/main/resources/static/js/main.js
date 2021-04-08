$(document).ready(function () {

    const url = 'http://localhost:8080/api/admin';
    let output = '';

    const renderUsers = (users) => {
        users.forEach(user => {
            output += `
            <tr id="${user.id}">
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.age}</td>
                <td>${user.email}</td>
                <td>${user.roles.map(role => role.role).join(" ")}</td>
                <td id="${user.id}"><a class="btn btn-info text-white" id="edit-user">Edit</a></td>
                <td id="${user.id}"><a class="btn btn-danger text-white" id="delete-user">Delete</a></td>
            </tr>
        `;
            const usersList = document.querySelector('#usersTable > tbody');
            usersList.innerHTML = output;
        })
    };

    let dBtn = document.getElementById('dBtn');
    let eBtn = document.getElementById('eBtn');
    let aBtn = document.getElementById('aBtn');

    //all users
    fetch(url)
        .then(res => res.json())
        .then(data => renderUsers(data));

    document.getElementById('usersTable').addEventListener('click', (e) => {
        e.preventDefault();
        let deleteButtonIsPressed = e.target.id === 'delete-user';
        let editButtonIsPressed = e.target.id === 'edit-user';

        let id = e.target.parentElement.id;

        // delete user
        if (deleteButtonIsPressed) {
            fetch(`/api/admin/${id}`, {
                method: 'GET'
            })
                .then(res => res.json())
                .then(user => {
                    document.getElementById('delId').value = `${user.id}`;
                    document.getElementById('delFirstName').value = `${user.name}`;
                    document.getElementById('delLastName').value = `${user.surname}`;
                    document.getElementById('delAge').value = `${user.age}`;
                    document.getElementById('delEmail').value = `${user.email}`;
                    document.getElementById('delPassword').value = `${user.password}`;
                })
            $('#deleteModal').modal('show');
        }

        // edit modal
        if (editButtonIsPressed) {
            fetch(`/api/admin/${id}`, {
                method: 'GET'
            })

                .then(res => res.json())
                .then(user => {
                    document.getElementById('editId').value = `${user.id}`;
                    document.getElementById('editFirstName').value = `${user.name}`;
                    document.getElementById('editLastName').value = `${user.surname}`;
                    document.getElementById('editAge').value = `${user.age}`;
                    document.getElementById('editEmail').value = `${user.email}`;
                    document.getElementById('editPassword').value = `${user.password}`;
                })
            $('#editModal').modal('show');
        }
    })

    //edit button
    eBtn.addEventListener('click', () => {
        let id = $('#editId').val()
        fetch(`/api/admin/edit/${id}`, {
            method: 'PUT',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify({
                id: document.getElementById('editId').value,
                name: document.getElementById('editFirstName').value,
                surname: document.getElementById('editLastName').value,
                age: document.getElementById('editAge').value,
                email: document.getElementById('editEmail').value,
                password: document.getElementById('editPassword').value,
                roles: getRoles('#editRoles')
            })
        })
            .then($('#editModal').modal('hide'))
            .then(response => response.json())
            .then(data => replaceRow(data)
            )
    });

    //delete button
    dBtn.addEventListener('click', e => {
        let target = $('#delId').val()
        fetch(`/api/admin/delete/${target}`, {
            method: 'DELETE'
        })
            .then($('#deleteModal').modal('hide'));
        document.getElementById(target).remove();
    });

    //add button
    aBtn.addEventListener('click', (e) => {
        e.preventDefault();
        fetch(`/api/admin/add`, {
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            },
            body: JSON.stringify({
                name: document.getElementById('newFirstName').value,
                surname: document.getElementById('newLastName').value,
                age: document.getElementById('newAge').value,
                email: document.getElementById('newEmail').value,
                password: document.getElementById('newPassword').value,
                roles: getRoles('#newUserRoles')
            })
        }).then(res => res.json())
            .then(data => addRowsFromData(data))
        $('#home-tab').tab('show')
    })

    function replaceRow(data) {
        $.each(data, function () {
            row = `<tr id="${data.id}">
                        <td>${data.id}</td>
                        <td>${data.name}</td>
                        <td>${data.surname}</td>
                        <td>${data.age}</td>
                        <td>${data.email}</td>
                        <td>${data.roles.map(role => role.role).join(" ")}</td>
                        <td id="${data.id}"><a class="btn btn-info text-white" id="edit-user">Edit</a></td>
                        <td id="${data.id}"><a class="btn btn-danger text-white" id="delete-user">Delete</a></td>
                    </tr>
            `;
        });
        document.getElementById(data.id).innerHTML = row;
    }

    function getRoles(adress) {
        var data = [];
        $(adress).find('option:selected').each(function () {
            data.push({id: $(this).val(), name: $(this).text(), role: $(this).text(),});
           // data.push({id: $(this).val(), name: $(this).text(), authority: $(this).text()});
        });
        return data;
    }

    function addRowsFromData(data) {
        $.each(data, function () {
            row = '<tr id="' + data.id + '"><td>' + data.id + '</td><td>' + data.name + '</td>' + '<td>' + data.surname + '</td>' +
                '<td>' + data.age + "</td>" + "<td>" + data.email + "</td>" + "<td>" + data.roles.map(
                    role => role.role).join(" ") + '</td>' +
                '<td id="' + data.id + '"><a class="btn btn-info text-white" id="edit-user">Edit</a></td>' +
                '<td id="' + data.id + '"><a class="btn btn-danger text-white" id="delete-user">Delete</a></td>'
            ;
        });
        $('#usersTable').append(row);
    }

    $('.tabb').click(function () {
        $('.tabb').addClass('text-primary');
        $(this).removeClass('text-primary');
    });
})
;