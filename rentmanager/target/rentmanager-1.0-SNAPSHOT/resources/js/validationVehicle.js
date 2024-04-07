var addButton = document.getElementById('adButton');
var form = document.querySelector('form');

function validationFormulaire() {
    const marqueValue = document.getElementById('manufacturer').value;
    const modeleValue = document.getElementById('modele').value;
    const nbPlacesValue = document.getElementById('seats').value;

    let marque = document.getElementById('manufacturer');
    let modele = document.getElementById('modele');
    let nbPlaces = document.getElementById('seats');

    var marqueError = document.getElementById('marqueError');
    var modeleError = document.getElementById('modeleError');
    var nbPlacesError = document.getElementById('nbPlacesError');

    var marqueValid = true;
    var modeleValid = true;
    var nbPlacesValid = true;

    if (!marqueValue) {
        marqueValid = false;
        marque.classList.add('invalid');
        marqueError.textContent = 'La marque du véhicule est requise !';
        marqueError.classList.add('error-message');
    }
    else {
        marque.classList.remove('invalid');
        marqueError.textContent = '';
    }

    if (!modeleValue) {
        console.log('okayyy');
        modeleValid = false;
        modele.classList.add('invalid')
        modeleError.textContent = 'Le modèle du véhicule est requis !';
        modeleError.classList.add('error-message');
    }
    else {
        modele.classList.remove('invalid');
        modeleError.textContent = '';
    }

    if (!nbPlacesValue) {
        nbPlacesValid = false;
        nbPlaces.classList.add('invalid')
        nbPlacesError.textContent = "Le nombre de places du véhicule est requis !";
        nbPlacesError.classList.add('error-message');
    }
    else if (nbPlacesValue<2) {
        nbPlacesValid = false;
        nbPlaces.classList.add('invalid');
        nbPlacesError.textContent = "Le nombre minimum de places possible est de 2 !";
        nbPlacesError.classList.add('error-message');
    }
    else if (nbPlacesValue>9) {
        nbPlacesValid = false;
        nbPlaces.classList.add('invalid');
        nbPlacesError.textContent = "Le nombre maximum de places possible est de 9 !";
        nbPlacesError.classList.add('error-message');
    }
    else {
        nbPlaces.classList.remove('invalid');
        nbPlacesError.textContent = '';
    }


    if (marqueValid && modeleValid && nbPlacesValid) {
        form.submit();
    }
}
addButton.addEventListener('click', function(event) {
    event.preventDefault();
    validationFormulaire();
});