// login form
let emailPattern = /^\w+@\w+(\.\w{2,4}){1,2}$/;
let textEmail;
let textPassword;
let email;
let password;

function loginValidationEmail() {
    email = document.querySelector("#email").value;
    if(email === "") {
        textEmail = "Vui lòng nhập email";
    }
    else if(!emailPattern.test(email)){
        textEmail = "Email không hợp lệ";
    }
    else
        textEmail="";
    document.querySelector("#textEmail").innerHTML = textEmail;
    return textEmail === "";    // Trả về true nếu textEmail = ""
}

function loginValidationPassword() {
    password = document.querySelector("#password").value;
    if(password === ""){
        textPassword = "Vui lòng nhập mật khẩu";
    }
    else if(password.length < 6)
        textPassword = "Mật khẩu phải có ít nhất 6 ký tự";
    else
        textPassword="";
    document.querySelector("#textPassword").innerHTML = textPassword;
    return textPassword === "";     // Trả về true nếu textPassword = ""
}

function validateForm(event){
    event.preventDefault(); // ngăn chặn việc submit form mặc định
    if (loginValidationEmail() && loginValidationPassword()){
        if (email === "admin@gmail.com" && password === "123456")
            swal("Đăng nhập thành công", "", "success");
        else
            swal("Đăng nhập thất bại!", "", "error");
    }
}

document.querySelector("#loginForm").addEventListener("submit", validateForm);