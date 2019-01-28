let checkMail = function (){
    let mailUser = document.getElementById("login").value;
    let typeUser = document.getElementById("profession").value;

    let checkRequest = new XMLHttpRequest();
    checkRequest.open("GET","ws/connexion?mail="+mailUser+"&type="+typeUser,true);
    
    checkRequest.onload = function () {
        if (this.status === 204) {
            document.getElementById("button").removeAttribute("disabled");
        } else if (this.status === 404) {
            document.getElementById("button").setAttribute("disabled", "disabled");
            alert("Votre mail n'a pas été trouvé, verifiez votre saisie et que vous avez selectionné la profession correspondante");
        } else {
            document.getElementById("button").setAttribute("disabled", "disabled");
            alert("Votre mail n'a pas été trouvé, verifiez votre saisie et que vous avez selectionné la profession correspondante");
        }
    };
    checkRequest.send();
};


window.onload = function () {

    document.getElementById("login").onblur = function () {
        checkMail();
    };

    document.getElementById("profession").onchange = function () {
        if (document.getElementById("login").value!==""){
            checkMail();
        }
    };

    document.getElementById("button").setAttribute("disabled","disabled");


};