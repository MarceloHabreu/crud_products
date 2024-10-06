let editingProductId = null; // Variável para armazenar o id do produto em edição

const productForm = document.getElementById("productForm"),
  productList = document.getElementById("productList"),
  submitButton = document.getElementById("submitButton");

// Função de listar produtos
async function listProducts() {
  const response = await fetch("http://localhost:8080/apiProducts");
  const products = await response.json();
  productList.innerHTML = ""; // Limpa a lista antes de adicionar os produtos

  products.forEach((product) => {
    const li = document.createElement("li");
    li.textContent = `Id: ${product.id}, Name: ${product.name}, Description: ${product.description}, Price: ${product.price}, Quantity: ${product.quantity}.`;

    // Botão para deletar produto
    const delButton = document.createElement("button");
    delButton.textContent = "X";
    delButton.addEventListener("click", async () => {
      await deleteProduct(product.id);
      listProducts(); // Atualiza a lista após deletar
    });

    // Botão para editar produto
    const editButton = document.createElement("button");
    editButton.textContent = "Edit";
    editButton.addEventListener("click", () => {
      document.getElementById("name").value = product.name;
      document.getElementById("description").value = product.description;
      document.getElementById("price").value = product.price;
      document.getElementById("quantity").value = product.quantity;

      // Altera o botão para "Update Product"
      submitButton.textContent = "Update Product";
      editingProductId = product.id; // Armazena o id do produto em edição
    });

    // Adiciona os botões ao item da lista
    li.appendChild(delButton);
    li.appendChild(editButton);
    productList.appendChild(li);
  });
}

// Função para adicionar ou atualizar produto
productForm.addEventListener("submit", async (e) => {
  e.preventDefault();

  const product = {
    name: document.getElementById("name").value,
    description: document.getElementById("description").value,
    price: parseFloat(document.getElementById("price").value),
    quantity: parseInt(document.getElementById("quantity").value),
  };

  if (editingProductId) {
    await fetch(`http://localhost:8080/apiProducts/${editingProductId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(product),
    });

    // Após atualizar o produto
    editingProductId = null;
    submitButton.textContent = "Add Product"; // Volta o botão para o modo de adicionar
  } else {
    await fetch(`http://localhost:8080/apiProducts`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(product),
    });
  }

  productForm.reset();
  listProducts();
});

// Função para deletar produto
async function deleteProduct(productId) {
  await fetch(`http://localhost:8080/apiProducts/${productId}`, {
    method: "DELETE",
  });
}

// Inicializa a listagem de produtos
listProducts();
