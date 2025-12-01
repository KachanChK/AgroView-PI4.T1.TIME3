const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');
const docInput = document.getElementById("docInput");
const telInput = document.getElementById("telInput");
const radios = document.getElementsByName("tipoDoc");



signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});
function tipoSelecionado() {
    return [...radios].find(r => r.checked).value;
}


function mudarPlaceholder(tipo) {
    const inputDoc = document.getElementById('docInput');
    inputDoc.value = ""; 
    inputDoc.placeholder = tipo === "cpf" ? "000.000.000-00" : "00.000.000/0000-00";
}

function mascaraCPF(v) {
    return v.replace(/\D/g, "")
            .replace(/(\d{3})(\d)/, "$1.$2")
            .replace(/(\d{3})(\d)/, "$1.$2")
            .replace(/(\d{3})(\d{1,2})$/, "$1-$2");
}

function mascaraCNPJ(v) {
    return v.replace(/\D/g, "")
            .replace(/(\d{2})(\d)/, "$1.$2")
            .replace(/(\d{3})(\d)/, "$1.$2")
            .replace(/(\d{3})(\d)/, "$1/$2")
            .replace(/(\d{4})(\d{1,2})$/, "$1-$2");
}



function mascaraTelefone(v) {
    v = v.replace(/\D/g, "");

    if (v.length <= 10) {

        return v.replace(/(\d{2})(\d{4})(\d{0,4})/, "($1) $2-$3");

    } else {
        
        return v.replace(/(\d{2})(\d{5})(\d{0,4})/, "($1) $2-$3");
    }
}

telInput.addEventListener("input", () => {
    let v = telInput.value.replace(/\D/g, "");

    if (v.length <= 10) {
        
        telInput.value = v.replace(/(\d{2})(\d{4})(\d{0,4})/, "($1) $2-$3");
    } else {
    
        telInput.value = v.replace(/(\d{2})(\d{5})(\d{0,4})/, "($1) $2-$3");
    }
});

function validarEmail(email) {
    return /\S+@\S+\.\S+/.test(email);
}

document.getElementById("emailInput").addEventListener("input", () => {
    document.getElementById("erroEmail").textContent = "";
});

function validarCPF(cpf) {
    cpf = cpf.replace(/\D/g, "");

    if (cpf.length !== 11 || /^(\d)\1{10}$/.test(cpf)) return false;

    let soma = 0;
    for (let i = 0; i < 9; i++) soma += parseInt(cpf[i]) * (10 - i);

    let digito1 = (soma * 10) % 11;
    digito1 = digito1 === 10 ? 0 : digito1;
    if (digito1 !== parseInt(cpf[9])) return false;

    soma = 0;
    for (let i = 0; i < 10; i++) soma += parseInt(cpf[i]) * (11 - i);

    let digito2 = (soma * 10) % 11;
    digito2 = digito2 === 10 ? 0 : digito2;

    return digito2 === parseInt(cpf[10]);
}

function validarCNPJ(cnpj) {
    cnpj = cnpj.replace(/\D/g, "");
    if (cnpj.length !== 14) return false;

    if (/^(\d)\1{13}$/.test(cnpj)) return false;

    let tamanho = 12;
    let numeros = cnpj.substring(0, tamanho);
    let digitos = cnpj.substring(tamanho);

    let soma = 0;
    let pos = tamanho - 7;

    for (let i = tamanho; i >= 1; i--) {
        soma += numeros[tamanho - i] * pos--;
        if (pos < 2) pos = 9;
    }

    let dig1 = soma % 11 < 2 ? 0 : 11 - (soma % 11);
    if (dig1 != digitos[0]) return false;

    tamanho++;
    numeros = cnpj.substring(0, tamanho);
    soma = 0;
    pos = tamanho - 7;

    for (let i = tamanho; i >= 1; i--) {
        soma += numeros[tamanho - i] * pos--;
        if (pos < 2) pos = 9;
    }

    let dig2 = soma % 11 < 2 ? 0 : 11 - (soma % 11);
    return dig2 == digitos[1];
}

function validarTelefone(telefone) {
    const numeros = telefone.replace(/\D/g, "");


    if (numeros.length !== 10 && numeros.length !== 11) {
        return false;
    }

    if (/^(\d)\1+$/.test(numeros)) {
        return false;
    }

    return true;
}

