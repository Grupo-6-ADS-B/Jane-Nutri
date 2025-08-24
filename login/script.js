
const loginForm = document.querySelector(".login-form");
const registerForm = document.querySelector(".register-form");
const wrapper = document.querySelector(".wrapper");
const loginTitle = document.querySelector(".title-login");
const registerTitle = document.querySelector(".title-register");

function loginFunction(){
    loginForm.style.left = "50%";
    loginForm.style.opacity = 1;
    registerForm.style.left = "150%";
    registerForm.style.opacity = 0;
    wrapper.style.height = "600px"; /* Altura ajustada */
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

// Inicializa na tela de login
loginFunction();