function changeTextButton() {
  var changeBtn = document.getElementById("changeButton");
  if (changeBtn.value == "Click me!") {
    changeBtn.value = "You click me";
    changeBtn.classList.add("green");
    changeBtn.classList.remove("red");
  } else {
    changeBtn.value = "Click me!";
    changeBtn.classList.add("red");
    changeBtn.classList.remove("green");
  }
}


function validateNIM() {
  const nimInput = document.getElementById('nim');
  const nimValue = nimInput.value;

  // Cek jika input bukan angka
  if (isNaN(nimValue)) {
      alert('NIM harus berupa angka.');
      nimInput.value = ''; // Mengosongkan input
      return;
  }

  // Cek jika panjang input lebih dari 9
  if (nimValue.length > 9) {
      alert('NIM tidak boleh lebih dari 9 angka.');
      nimInput.value = ''; // Mengosongkan input
  }
}