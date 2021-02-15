'use strict'
const allLists = document.querySelector("#listOfLists")
const createListDiv = document.querySelector("#createListDiv")
const overview = document.querySelector("#overview")
const thisListTitle = document.querySelector("#thisListTitle")
const thisList = document.querySelector("#thisList")
const createItemDiv = document.querySelector("#createItemDiv")

var listsArray = new Array();
let currentList = 0;
let allItems = new Array();

const deleteItem = (itemID) =>{
    let data = {
        id: itemID
    }
    fetch("http://localhost:8080/item/delete/" + itemID, {
        method: "DELETE",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
    });
	setTimeout(() => { viewItems(currentList); }, 200);
}

const updateCompleteStatus = (itemID) => {
	console.log("updateCompleteStatusAccessed");
	let boxIsChecked = isChecked(itemID);
	let storedItem = null;
	for(let i = 0; i < allItems.length; i++){
		if(allItems[i].id == itemID){
			storedItem = allItems[i];
		}
	}
	console.log(storedItem);
	let data = {
        description: storedItem.description,
        completeBy: storedItem.completeBy,
        completeStatus: boxIsChecked
    }
    fetch("http://localhost:8080/item/update/" + itemID, {
        method: "PUT",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
        })
        .catch(err => console.error('ERROR!' + err));
	setTimeout(() => { viewItems(currentList); }, 200);

}

const isChecked = (itemID) =>{
	console.log(itemID);
	let check = document.querySelector("#itemCompleteCheck"+itemID);
	if(check.checked == true){
		return true;
	}
	else{
		return false;
	}
}

const updateItem = (updateItemID) => {
	const itemDescription = document.querySelector("#createItemDescriptionInput").value;
    const itemCompleteBy = document.querySelector("#createItemCompleteByInput").value;
    const itemCompleteStatus = isChecked(updateItemID);
    const id = updateItemID;

    let data = {
        description: itemDescription,
        completeBy: itemCompleteBy,
        completeStatus: itemCompleteStatus
    }
    fetch("http://localhost:8080/item/update/" + id, {
        method: "PUT",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
    })
        .then(response => response.json())
        .then(info => {
            console.log(info);
        })
        .catch(err => console.error('ERROR!' + err));
	setTimeout(() => { viewItems(currentList); }, 200);
}

const showUpdateItem = (updateItemID) => {
	const itemContainer = document.querySelector("#itemContainer" + updateItemID);
	
	//text and input second row div
	let updateInputDiv = document.createElement("div");
	updateInputDiv.setAttribute("class", "row justify-content-start");
	updateInputDiv.setAttribute("id", "updateInputDiv");
	itemContainer.appendChild(updateInputDiv);

	let updateItemDescriptionLabel = document.createElement("label");
	updateItemDescriptionLabel.setAttribute("class", "col-1");
	updateItemDescriptionLabel.innerHTML = "Description:"

	let updateItemDescriptionInput = document.createElement("input");
	updateItemDescriptionInput.setAttribute("type", "text");
	updateItemDescriptionInput.setAttribute("class", "col-2");
	updateItemDescriptionInput.setAttribute("id", "createItemDescriptionInput");

	let updateItemCompleteByLabel = document.createElement("label");
	updateItemCompleteByLabel.setAttribute("class", "col-1");
	updateItemCompleteByLabel.innerHTML = "Finish By:"

	let updateItemCompleteByInput = document.createElement("input");
	updateItemCompleteByInput.setAttribute("type", "text");
	updateItemCompleteByInput.setAttribute("class", "col-2");
	updateItemCompleteByInput.setAttribute("id", "createItemCompleteByInput");

	let updateItemSubmitButton = document.createElement("button");
	updateItemSubmitButton.setAttribute("class", "btn btn-outline-success btn-sm col-1")
	updateItemSubmitButton.setAttribute("onClick", "updateItem("+updateItemID+")");
	updateItemSubmitButton.innerHTML = "Submit";

	let divSpace = document.createElement("div");
	divSpace.setAttribute("class", "col-1");
	let divSpace2 = document.createElement("div");
	divSpace2.setAttribute("class", "col-1");

	updateInputDiv.appendChild(updateItemDescriptionLabel);
	updateInputDiv.appendChild(updateItemDescriptionInput);
	updateInputDiv.appendChild(divSpace);
	updateInputDiv.appendChild(updateItemCompleteByLabel);
	updateInputDiv.appendChild(updateItemCompleteByInput);
	updateInputDiv.appendChild(divSpace2);
	updateInputDiv.appendChild(updateItemSubmitButton);

}

