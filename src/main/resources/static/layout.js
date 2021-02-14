'use strict'
const allLists = document.querySelector("#listOfLists")
const overview = document.querySelector("#overview")
const thisListTitle = document.querySelector("#thisListTitle")
const thisList = document.querySelector("#thisList")
const createListButton = document.querySelector("#createListButton")
const createItemButton = document.querySelector("#createItemButton")

var listsArray = new Array();

const showRenameList = (renameListID) => {
	
	console.log(renameListID);
	const listContainer = document.querySelector("#listContainer"+renameListID);
	//text and input second row div
	let renameDiv = document.createElement("div");
	renameDiv.setAttribute("class","row justify-content-start");
	renameDiv.setAttribute("id","renameInput");
	listContainer.innerHTML = renameDiv;
	let renameSpan = document.createElement("span");
	renameSpan.setAttribute("class","group-input-text col-1");
	renameSpan.setAttribute("id","inputGroup-sizing-default");
	let inputText = document.createTextNode("New name:")
	renameSpan.appendChild(inputText);
	let renameInput = document.createElement("input");
	renameInput.setAttribute("type","text");
	renameInput.setAttribute("class","col-2");
	renameDiv.appendChild(renameSpan);
	renameDiv.appendChild(renameInput);
}

const viewList = (viewListID) => {
	thisList.style.display = "block";
	thisListTitle.style.display = "block";
	createItemButton.style.display = "block";
	
	
	for(let i=0; i<listsArray.length;i++){
		if(listsArray[i].id === viewListID){
			thisListTitle.innerHTML = listsArray[i].title;
		}
	}
	
	fetch("http://localhost:8080/item/getAll", {
		method:"GET",
		headers: {
			"Content-Type": "application/json",
			"Access-Control-Allow-Origin": "*"
		},
	})
	.then((response) => {
		if(response.status != 200){
			throw new Error("I don't have a status of 200");
		} else {
			console.log(response);
			console.log('response is OK (200)');
			response.json().then((infofromserver) =>{
				thisList.innerHTML ="";
				for(let item of infofromserver){
					if(item.list.id == viewListID){
						const itemID = item.id;
						console.log(item);
						//create list item and add to list
						let itemView = document.createElement("li");
						itemView.setAttribute("id", 'Item'  + item.id);
						itemView.setAttribute("class", "list-group-item list-group-item-light");

						//create container for all functionality in that list item
						let itemContainer = document.createElement("div");
						itemContainer.setAttribute("id", "itemContainer" + item.id);
						itemContainer.setAttribute("class", "container");
						let containerRow1 = document.createElement("div");
						containerRow1.setAttribute("class", "row justify-content-start");
						itemView.appendChild(itemContainer);
						itemContainer.appendChild(containerRow1);

						//create title text
						let itemTitleDiv = document.createElement("div");
						itemTitleDiv.setAttribute("id", "itemTitle" + item.id);
						itemTitleDiv.setAttribute("class", "col-3");
						let title = document.createElement("h5");
						let titleText = document.createTextNode(`${item.description}`);
						containerRow1.appendChild(itemTitleDiv);
						itemTitleDiv.appendChild(title);
						title.appendChild(titleText);
						
						//create complete checkbox
						let completeCheckBoxDiv = document.createElement("div");
						completeCheckBoxDiv.setAttribute("class", "col-7 d-grid gap-2 form-check");
						containerRow1.appendChild(completeCheckBoxDiv);
						let completeCheckbox = document.createElement("input");
						completeCheckbox.setAttribute("class", "form-check-input");
						completeCheckbox.setAttribute("type", "checkbox");
						completeCheckBoxDiv.appendChild(completeCheckbox);

						//update button div
						let updateButtonDiv = document.createElement("div");
						updateButtonDiv.setAttribute("class", "col-1 d-grid gap-2");
						containerRow1.appendChild(updateButtonDiv);
						let updateButton = document.createElement("button");
						updateButton.setAttribute("class", "btn btn-outline-primary btn-block");
						updateButton.setAttribute("onClick", "showUpdateItem("+itemID+")");
						let updateText = document.createTextNode("Update");
						updateButton.appendChild(updateText);
						updateButtonDiv.appendChild(updateButton);

						//create delete button div
						let deleteButtonDiv = document.createElement("div");
						deleteButtonDiv.setAttribute("class", "col-1 d-grid gap-2");
						containerRow1.appendChild(deleteButtonDiv);
						let deleteButton = document.createElement("button");
						deleteButton.setAttribute("class", "btn btn-outline-danger btn-block")
						let deleteText = document.createTextNode("Delete");
						deleteButton.appendChild(deleteText);
						deleteButtonDiv.appendChild(deleteButton);
						
						//add complete by
						let containerRow2 = document.createElement("div");
						containerRow2.setAttribute("class", "row justify-content-start");
						let completeByPara = document.createElement("p");
						let completeByText = document.createTextNode("Complete by: " + item.completeBy);
						itemContainer.appendChild(containerRow2);
						containerRow2.appendChild(completeByPara);
						completeByPara.appendChild(completeByText);
						
						
						
						thisList.appendChild(itemView);
					}
				}
			})
		}
	})
}	

const startUp = () => {
	thisList.style.display = "none";
	thisListTitle.style.display = "none";
	createItemButton.style.display = "none";
	fetch("http://localhost:8080/list/getAll", {
		method: "GET",
		headers: {
			"Content-Type": "application/json",
			"Access-Control-Allow-Origin": "*"
		}
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
						let listID= list.id
						listsArray.push(list)
						//create list item and add to list
						let listElement = document.createElement("li");
						listElement.setAttribute("id", 'listItem'  + list.id);
						listElement.setAttribute("class", "list-group-item list-group-item-light");
						allLists.appendChild(listElement);

						//create container for all functionality in that list item
						let listContainer = document.createElement("div");
						listContainer.setAttribute("id", "listContainer" + list.id);
						listContainer.setAttribute("class", "container");
						let containerRow = document.createElement("div");
						containerRow.setAttribute("class", "row justify-content-start");
						listElement.appendChild(listContainer);
						listContainer.appendChild(containerRow);

						//create title text
						let listTitleDiv = document.createElement("div");
						listTitleDiv.setAttribute("id", "listTitle" + list.id);
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
						updateButton.setAttribute("class", "btn btn-outline-primary btn-block");
						updateButton.setAttribute("onClick", "showRenameList("+listID+")");
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
				})
			}
		}).catch((err) => {
			console.error(err);
		})
}