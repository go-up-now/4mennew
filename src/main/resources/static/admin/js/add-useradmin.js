let userAPI = "/4men/admin/api/users";
let roleAPI = "/4men/admin/api/roles";
let checkUsernameAPI = "/4men/admin/api/users/check/username";

// Lấy đường dẫn url hiện tại
let url = window.location.pathname;

// Tách chuỗi lấy id
let path = url.split("/");
let id = parseInt(path[path.length - 1]);

$(document).ready(function () {
    loadRoles(loadUser);

    if (isNaN(id)) {
        $(".create").show();
        $(".update").hide();
        $("#username").attr("readonly", false);
    }
    else {
        $(".create").hide();
        $(".update").show();
        $("#username").attr("readonly", true);
    }
    chonAnh();
})

function loadRoles(callback) {
    $.ajax({
        url: roleAPI,
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            let roles = response.data; // Lay danh sach data
            let row = '`<option value="">-- Vai trò --</option>`';

            if (Array.isArray(roles)) {
                roles.forEach(function (role) {
                    row += `<option value="${role.id}">${role.name}</option>`;
                })
                $("#roles").html(row);
            }
            else
                console.log("Expected an array but got: ", typeof roles);
            callback(id);
        },
        error: function (e) {
            console.log("Error: ", e.message);
        }
    })
}

// Load thông tin người dùng
function loadUser(id) {
    if (!isNaN(id)) {
        $.ajax({
            url: userAPI + "/" + id,
            type: 'GET',
            dataType: "json",
            success: function (response) {
                let user = response.data;
                console.log(user);
                $('#address').val(user.address);
                $('#email').val(user.email);
                $('#fullname').val(user.fullname);
                $('#username').val(user.username);
                $('#password').val(user.password);
                $('#phone').val(user.phone);
                $('#birthday').val(user.birthday);
                $('#states').val(user.state);
                $('#roles').val(user.role.id);
                if (user.gender)
                    $("#male").prop("checked", true);
                else
                    $("#female").prop("checked", true);

                // Hiển thị hình ảnh nếu có
                if (user.img) {
                    $('#chon-anh').html('<img src="/4men/images/' + user.img + '" alt="User Image" style="width: 100%; height: 100%">');
                }
            },
            error: function (xhr, status, error) {
                alert('Failed to load user: ' + error);
            }
        });
    }
}

function createUser() {
    $("#form_create_user").submit((event) => {
        event.preventDefault();
        if (validationUsername() && validationPassword() && validationRole() && validationState() && validationEmail()) {

            let formData = new FormData();
            let user = {
                fullname: $("#fullname").val(),
                username: $("#username").val(),
                password: $("#password").val(),
                email: $("#email").val(),
                phone: $("#phone").val(),
                birthday: $("#birthday").val(),
                address: $("#address").val(),
                gender: $('input[name="gender"]:checked').val(),
                role: $("#roles").val(),
                state: $("#states").val()
            };
            // Append user data as a json string
            formData.append('data', new Blob([JSON.stringify(user)], { type: 'application/json' }));

            // Append the file
            let fileInput = document.getElementById('img');
            if (fileInput.files.length > 0)
                formData.append('img', fileInput.files[0]);

            $.ajax({
                url: userAPI,
                method: "POST",
                data: formData,
                processData: false, // Prevent jQuery from automatically transforming the data into a query string
                contentType: false, // Setting contentType to false is important for file upload
                success: function () {
                    swal("Thêm người dùng thành công", "", "success");
                    $("#form_create_user")[0].reset();
                },
                error: function (e) {
                    swal("Thêm người dùng thất bại", "", "error");
                    console.log("error: ", e);
                }
            })
        }
        else
            console.log("error: bị lỗi");
    })
}

function updateUser() {
    $("#form_create_user").submit(function (event) {
        event.preventDefault();

        let formData = new FormData(this);
        let user = {
            fullname: $("#fullname").val(),
            password: $("#password").val(),
            email: $("#email").val(),
            phone: $("#phone").val(),
            birthday: $("#birthday").val(),
            address: $("#address").val(),
            gender: $('input[name="gender"]:checked').val(),
            role: $("#roles").val(),
            state: $("#states").val()
        };
        // Append user data as a json string
        formData.append('data', new Blob([JSON.stringify(user)], { type: 'application/json' }));

        // Append the file
        let fileInput = document.getElementById('img');
        if (fileInput.files.length > 0)
            formData.append('img', fileInput.files[0]);

        $.ajax({
            url: userAPI + "/" + id,
            method: "PUT",
            data: formData,
            processData: false,
            contentType: false,
            success: function () {
                swal("Cập nhật người dùng thành công", "", "success");
            },
            error: function (e) {
                swal("Cập nhật người dùng thất bại", "", "error");
            }
        })
    })
}

function chonAnh() {
    $("#chon-anh").click(() => {
        $("#img").click();
    });

    $("#img").change((event) => {
        var file = event.target.files[0]; // Chọn file đầu tiên trong các file đã chọn
        if (file) {
            var reader = new FileReader(); // Tạo 1 đối tượng reader để đọc nội dung của tệp
            reader.onload = function (e) {
                $('#chon-anh').html('<img src = "' + e.target.result + '" alt="Ảnh đã chọn" style="width: 100%; height: 100%">');
            }
            reader.readAsDataURL(file); // Đọc tệp dưới dạng URL data
        }
    })
}

let textUsername;
let textPassword;
let textRole;
let textState;
let textEmail;
let emailPattern = /^\w+@\w+(\.\w{2,4}){1,2}$/;

function validationUsername() {
    let username = $("#username").val();

    if (username === '') {
        textUsername = "Vui lòng nhập tài khoản";
    }
    else if (username) {
        if (username.length < 6) {
            textUsername = "Tên tài khoản phải có ít nhất 6 ký tự";
        }
        else {
            $.ajax({
                url: checkUsernameAPI,
                method: 'GET',
                dataType: 'json',
                contentType: "application/json",
                data: { username: username },
                success: function (response) {
                    let result = response.data;
                    if (result) {
                        textUsername = "Tài khoản này đã tồn tại";
                    }
                    else
                        textUsername = '';
                },
                error: function (e) {
                    console.log("error: ", e);
                }
            })
        }

    }
    $("#usernameText").html(textUsername);
    return textUsername === '';
}

function validationPassword() {
    let password = $("#password").val();

    if (password === '') {
        textPassword = "Vui lòng nhập mật khẩu";
    }
    else if (password.length < 6) {
        textPassword = "Mật khẩu phải có ít nhất 6 ký tự";
    }
    else
        textPassword = '';
    $("#passwordText").html(textPassword);
    return textPassword === '';
}

function validationRole() {
    let role = $("#roles").val();
    console.log("role: ", role);

    if (!role) {
        textRole = "Vui lòng chọn vai trò";
    }
    else
        textRole = '';
    $("#roleText").html(textRole);
    return textRole === '';
}

function validationState() {
    let state = $("#states").val();

    if (!state) {
        textState = "Vui lòng chọn tình trạng";
    }
    else
        textState = '';
    $("#stateText").html(textState);
    return textState === '';
}

function validationEmail() {
    let email = $("#email").val();

    if (email === "") {
        textEmail = "Vui lòng nhập email";
    }
    else if (!emailPattern.test(email)) {
        textEmail = "Email không hợp lệ";
    }
    else
        textEmail = "";
    $("#emailText").html(textEmail);
    return textEmail === "";
}