const createItem = () => {
	const completeByValue = document.querySelector("#createItemCompleteByInput").value;
	const completeStatusValue = false;
	const descriptionValue = document.querySelector("#createItemDescriptionInput").value;
	let data = {
		completeBy: completeByValue,
		completeStatus: completeStatusValue,
		description: descriptionValue,
		list: { id: currentList }
	}
	fetch("http://localhost:8080/item/create", {
		method: "POST",
		body: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json",
			"Access-Control-Allow-Origin": "*"
		}
	})
		.then(response => response.json())
		.then(info => {
			console.log(info);
		})
		.catch(err => console.error('ERROR!' + err));
	setTimeout(() => { viewItems(currentList); }, 200);
}

const showCreateItemInput = () => {

	let createItemDiv = document.querySelector("#createItemDiv");
	createItemDiv.innerHTML = "";

	let createItemDescriptionLabel = document.createElement("label");
	createItemDescriptionLabel.setAttribute("class", "col-1");
	createItemDescriptionLabel.innerHTML = "Description:"

	let createItemDescriptionInput = document.createElement("input");
	createItemDescriptionInput.setAttribute("type", "text");
	createItemDescriptionInput.setAttribute("class", "col-2");
	createItemDescriptionInput.setAttribute("id", "createItemDescriptionInput");

	let createItemCompleteByLabel = document.createElement("label");
	createItemCompleteByLabel.setAttribute("class", "col-1");
	createItemCompleteByLabel.innerHTML = "Finish By:"

	let createItemCompleteByInput = document.createElement("input");
	createItemCompleteByInput.setAttribute("type", "text");
	createItemCompleteByInput.setAttribute("class", "col-2");
	createItemCompleteByInput.setAttribute("id", "createItemCompleteByInput");

	let createItemSubmitButton = document.createElement("button");
	createItemSubmitButton.setAttribute("class", "btn btn-outline-success btn-sm col-1")
	createItemSubmitButton.setAttribute("onClick", "createItem()");
	createItemSubmitButton.innerHTML = "Submit";

	let divSpace = document.createElement("div");
	divSpace.setAttribute("class", "col-1");
	let divSpace2 = document.createElement("div");
	divSpace2.setAttribute("class", "col-1");

	createItemDiv.appendChild(createItemDescriptionLabel);
	createItemDiv.appendChild(createItemDescriptionInput);
	createItemDiv.appendChild(divSpace);
	createItemDiv.appendChild(createItemCompleteByLabel);
	createItemDiv.appendChild(createItemCompleteByInput);
	createItemDiv.appendChild(divSpace2);
	createItemDiv.appendChild(createItemSubmitButton);
}

const createList = () => {
	const titleValue = document.querySelector("#createListInput").value;
	let data = {
		title: titleValue,
	}
	fetch("http://localhost:8080/list/create/", {
		method: "POST",
		body: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json",
			"Access-Control-Allow-Origin": "*"
		}
	})
		.then(response => response.json())
		.then(info => {
			console.log(info);
		})
		.catch(err => console.error('ERROR!' + err));
	allLists.innerHTML = "";
	setTimeout(() => { startUp(); }, 200);
}

const showCreateListInput = () => {

	let createListLabel = document.createElement("label");
	createListLabel.setAttribute("class", "col-1");
	createListLabel.innerHTML = "List Name:"

	let createListInput = document.createElement("input");
	createListInput.setAttribute("type", "text");
	createListInput.setAttribute("class", "col-2");
	createListInput.setAttribute("id", "createListInput");

	let createListSubmitButton = document.createElement("button");
	createListSubmitButton.setAttribute("class", "btn btn-outline-success btn-sm col-1")
	createListSubmitButton.setAttribute("onClick", "createList()");
	createListSubmitButton.innerHTML = "Submit";

	let divSpace = document.createElement("div");
	divSpace.setAttribute("class", "col-1");

	let createListDiv = document.querySelector("#createListDiv");
	createListDiv.innerHTML = "";
	createListDiv.appendChild(createListLabel);
	createListDiv.appendChild(createListInput);
	createListDiv.appendChild(divSpace);
	createListDiv.appendChild(createListSubmitButton);

}

