function calcularIMC(peso, altura){
    return  (peso / (altura * altura)).toFixed(2);
}

console.log(calcularIMC(70, 1.75));