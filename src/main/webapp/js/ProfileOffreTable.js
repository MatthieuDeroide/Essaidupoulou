let listoffers = function(){
    let profession = document.getElementById("profession").value;
    let idUser = document.getElementById("idUser").value;
    let offersListRequest=new XMLHttpRequest();
    offersListRequest.open("GET", "ws/offers/list/"+profession+"/"+idUser,true);
    offersListRequest.responseType = "json";

    offersListRequest.onload = function () {
        let offers = this.response;
        refreshTable(offers);
    };

    offersListRequest.send();
};


window.onload = function () {
    listoffers();
};