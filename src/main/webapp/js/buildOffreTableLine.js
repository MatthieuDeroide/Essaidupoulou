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


let buildOffreTableLine = function (offre) {
    let lineElement = document.createElement("tr");
    lineElement.appendChild(createTableCell(offre.id));
    lineElement.appendChild(createTableCell(offre.titre));
    lineElement.appendChild(createTableCell(offre.description));
    delete offre.datedebut.dayOfWeek;
    delete offre.datedebut.dayOfYear;
    delete offre.datedebut.era;
    delete offre.datedebut.leapYear;
    delete offre.datedebut.monthValue;
    delete offre.datedebut.chronology;
    delete offre.datefin.dayOfWeek;
    delete offre.datefin.dayOfYear;
    delete offre.datefin.era;
    delete offre.datefin.leapYear;
    delete offre.datefin.monthValue;
    delete offre.datefin.chronology;
    lineElement.appendChild(createTableCell(Object.values(offre.datedebut)));
    lineElement.appendChild(createTableCell(Object.values(offre.datefin)));
    lineElement.appendChild(createTableCell(offre.professionRecherchee));
    //lineElement.appendChild(createTableCell(offre.adresse));
    lineElement.appendChild(createTableCell(offre.etablissement));
    lineElement.appendChild(createTableCell(offre.codePostal));
    lineElement.appendChild(createTableCellHTML("<input class='suppr' type='submit' id=\"user"+offre.id+"\" value=\"supprimer\" >"));

    let actionCell = document.createElement("td");

    lineElement.appendChild(actionCell);

    return lineElement;
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

    if(confirm("Êtes-vous sûr de vouloir supprimer l'offre avec l'id n°"+id)){
        supprUser(4, id);
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
            alert(" offre non trouvé");
        } else {
            alert("erreur inconnue");
        }
    };

    deleteRequest.send();
};


let refreshTable = function (offers) {
    let tableElement = document.getElementById("tbodyForOffers");
    let newTableElement = tableElement.cloneNode(false);
    for (const offre of offers) {
        newTableElement.appendChild(buildOffreTableLine(offre));
    }
    tableElement.parentNode.replaceChild(newTableElement, tableElement);
    setEvent();
};