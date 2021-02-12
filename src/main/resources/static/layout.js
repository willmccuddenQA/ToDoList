'use strict'
const allLists = document.querySelector("#listOfLists")
const overview = document.querySelector("#overview")

var listsArray = new Array();

const viewList = (id) => {
	console.log(id);
}

const startUp = () => {
	fetch("http://localhost:8080/list/getAll", {
		method: "GET",
		headers: {
			"Content-Type": "application/json",
			"Access-Control-Allow-Origin": "*"
		},
	})
		.then((response) => {
			// check that the response is OK (i.e. 200)
			if (response.status !== 200) {
				throw new Error("I don't have a status of 200");
			} else {
				console.log(response);
				console.log(`response is OK (200)`);
				//json-ify it (which returns a promise)
				response.json().then((infofromserver) => {
					//console.log(infofromserver.data); // key - return array(6)
					for (let list of infofromserver) {
						
						//create list item and add to list
						let listElement = document.createElement("li");
						listElement.setAttribute("id", 'ListItem'  + list.title);
						listElement.setAttribute("class", "list-group-item list-group-item-light");
						allLists.appendChild(listElement);

						//create container for all functionality in that list item
						let listContainer = document.createElement("div");
						listContainer.setAttribute("id", "ListContainer" + list.title);
						listContainer.setAttribute("class", "container");
						let containerRow = document.createElement("div");
						containerRow.setAttribute("class", "row justify-content-start");
						listElement.appendChild(listContainer);
						listContainer.appendChild(containerRow);

						//create title text
						let listTitleDiv = document.createElement("div");
						listTitleDiv.setAttribute("id", "ListTitle" + list.title + "Div");
						listTitleDiv.setAttribute("class", "col-9");
						let title = document.createElement("h4");
						let titleText = document.createTextNode(`${list.title}`);
						containerRow.appendChild(listTitleDiv);
						listTitleDiv.appendChild(title);
						title.appendChild(titleText);

						//view button div
						let viewButtonDiv = document.createElement("div");
						viewButtonDiv.setAttribute("class", "col-1 d-grid gap-2");
						containerRow.appendChild(viewButtonDiv);
						let viewButton = document.createElement("button");
						viewButton.setAttribute("class", "btn btn-outline-info btn-block");
						viewButton.setAttribute("onClick", "viewList("+listID+")");
						let viewText = document.createTextNode("View");
						viewButton.appendChild(viewText);
						viewButtonDiv.appendChild(viewButton);

						//update button div
						let updateButtonDiv = document.createElement("div");
						updateButtonDiv.setAttribute("class", "col-1 d-grid gap-2");
						containerRow.appendChild(updateButtonDiv);
						let updateButton = document.createElement("button");
						updateButton.setAttribute("class", "btn btn-outline-primary btn-block")
						let updateText = document.createTextNode("Rename");
						updateButton.appendChild(updateText);
						updateButtonDiv.appendChild(updateButton);

						//create delete button div
						let deleteButtonDiv = document.createElement("div");
						deleteButtonDiv.setAttribute("class", "col-1 d-grid gap-2");
						containerRow.appendChild(deleteButtonDiv);
						let deleteButton = document.createElement("button");
						deleteButton.setAttribute("class", "btn btn-outline-danger btn-block")
						let deleteText = document.createTextNode("Delete");
						deleteButton.appendChild(deleteText);
						deleteButtonDiv.appendChild(deleteButton);
					}

					let createList = document.createElement("button");
					let createText = document.createTextNode("Start new list");
					createList.setAttribute("id", 'createNewList');
					createList.setAttribute("class", "btn btn-outline-success");
					createList.appendChild(createText);
					overview.appendChild(createList);
					let breakElement = document.createElement("br");
					overview.appendChild(breakElement);
				})
			}
		}).catch((err) => {
			console.error(err);
		})
}