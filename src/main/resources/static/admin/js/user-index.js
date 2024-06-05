let getAPI = "/4men/admin/api/users";
let createURL = "/4men/admin/update-user";
let deleteURL = "/4men/admin/api/users/";

$(document).ready(function () {
    loadData();
    $("#btn-addUser").click(() => {
        localStorage.setItem("user", null);
    })

    // search
    $("#search").on("keyup", function () {
        let result = $(this).val().toLowerCase();
        $("#userBody tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(result) > -1);
        })
    })
})

function loadData() {
    $.ajax({
        url: getAPI,
        method: "GET",
        contentType: "application/json",
        dataType: "json",
        success: function (response) {
            console.log(response); // Kiểm tra cấu trúc của phản hồi
            let users = response.data; // Lấy danh sách data
            let row = '';
            if (Array.isArray(users)) {
                users.forEach(function (user, index) {
                    row += `
                                <tr id = 'userrow_${user.id}'>
                                    <td>${index + 1}</td>
                                    <td>${user.fullname}</td>
                                    <td>${user.username}</td>
                                    <td>${user.gender ? 'nam' : 'nữ'}</td>
                                    <td>${user.email}</td>
                                    <td>${user.phone}</td>
                                    <td>${user.address}</td>
                                    <td>${user.birthday}</td>
                                    <td>${user.state}</td>
                                    <td>
                                        <button
                                                type="button"
                                                class="btn btn-primary"
                                            onclick="openUpdateUser(${user.id})">
                                            Sửa
                                        </button>
                                        </button>
                                        <button
                                                type="button"
                                                class="btn btn-danger"
                                                data-toggle="modal"
                                            onclick="openRemoveUser(${user.id})">
                                            Xoá
                                        </button>
                                    </td>
                                </tr>
                                    `;
                    $("#userBody").html(row);
                })
            }
            else {
                console.error("Expected an array but got:", typeof users);
                alert('The response is not an array.');
            }
        },
        error: function (e) {
            console.log("Error: ", e);
        }
    })
}

function openUpdateUser(id) {
    $.ajax({
        // url: getAPI + "/" + id,
        // method: "GET",
        // dataType: "json",
        // contentType: "application/json",
        success: function (response) {
            // console.log(response);
            // let user = response.data;
            // localStorage.setItem("user", JSON.stringify(user));
            window.location.href = createURL + "/" + id;
        },
        error: function (e) {
            console.log("Error: ", e);
        }
    })
}

function openRemoveUser(id) {
    swal({
        title: "Bạn có chắc muốn xóa không?",
        text: "",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
        .then((willDelete) => {
            if (willDelete) {
                $.ajax({
                    url: deleteURL + id,
                    method: "DELETE",
                    contentType: "application/json",
                    dataType: "json",
                    success: function () {
                        $("#userrow_" + id).remove();
                        swal("Xóa người dùng thành công!", {
                            icon: "success",
                        });
                    },
                    error: function () {
                        swal("Xóa người dùng thất bại!", {
                            icon: "error",
                        });
                    }
                })
            }
        });
}
