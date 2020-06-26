/**
 * Created by Minwoo on 2017. 3. 29..
 */


Kakao.init('d32b44e7f16d211afcab733f0a367dda');
// 카카오 로그인 버튼을 생성합니다.

//

function getStatus() {
    Kakao.Auth.getStatus(function(statusObj) {
            //var kakao = statusObj['user']['id'];
            //alert(JSON.stringify(statusObj['user']['id']));

        $.ajax({
            type: "POST",
            url: "http://localhost:8080/kakao",
            data:  JSON.stringify(statusObj['user']),
            dataType: "json",
            contentType : "application/json; charset=UTF-8",
            success: function(data, textStatus) {
                if (data.redirect) {
                    // data.redirect contains the string URL to redirect to
                }
                else {
                    // data.form contains the HTML for the replacement form
                    $("#myform").replaceWith(data.form);
                }
            },
            error : function (xhr, ajaxOptions, thrownError) {
                alert(xhr.status + thrownError);
            }
        });

    });
}
function loginWithKakao() {
    // 로그인 창을 띄웁니다.
    Kakao.Auth.login({
        success: function(authObj) {

            //alert(JSON.stringify(authObj));

            //$.post("http://localhost:8080/kakao", { name: "John", time: "2pm" } );
            //window.location.href = "/index";
            getStatus();
        },
        fail: function(err) {
            alert(JSON.stringify(err));
        }
    });
};

function logoutWithKakao() {
    // 로그인 창을 띄웁니다.
    Kakao.Auth.logout(function () {
        window.location.href = "/logout";
    })
};
