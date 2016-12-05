var myVar = {}

function addElement () {
  // create a new div element
  // and give it some content
	alert("test");
  var newTableRow = document.createElement("td");
  var newContent = document.createTextNode("Hi there and greetings!");
  newTableRow.appendChild(newContent); //add the text node to the newly created div.
	console.log(newTableRow);
  // add the newly created element and its content into the DOM
  var currentDiv = document.getElementById("category");
	console.log(currentDiv);
  currentDiv.insertBefore(newTableRow, currentDiv.childNodes[0]);
}
