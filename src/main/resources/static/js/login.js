document.querySelector('form').addEventListener('submit', function(event) {
    event.preventDefault();
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    // 发送登录请求
    fetch('/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: 'username=' + encodeURIComponent(username) + '&password=' + encodeURIComponent(password)
    })
        .then(function(response) {
            if (response.ok) {
                // 登录成功，重定向到其他页面
                window.location.href = '/';
            } else {
                // 登录失败，获取错误消息
                response.text().then(function(errorMessage) {
                    document.getElementById('errorMessage').textContent = errorMessage;
                });
            }
        })
        .catch(function(error) {
            console.error('登录请求失败:', error);
        });
});