function validarTelefone(telefone) {
    const regex = /^\(\d{2}\) \d{4,5}-\d{4}$/;
    return regex.test(telefone);
}

console.log(validarTelefone("(12) 34567-8901")); 
