function addCategory (options) {
  var newTableRow = document.createElement("td");

  // create select drop-down
  select = document.createElement("select");

  for (var i = 0; i < options.length-1; i++) { // last entry empty string
    var opt = options[i];
    var el = document.createElement("option");
    el.textContent = opt;
    el.value = opt;
    select.appendChild(el);
  }
  
  // add the newly created element and its content into the DOM
  var currentEl = document.getElementById("category");
  currentEl.insertBefore(select, currentEl.lastChild);
}