document.getElementById("telInput").addEventListener("input", () => {
    document.getElementById("erroTel").textContent = "";
});




docInput.addEventListener("input", () => {
    let v = docInput.value;

    document.getElementById("erroDoc").textContent = "";

    if (tipoSelecionado() === "cpf") {
        docInput.maxLength = 14;
        docInput.value = mascaraCPF(v);
    } else {
        docInput.maxLength = 18;
        docInput.value = mascaraCNPJ(v);
    }
});



radios.forEach(r => {
    r.addEventListener("change", () => {
        mudarPlaceholder(r.value);
        docInput.value = "";
        document.getElementById("erroDoc").textContent = ""; // limpa erro
    });
});

function toggleSenha(id, icon) {
    const input = document.getElementById(id);

    if (!input) {
        console.log("Input não encontrado:", id);
        return;
    }

    if (input.type === "password") {
        input.type = "text";
        icon.classList.replace("fa-eye", "fa-eye-slash");
    } else {
        input.type = "password";
        icon.classList.replace("fa-eye-slash", "fa-eye");
    }
}


document.getElementById("btnCadastrar").addEventListener("click", async (e) => {
    e.preventDefault();

    const name = document.getElementById("nameInput").value.trim();
    const email = document.getElementById("emailInput").value.trim();
    const telefone = document.getElementById("telInput").value.trim();

    const documento = document.getElementById("docInput").value.trim();
    const tipoDoc = document.querySelector("input[name='tipoDoc']:checked").value;

    const senha = document.getElementById("passInput").value.trim();
    const confirmarSenha = document.getElementById("passConfirmInput").value.trim();
    const documentoLimpo = documento.replace(/\D/g, "");
    const telefoneLimpo = telefone.replace(/\D/g, "");

    if (senha !== confirmarSenha) {
        alert("As senhas não coincidem!");
        return;
    }

    document.querySelectorAll(".error-msg").forEach(e => e.textContent = "");

    let erros = 0;

    if (!validarEmail(email)) {
        document.getElementById("erroEmail").textContent = "Email inválido!";
        erros++;
    }

    if (!validarTelefone(telefoneLimpo)) {
        document.getElementById("erroTel").textContent = "Telefone inválido!";
        erros++;
    }

    if (tipoDoc === "cpf" && documentoLimpo.length !== 11) {
        document.getElementById("erroDoc").textContent = "CPF incompleto!";
        erros++;
    }

    if (tipoDoc === "cnpj" && documentoLimpo.length !== 14) {
        document.getElementById("erroDoc").textContent = "CNPJ incompleto!";
        erros++;
    }

    if (senha !== confirmarSenha) {
        document.getElementById("erroSenha").textContent = "As senhas não coincidem!";
        erros++;
    }

    if (erros > 0) return;

    const payload = {
        name,
        email,
        telefone:telefoneLimpo,
        documento: documentoLimpo,
        tipoDoc,
        password: senha
    };

    try {
        const response = await fetch("/api/auth/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        });

        const data = await response.json();

        if (!response.ok) {
            alert("Erro: " + data.error);
            return;
        }

        alert("Cadastro realizado com sucesso! Agora faça o login.");
        window.location.href = "/auth";

    } catch (err) {
        alert("Erro ao conectar com o servidor.");
    }
});

document.getElementById("btnLogin").addEventListener("click", async (e) => {
    e.preventDefault();

    const email = document.getElementById("loginEmail").value.trim();
    const password = document.getElementById("loginPassword").value.trim();

    if (!email || !password) {
        alert("Preencha todos os campos!");
        return;
    }

    try {
        const response = await fetch("/api/auth/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password })
        });

        const data = await response.json();

        if (!response.ok) {
            alert("Erro: " + data.error);
            return;
        }

        localStorage.setItem("userOid", data.user._id);
        console.log("OID armazenado:", data.user._id);

        alert("Login realizado com sucesso!");

        localStorage.setItem("user", JSON.stringify(data.user));

        window.location.href = "/painel";

    } catch (error) {
        alert("Erro ao conectar com o servidor.");
    }
});