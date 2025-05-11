console.log("🚀 Welcome to the Event Registration System");

window.addEventListener("load", () => {
  window.scrollTo({ top: 0, behavior: "smooth" });
});

document.addEventListener("DOMContentLoaded", () => {
  const today = new Date().toISOString().split("T")[0];
  document.querySelectorAll("tbody tr").forEach(row => {
    const dateCell = row.cells[1];
    if (dateCell && dateCell.textContent.includes(today)) {
      row.style.backgroundColor = "#e8f5e9";
      row.style.fontWeight = "bold";
    }
  });
});

const form = document.getElementById("registration-form");
if (form) {
  form.addEventListener("submit", function (e) {
    e.preventDefault();

    const name = document.getElementById("name")?.value.trim();
    const email = document.getElementById("email")?.value.trim();
    const message = document.getElementById("confirmation-message");

    if (name && email) {
      message.textContent = `✅ Thank you, ${name}! Your registration is confirmed.`;
      message.style.color = "green";
      form.reset();
    } else {
      message.textContent = "❌ Please fill in all fields.";
      message.style.color = "red";
    }
  });
}

window.addEventListener("resize", () => {
  if (window.innerWidth < 768) {
    console.log("📱 Mobile view");
  } else {
    console.log("💻 Desktop view");
  }
});