const deleteList = (deleteListID) => {
	fetch("http://localhost:8080/list/delete/" + deleteListID, {
		method: "DELETE",
		headers: {
			"Content-Type": "application/json",
			"Access-Control-Allow-Origin": "*"
		}
	})
	allLists.innerHTML = "";
	setTimeout(() => { startUp(); }, 100);
}

const renameList = (renameListID) => {
	const titleValue = document.querySelector("#renameListInput" + renameListID).value;
	const id = renameListID;
	let data = {
		title: titleValue,
	}
	fetch("http://localhost:8080/list/update/" + id, {
		method: "PUT",
		body: JSON.stringify(data),
		headers: {
			"Content-Type": "application/json",
			"Access-Control-Allow-Origin": "*"
		}
	})
		.then(response => response.json())
		.then(info => {
			console.log(info);
			allLists.innerHTML = "";
			startUp();
		})
		.catch(err => console.error('ERROR!' + err));
}

const showRenameInput = (renameListID) => {
	console.log(2);
	const listContainer = document.querySelector("#listContainer" + renameListID);
	//text and input second row div
	let renameDiv = document.createElement("div");
	renameDiv.setAttribute("class", "row justify-content-start");
	renameDiv.setAttribute("id", "renameInput");
	listContainer.appendChild(renameDiv);
	let renameSpan = document.createElement("span");
	renameSpan.setAttribute("class", "group-input-text col-1");
	renameSpan.setAttribute("id", "inputGroup-sizing-default");
	let inputText = document.createTextNode("New name:")
	renameSpan.appendChild(inputText);
	let renameInput = document.createElement("input");
	renameInput.setAttribute("type", "text");
	renameInput.setAttribute("class", "col-2");
	renameInput.setAttribute("id", "renameListInput" + renameListID);

	let spaceDiv = document.createElement("div");
	spaceDiv.setAttribute("class", "col-1");

	let renameListSubmitButton = document.createElement("button");
	renameListSubmitButton.setAttribute("class", " col-1 btn btn-outline-success btn-sm");
	renameListSubmitButton.setAttribute("onClick", "renameList(" + renameListID + ")");
	let submitText = document.createTextNode("Submit");
	renameListSubmitButton.appendChild(submitText);

	renameDiv.appendChild(renameSpan);
	renameDiv.appendChild(renameInput);
	renameDiv.appendChild(spaceDiv);
	renameDiv.appendChild(renameListSubmitButton);
}

