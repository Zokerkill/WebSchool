import {http} from './http.js';
import {getSubjects} from './printSubjects.js';
export {printLoginForm, signOut};

function printLoginForm() {
    let output = '<form action="login" method="POST" style="margin-top:10px;margin-bottom:50px;">';
        output += '<div class="row justify-content-center">';
        output += '<div class="card">';
        output += '<div class="card-body">';
        output += '<h3 class="card-title text-center">Аутентификация</h3>';
        output += '<p class="card-text">';
        output += '<div class="form-group">';
        output += '<label for="login" class="badge badge-secondary">Логин:</label>';
        output += '<input type="text" name="login" id="login" class="form-control form-control-md">';
        output += '</div>';
        output += '<div class="form-group">';
        output += '<label for="password" class="badge badge-secondary">Пароль:</label>';
        output += '<input type="password" name="password" id="password" class="form-control form-control-md">';
        output += '</div>';
        output += '<p class="text-center"><input class="btn btn-secondary" id="loginButton" style="width: 100%;" type="submit" value="Войти"></p>';
        output += '<p class="text-center">Нет учетной записи? <a href="newPerson">Зарегистрироваться</a></p>';
        output += '</p>';
        output += '</div>';
        output += '</div>';
        output += '</div>';
        output += '</form>';
    document.getElementById('content').innerHTML = output;
    document.getElementById('loginButton').onclick = function() { 
        auth(); 
    };
}

function auth() {
    let login = document.getElementById('login').value;
    let password = document.getElementById('password').value;
    let credentials = {
        'login': login,
        'password': password
    };
    http({url:'loginJson',
        options:{method:'POST', data:credentials}
        })
        .then(function(response) {
            if (response.authStatus === 'true') {
            localStorage.setItem('token', response.token);
                    localStorage.setItem('user', response.user);
                    document.getElementById('info').innerHTML = 'Вы вошли как ' + response.user.login;
                    document.getElementById('signIn').style.display = 'none';
                    document.getElementById('signOut').style.display = 'block';
                    getSubjects();
            }
            else {
                document.getElementById('info').innerHTML = 'Войти не удалось';
                printLoginForm();
                document.getElementById('signIn').style.display = 'block';
                document.getElementById('signOut').style.display = 'none';
            }
            console.log('Request succeeded with JSON response', response);
    });
}

function signOut() {
http({url:'logoutJson',
        options:{method:'GET'}
})
    .then(function(response) {
        if ('false' === response.authStatus) {
            if (localStorage.getItem('token') !== null) {
                localStorage.removeItem('token');
            }
            if (localStorage.getItem('user') !== null) {
                localStorage.removeItem('user');
            }
            document.getElementById('signIn').style.display = 'block';
            document.getElementById('signOut').style.display = 'none';
            document.getElementById('info').innerHTML = 'Вы вышли';
        }
        console.log('Request succeeded with JSON response', response);
    });
}