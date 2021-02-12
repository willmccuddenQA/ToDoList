'use strict'

const createTitle = document.querySelector("#createTitle");

const showLists = document.querySelector("#showLists");

const showList = document.querySelector("#showList");

const readOneListInput = document.querySelector("#readOneList");

const deleteListInput = document.querySelector("#deleteListInput");

const updateListInputTitle = document.querySelector("#updateInputTitle");
const updateListInputID = document.querySelector("#updateInputID");

const updateItemDescription = document.querySelector("#updateDescription");
const updateItemCompleteBy = document.querySelector("#updateCompleteBy");
const updateItemCompleteStatus = document.querySelector("#updateCompleteStatus");
const updateItemID = document.querySelector("#updateItemID");

const readOneItemInput = document.querySelector("#readOneItem");
const showItem = document.querySelector("#showItem");

const createCompleteBy = document.querySelector("#createCompleteBy");
const createCompleteStatus = document.querySelector("#createCompleteStatus");
const createDescription = document.querySelector("#createDescription");

const deleteItemInput = document.querySelector("#deleteItemInput");

let createCheckBoxStatus = false;
let updateCheckBoxStatus = false;

const checkUpdateItemCheckBox = () => {
    let check = document.querySelector("#updateCompleteStatus")
    if (check.checked === true) {
        return true
    }
    else {
        return false;
    }
}

const checkCreateItemCheckBox = () => {
    let check = document.querySelector("#createCompleteStatus")
    if (check.checked === true) {
        return true
    }
    else {
        return false;
    }
}

createCompleteStatus.addEventListener('change',function(){
	if(createCompleteStatus.checked){
		createCheckBoxStatus = true;
	}
	else{
		createCheckBoxStatus = false;
	}
})

const printListToElement = (list, element) => {
    let readList = document.createElement("p"); // <p> </p>
    let text = document.createTextNode(` List: ${list.title}`);
    readList.appendChild(text); // <p> username </p>
    element.appendChild(readList);
}

const printItemToElement = (item, element) => {
    let readList = document.createElement("p"); // <p> </p>
    let text = document.createTextNode(` Item: ${item.description}`);
    readList.appendChild(text); // <p> username </p>
    element.appendChild(readList);
}

const readAllLists = () => {
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
                    console.log(infofromserver);
                    //console.log(infofromserver.data); // key - return array(6)
                    for (let list of infofromserver) {
                        console.log(list);
                        printListToElement(list, showLists);
                    }
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}


const readOneList = () => {
    const id = readOneListInput.value
    fetch("http://localhost:8080/list/get/" + id)
        .then((response) => {
            // check that the response is OK (i.e. 200)
            if (response.status !== 200) {
                throw new Error("I don't have a status of 200");
            } else {
                console.log(response);
                console.log(`response is OK (200)`);
                //json-ify it (which returns a promise)
                response.json().then((infofromserver) => {
                    console.log(infofromserver);
                    //console.log(infofromserver.data); // key - return array(6)
                    printListToElement(infofromserver, showList);
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}

const readOneItem = () => {
    const id = readOneItemInput.value
    fetch("http://localhost:8080/item/get/" + id)
        .then((response) => {
            // check that the response is OK (i.e. 200)
            if (response.status !== 200) {
                throw new Error("I don't have a status of 200");
            } else {
                console.log(response);
                console.log(`response is OK (200)`);
                //json-ify it (which returns a promise)
                response.json().then((infofromserver) => {
                    console.log(infofromserver);
                    //console.log(infofromserver.data); // key - return array(6)
                    printItemToElement(infofromserver, showItem);
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}

const deleteList = () => {
    const listID = deleteListInput.value;
    let data = {
        id: listID
    }
    fetch("http://localhost:8080/list/delete/" + listID, {
        method: "DELETE",
        body: JSON.stringify(data),
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*"
        }
    });
}

const deleteItem = () => {
    const itemID = deleteListInput.value;
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
}

const updateList = () => {
    const titleValue = updateListInputTitle.value;
    const id = updateListInputID.value;
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
        })
        .catch(err => console.error('ERROR!' + err));
}

const updateItem = () => {

    const itemDescription = updateItemDescription.value;
    const itemCompleteBy = updateItemCompleteBy.value;
    const itemCompleteStatus = updateCheckBoxStatus;
    const id = updateItemID.value;

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
}

const createList = () => {
    const titleValue = createTitle.value;
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
}

const createItem = () => {
    const completeByValue = createCompleteBy.value;
    const completeStatusValue = createCheckBoxStatus;
    const descriptionValue = createDescription.value;
    let data = {
        completeBy: completeByValue,
        completeStatus: completeStatusValue,
        description: descriptionValue
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
}