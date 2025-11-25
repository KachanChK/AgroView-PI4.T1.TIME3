const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
    container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
    container.classList.remove("right-panel-active");
});

// Função para alterar o placeholder baseado na seleção
function mudarPlaceholder(tipo) {
    const inputDoc = document.getElementById('docInput');
    inputDoc.value = ""; // limpa o campo ao trocar
    inputDoc.placeholder = tipo === "cpf" ? "Digite seu CPF" : "Digite seu CNPJ";
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

    if (senha !== confirmarSenha) {
        alert("As senhas não coincidem!");
        return;
    }

    const payload = {
        name,
        email,
        telefone,
        documento,
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

        alert("Cadastro realizado com sucesso!");
        window.location.href = "/painel";

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
        
        // Depois vamos trocar isso por JWT
        localStorage.setItem("user", JSON.stringify(data.user));

        // redirecionar
        window.location.href = "/painel";

    } catch (error) {
        alert("Erro ao conectar com o servidor.");
    }
});
