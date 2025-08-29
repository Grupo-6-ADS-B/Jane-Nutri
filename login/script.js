const loginForm = document.querySelector(".login-form");
const registerForm = document.querySelector(".register-form");
const registerBtn = document.getElementById("SignUpBtn");
const registerName = document.getElementById("reg-name");
const registerEmail = document.getElementById("reg-email");
const registerPass = document.getElementById("reg-pass");
const registerConfirmPass = document.getElementById("reg-confirm-pass");
const wrapper = document.querySelector(".wrapper");
const loginTitle = document.querySelector(".title-login");
const registerTitle = document.querySelector(".title-register");
const loginBtn = document.getElementById("SignInBtn"); 
const loginEmail = document.getElementById("log-email");
const loginPass = document.getElementById("log-pass");

function showNotification(message, type) {
    const popup = document.getElementById('notification-popup');
    const messageText = popup.querySelector('.message-text');
    const progressBar = popup.querySelector('.progress-bar');

    popup.classList.remove('success', 'error');
    popup.classList.add(type);
    messageText.innerText = message;

    popup.classList.add('show');
    
    progressBar.style.animation = 'none';
    progressBar.offsetHeight;
    progressBar.style.animation = null;

    setTimeout(() => {
        popup.classList.remove('show');
    }, 4000);
}

function loginFunction(){
    loginForm.style.left = "50%";
    loginForm.style.opacity = 1;
    registerForm.style.left = "150%";
    registerForm.style.opacity = 0;
    wrapper.style.height = "600px"; 
    loginTitle.style.top = "50%";
    loginTitle.style.opacity = 1;
    registerTitle.style.top = "50px";
    registerTitle.style.opacity = 0;
    document.body.classList.add('login-mode');
    document.body.classList.remove('register-mode');
}

function registerFunction(){
    loginForm.style.left = "-50%";
    loginForm.style.opacity = 0;
    registerForm.style.left = "50%";
    registerForm.style.opacity = 1;
    wrapper.style.height = "620px";
    loginTitle.style.top = "-60px";
    loginTitle.style.opacity = 0;
    registerTitle.style.top = "50%";
    registerTitle.style.opacity = 1;
    document.body.classList.add('register-mode');
    document.body.classList.remove('login-mode');
}

document.addEventListener('mousemove', (e) => {
    const x = e.clientX;
    const y = e.clientY;

    document.body.style.setProperty('--x', `${x}px`);
    document.body.style.setProperty('--y', `${y}px`);
});

loginFunction();

registerForm.addEventListener('submit', function(event) {
    event.preventDefault(); 

    const name = registerName.value;
    const email = registerEmail.value;
    const password = registerPass.value;
    const confirmPassword = registerConfirmPass.value;

    if (!email.includes('@')) {
        showNotification('O e-mail deve conter um "@".', 'error');
        return;
    }

    const passwordRegex = /^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{6,})/;
    if (!passwordRegex.test(password)) {
        showNotification('A senha deve ter no mínimo 6 caracteres, um número e um caractere especial.', 'error');
        return;
    }

    if (password !== confirmPassword) {
        showNotification('As senhas não coincidem.', 'error');
        return;
    }

    const newUserData = {
        name: name,
        email: email,
        password: password
    };

    fetch('http://localhost:3000/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(newUserData),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao cadastrar usuário');
        }
        return response.json();
    })
    .then(data => {
        showNotification('Cadastro realizado com sucesso!', 'success');
        loginFunction(); 
    })
    .catch(error => {
        showNotification('Ocorreu um erro ao cadastrar. Tente novamente.', 'error');
    });
});

loginForm.addEventListener('submit', function(event) {
    event.preventDefault(); 

    const email = loginEmail.value;
    const password = loginPass.value;

    if (!email.includes('@')) {
        showNotification('O e-mail deve conter um "@".', 'error');
        return;
    }

    const passwordRegex = /^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{6,})/;
    if (!passwordRegex.test(password)) {
        showNotification('A senha deve ter no mínimo 6 caracteres, um número e um caractere especial.', 'error');
        return;
    }

    fetch(`http://localhost:3000/users?email=${email}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro ao buscar usuário.');
            }
            return response.json();
        })
        .then(users => {
            if (users.length === 0) {
                showNotification('Usuário não encontrado.', 'error');
                return;
            }

            const user = users[0]; 
            if (user.password === password) {
                showNotification(`Login realizado com sucesso! Bem-vindo(a), ${user.name}!`, 'success');
            } else {
                showNotification('Senha incorreta.', 'error');
            }
        })
        .catch(error => {
            showNotification('Ocorreu um erro ao fazer login. Tente novamente.', 'error');
        });
});