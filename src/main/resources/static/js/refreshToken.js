function refreshToken() {
    fetch("/oauth/refresh", {
        method: "POST",
        credentials: "same-origin"
    })
    .then(response => {
        if (!response.ok) {
            alert("세션이 만료됨. 다시 로그인");
            window.location.href = "/member/login";
            return;
        }
        alert("연장됨");
        location.reload();
    })
    .catch(() => {
        alert("서버 오류");
    });
}