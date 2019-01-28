let offerCreationPanelClick=function(){
    if (document.getElementById("newOfferForm").hidden ===true){
        document.getElementById("newOfferForm").hidden = false;
    } else{
        document.getElementById("newOfferForm").hidden = true;
    }
};

let saveOffer = function(){
    let isAideOp;
    if(document.getElementById("isAideOp").value==="1"){
        isAideOp = true.toString();
    }else if(document.getElementById("isAideOp").value==="2"){
        isAideOp = false.toString();
    }
    let offreDto={
        titre: document.getElementById("title").value,
        description: document.getElementById("description").value,
        datedebut:document.getElementById("startingDate").value,
        datefin: document.getElementById("endingDate").value,
        professionRecherche: document.getElementById("professionRecherche").value,
        adresse:document.getElementById("adresseOffre").value,
        etablissement:document.getElementById("etablissement").value,
        codePostal:document.getElementById("cpOffre").value,
        isAideOp: isAideOp,
        auteurId: document.getElementById("idUser").value
    };
    createOffer(offreDto);
};

let createOffer = function(offreDto){
    let creationRequest= new XMLHttpRequest();
    creationRequest.open("POST", "ws/offers", true);

    creationRequest.onload = function () {
        if(this.status === 201){
            document.getElementById("newOfferForm").hidden = true;
            alert("L'offre à bien été créée");
            location.reload();
        }else if (this.status === 204) {
            alert("La requète aurait du retourner une réponse");
        } else if (this.status === 409) {
            alert("L'offre existe déjà");
        }else if(this.status === 500){
            alert("erreur 500");
        }
    };

    creationRequest.setRequestHeader("content-type","application/json");
    creationRequest.send(JSON.stringify(offreDto));
};


window.onload=function () {
    document.getElementById("newOfferForm").hidden = true;

    document.getElementById("getTheOfferCreationPanelButton").onclick=function () {
        offerCreationPanelClick();
    };

    document.getElementById("closeOfferCreationPanel").onclick=function () {
        document.getElementById("newOfferForm").hidden = true;
    };

    document.getElementById("createOfferButton").onclick = function () {
        saveOffer();
    };

};