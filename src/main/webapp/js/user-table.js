//Khi nào trang html nội dung đã được nạp vào trình duyệt
//thì sẽ chạy code bên trong function
$(document).ready(function () {
    //lắng nghe sự kiện click cho thẻ có id là btn-delete-user
    $(".btn-delete-user").click(function () {
        var id = $(this).attr("userid")
        var This = $(this)
        alert(id)

        $.ajax({
            method: "GET",
            url: "http://localhost:8080/demoservlet/user-table/delete?id=" + id,
            //data: { name: "John", location: "Boston" }
        })
            .done(function (results) {
                This.closest("tr").remove();
                console.log("Ket qua" , results);
            });
    })
    $(".btn-details-user").click(function () {
        var id = $(this).attr("userid")
        alert(id)

        $.ajax({
            method: "GET",
            url: "http://localhost:8080/demoservlet/user-table/details?id=" + id,
            //data: { name: "John", location: "Boston" }
        })
            .done(function (results) {
                console.log("Ket qua" , results);
            });
    })
})