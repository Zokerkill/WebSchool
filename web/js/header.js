import {json, status} from './http.js';
import {printLoginForm,signOut} from './auth.js';
import {getSubjects} from './printSubjects.js';
import {getPersons} from './printPersons.js';
import {getGrades} from './printGrades.js';

document.getElementById("menu1").onclick = function() {
    toggleMenuActive("menu1");
    getSubjects();
};

document.getElementById("menu2").onclick = function() {
    toggleMenuActive("menu2");
    getPersons();
};

document.getElementById("menu3").onclick = function() {
    toggleMenuActive("menu3");
    getGrades();
};

document.getElementById("signIn").onclick = function() {
    toggleMenuActive("signIn");
    printLoginForm();
};

document.getElementById("signOut").onclick = function() {
    toggleMenuActive("signOut");
    signOut();
};

function toggleMenuActive(elementId) {
    let activeElement = document.getElementById(elementId);
    let passiveElements = document.getElementsByClassName("nav-item");
    for(let i = 0; i < passiveElements.length; i++) {
        if(activeElement === passiveElements[i]) {
            passiveElements[i].classList.add("active");
        }
        else {
            if(passiveElements[i].classList.contains("active")) {
                passiveElements[i].classList.remove("active");
            }
        }
    }
}