const viewItems = (viewListID) => {
	currentList = viewListID;
	thisList.style.display = "block";
	thisListTitle.style.display = "block";
	
	createItemDiv.innerHTML = "";

	for (let i = 0; i < listsArray.length; i++) {
		if (listsArray[i].id === viewListID) {
			thisListTitle.innerHTML = listsArray[i].title;
		}
	}

	fetch("http://localhost:8080/item/getAll", {
		method: "GET",
		headers: {
			"Content-Type": "application/json",
			"Access-Control-Allow-Origin": "*"
		},
	})
		.then((response) => {
			if (response.status != 200) {
				throw new Error("I don't have a status of 200");
			} else {
				console.log(response);
				console.log('response is OK (200)');
				response.json().then((infofromserver) => {
					thisList.innerHTML = "";
					for (let item of infofromserver) {
						allItems.push(item);
						if (item.list.id == viewListID) {
							const itemID = item.id;
							console.log(item);
							//create list item and add to list
							let itemView = document.createElement("li");
							itemView.setAttribute("id", 'Item' + item.id);
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
							title.setAttribute("id","itemTitle"+item.id);
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
							completeCheckbox.setAttribute("id", "itemCompleteCheck"+itemID);
							completeCheckbox.setAttribute("onClick", "updateCompleteStatus("+itemID+")");
							if(item.completeStatus==true){
								completeCheckbox.checked=true;
							}
							completeCheckBoxDiv.appendChild(completeCheckbox);

							//update button div
							let updateButtonDiv = document.createElement("div");
							updateButtonDiv.setAttribute("class", "col-1 d-grid gap-2");
							containerRow1.appendChild(updateButtonDiv);
							let updateButton = document.createElement("button");
							updateButton.setAttribute("class", "btn btn-outline-primary btn-block");
							updateButton.setAttribute("onClick", "showUpdateItem(" + itemID + ")");
							let updateText = document.createTextNode("Update");
							updateButton.appendChild(updateText);
							updateButtonDiv.appendChild(updateButton);

							//create delete button div
							let deleteButtonDiv = document.createElement("div");
							deleteButtonDiv.setAttribute("class", "col-1 d-grid gap-2");
							containerRow1.appendChild(deleteButtonDiv);
							let deleteButton = document.createElement("button");
							deleteButton.setAttribute("class", "btn btn-outline-danger btn-block");
							deleteButton.setAttribute("onClick", "deleteItem("+itemID+")");
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
		
		let createItemButton = document.createElement("button");
		createItemButton.setAttribute("id", "createItemButton");
		createItemButton.setAttribute("class", "btn btn-outline-success btn-sm col-1");
		createItemButton.setAttribute("onClick", "showCreateItemInput()");
		createItemButton.innerHTML = "Add Item";
		createItemDiv.appendChild(createItemButton);
}

const startUp = () => {
	thisList.style.display = "none";
	thisListTitle.style.display = "none";
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
						let listID = list.id
						listsArray.push(list)
						//create list item and add to list
						let listElement = document.createElement("li");
						listElement.setAttribute("id", 'listItem' + list.id);
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
						listTitleDiv.setAttribute("id", "listTitleDiv" + list.id);
						listTitleDiv.setAttribute("class", "col-9");
						let title = document.createElement("h4");
						title.setAttribute("id","listTitle" +list.id);
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
						viewButton.setAttribute("onClick", "viewItems(" + listID + ")");
						let viewText = document.createTextNode("View");
						viewButton.appendChild(viewText);
						viewButtonDiv.appendChild(viewButton);

						//update button div
						let updateButtonDiv = document.createElement("div");
						updateButtonDiv.setAttribute("class", "col-1 d-grid gap-2");
						containerRow.appendChild(updateButtonDiv);
						let updateButton = document.createElement("button");
						updateButton.setAttribute("class", "btn btn-outline-primary btn-block");
						updateButton.setAttribute("onClick", "showRenameInput(" + listID + ")");
						let updateText = document.createTextNode("Rename");
						updateButton.appendChild(updateText);
						updateButtonDiv.appendChild(updateButton);

						//create delete button div
						let deleteButtonDiv = document.createElement("div");
						deleteButtonDiv.setAttribute("class", "col-1 d-grid gap-2");
						containerRow.appendChild(deleteButtonDiv);
						let deleteButton = document.createElement("button");
						deleteButton.setAttribute("class", "btn btn-outline-danger btn-block");
						deleteButton.setAttribute("onClick", "deleteList(" + listID + ")");
						let deleteText = document.createTextNode("Delete");
						deleteButton.appendChild(deleteText);
						deleteButtonDiv.appendChild(deleteButton);
					}
				})
			}
		}).catch((err) => {
			console.error(err);
		})
	createListDiv.innerHTML = "";
	let createListButton = document.createElement("button");
	createListButton.setAttribute("id", "createListButton");
	createListButton.setAttribute("class", "btn btn-outline-success btn-sm col-1");
	createListButton.setAttribute("onClick", "showCreateListInput()");
	createListButton.innerHTML = "New List";

	createListDiv.appendChild(createListButton);
	createItemDiv.innerHTML = "";
	let createItemButton = document.createElement("button");
	createItemButton.setAttribute("id", "createItemButton");
	createItemButton.setAttribute("class", "btn btn-outline-success btn-sm col-1");
	createItemButton.setAttribute("onClick", "showCreateItemInput()");
	createItemButton.innerHTML = "Add Item";
	createItemDiv.appendChild(createItemButton);
	createItemButton.style.display = "none";
}