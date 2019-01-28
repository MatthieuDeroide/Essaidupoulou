let tableParam = {
    type: undefined  // 1 = AIDEOP  2 = DOCTEUR  3 = ADMIN  4 = OFFER
};

let createTableCell = function (text, header = false) {
    let cellElement;
    if (header) {
        cellElement = document.createElement("th");
        cellElement.setAttribute("scope", "row");
    } else {
        cellElement = document.createElement("td");
    }
    cellElement.innerText = text;
    return cellElement;
};

let createTableCellHTML = function (text, header = false) {
    let cellElement;
    if (header) {
        cellElement = document.createElement("th");
        cellElement.setAttribute("scope", "row");
    } else {
        cellElement = document.createElement("td");
    }
    cellElement.innerHTML = text;
    return cellElement;
};





let buildUserTableLine = function (user) {
    let lineElement = document.createElement("tr");
    lineElement.appendChild(createTableCell(user.id));
    lineElement.appendChild(createTableCell(user.civilite));
    lineElement.appendChild(createTableCell(user.nom));
    lineElement.appendChild(createTableCell(user.prenom));
    lineElement.appendChild(createTableCell(user.mail));
    lineElement.appendChild(createTableCell(user.numeroTel));
    if (user.dateNaissance === undefined){
        lineElement.appendChild(createTableCell(""));
    } else {
        lineElement.appendChild(createTableCell(Object.values(user.dateNaissance)));
    }
    lineElement.appendChild(createTableCell(user.pays));
    lineElement.appendChild(createTableCell(user.ville));
    lineElement.appendChild(createTableCell(user.codePostal));
    lineElement.appendChild(createTableCell(user.adresse));
    lineElement.appendChild(createTableCell(user.etablissement));
    lineElement.appendChild(createTableCellHTML("<ahref='#'>modifier</a><br><input class='suppr' type='submit' id=\"user"+user.id+"\" value=\"supprimer\" >"));


    let actionCell = document.createElement("td");

    lineElement.appendChild(actionCell);

    return lineElement;
};



let refreshTable = function (users) {
    let tableElement = document.getElementById("tbodyForListing");
    let newTableElement = tableElement.cloneNode(false);
    for (const user of  users) {
        newTableElement.appendChild(buildUserTableLine(user));
    }
    tableElement.parentNode.replaceChild(newTableElement, tableElement);
    setEvent();
};

let listusers = function (typeList) {
    let listUsersRequest = new XMLHttpRequest();
    listUsersRequest.open("GET", "ws/admin/"+typeList,true);
    listUsersRequest.responseType = "json";

    listUsersRequest.onload = function () {
        let usersList = this.response;
        refreshTable(usersList);
    }

    listUsersRequest.send();
};


//OFFERS

let listoffers = function(){
    let offersListRequest=new XMLHttpRequest();
    offersListRequest.open("GET", "ws/offers/list/",true);
    offersListRequest.responseType = "json";

    offersListRequest.onload = function () {
        let offers = this.response;
        refreshTableOffers(offers);
    };

    offersListRequest.send();
};

let createTableCellOffer = function (text, header = false) {
    let cellElement;
    if (header) {
        cellElement = document.createElement("th");
        cellElement.setAttribute("scope", "row");
    } else {
        cellElement = document.createElement("td");
    }
    cellElement.innerText = text;
    return cellElement;
};


let buildOffreTableLine = function (offre) {
    let lineElement = document.createElement("tr");
    lineElement.appendChild(createTableCellOffer(offre.id));
    lineElement.appendChild(createTableCellOffer(offre.titre));
    lineElement.appendChild(createTableCellOffer(offre.description));
    delete offre.datedebut.dayOfWeek;
    delete offre.datedebut.dayOfYear;
    delete offre.datedebut.era;
    delete offre.datedebut.leapYear;
    delete offre.datedebut.monthValue;
    delete offre.datedebut.chronology;
    delete offre.datefin.dayOfWeek;
    delete offre.datefin.dayOfYear;
    delete offre.datefin.era;
    lineElement.appendChild(createTableCellOffer(Object.values(offre.datedebut)));
    lineElement.appendChild(createTableCellOffer(Object.values(offre.datefin)));
    lineElement.appendChild(createTableCellOffer(offre.professionRecherchee));
    //lineElement.appendChild(createTableCell(offre.adresse));
    lineElement.appendChild(createTableCellOffer(offre.etablissement));
    lineElement.appendChild(createTableCellOffer(offre.codePostal));
    lineElement.appendChild(createTableCellHTML("<input class='suppr' type='submit' id=\"user"+offre.id+"\" value=\"supprimer\" >"));

    let actionCell = document.createElement("td");

    lineElement.appendChild(actionCell);

    return lineElement;
};



let refreshTableOffers = function (offers) {
    let tableElement = document.getElementById("tbodyForOffers");
    let newTableElement = tableElement.cloneNode(false);
    for (const offre of offers) {
        newTableElement.appendChild(buildOffreTableLine(offre));
    }
    tableElement.parentNode.replaceChild(newTableElement, tableElement);
    setEvent();
};



let setEvent = function(){
    console.log("set EVENT launch");
    var classname = document.getElementsByClassName('suppr');
    console.log(classname.length);


    for (var i = 0; i < classname.length; i++) {
        classname[i].onclick = function () {
            myFunction(this);
        }
    }
};

let myFunction = function(truc) {
    let id =truc.getAttribute("id").substring(4);

    if(confirm("Êtes-vous sûr de vouloir supprimer l'utilisateur avec l'id n°"+id)){
        if (tableParam.type === 3){
            alert("Un admin ne peut être supprimé");
        }else {
            supprUser(tableParam.type, id);
        }
    }

};

let supprUser = function(typeUser, idUser){
    let url = "ws/admin/"+typeUser+"/"+idUser ;
    let deleteRequest = new XMLHttpRequest();

    deleteRequest.open("DELETE", url, true);

    deleteRequest.onload = function () {
        if (this.status == 200){
            location.reload();
        }else if(this.status == 404){
            alert("Utilisateur ou offre non trouvé");
        } else {
            alert("erreur inconnue");
        }
    };

    deleteRequest.send();
};

window.onload = function () {
    let tableListUser = document.getElementById("tableListUser");
    let tableListOffer = document.getElementById("tableListOffer");
    tableListUser.hidden = true;
    tableListOffer.hidden = true;
    document.getElementById("listAdmin").onclick = function(){
        tableParam.type = 3;
        tableListUser.hidden = false;
        tableListOffer.hidden = true;
        listusers("listAdmin")  ;
    };
    document.getElementById("listDocteur").onclick = function(){
        tableParam.type = 2;
        tableListUser.hidden = false;
        tableListOffer.hidden = true;
        listusers("listDocteur");
    };
    document.getElementById("listAideOp").onclick = function(){
        tableParam.type = 1;
        tableListUser.hidden = false;
        tableListOffer.hidden = true;
        listusers("listAideOp");
    };

    document.getElementById("listOffers").onclick = function(){
        tableParam.type = 4;
        tableListUser.hidden = true;
        tableListOffer.hidden = false;

        listoffers();
    };


};