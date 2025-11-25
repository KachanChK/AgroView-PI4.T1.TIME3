document.getElementById("btn").onclick = async () => {
  const res = await fetch("/teste");
  const data = await res.json();
  document.getElementById("msg").textContent = data.message;
};
