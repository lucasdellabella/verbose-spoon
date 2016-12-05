function addCategory (options) {
  // create a new div element
  // and give it some content
  var newTableRow = document.createElement("td");
  var newContent = document.createTextNode("Hi there and greetings!");

  // create select drop-down
  select = document.createElement("select");

  for (var i = 0; i < options.length; i++) {
    var opt = options[i];
    var el = document.createElement("option");
    el.textContent = opt;
    el.value = opt;
    select.appendChild(el);
  }

  newTableRow.appendChild(newContent); //add the text node to the newly created div.
  console.log(newTableRow);
  // add the newly created element and its content into the DOM
  var currentDiv = document.getElementById("category");
  console.log(currentDiv);
  currentDiv.insertBefore(select, currentDiv.lastChild);
}
