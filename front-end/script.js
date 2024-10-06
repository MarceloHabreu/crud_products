let editingProductId = null; //   Variable for to get the product id

const productForm = document.getElementById("productForm"),
  productList = document.getElementById("productList"),
  submitButton = document.getElementById("submitButton");

// Function of list
async function listProducts() {
  const response = await fetch("http://localhost:8080/apiProducts");
  const products = await response.json();
  productList.innerHTML = "";

  products.forEach((product) => {
    const li = document.createElement("li");
    li.textContent = `Id: ${product.id}, Name: ${product.name}, Description: ${product.description}, Price: ${product.price}, Quantity: ${product.quantity}.`;

    productList.appendChild(li);
  });
}

listProducts();
