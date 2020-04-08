import {http} from './http.js';
import {getSubjects} from './printSubjects.js';
export {getGrades};
export {printListGrades};

function getGrades() {
    http({url:'listGradesJson',options:{method:'GET'}})
        .then(function(response) {  
            printListGrades(response.dataJson);
            console.log('Request succeeded with JSON response', response);  
        });
}

function printListGrades(data) {
    let output = '<div class="w-100 d-flex justify-content-center">';
    for(let i=0; i<data.length; i++) {
        output += '<div class="card border-primary m-3" style="max-width: 30rem;">';
        output += '<div class="card-header">';
        output += data[i].id;
        output += '</div>';
        output += '<div class="card-body">';
        output += '<h4 class="card-title">';
        output += data[i].name;
        output += '</h4>';
        output += '<p class="card-text">';
        output += data[i].text;
        output += '</p>';
        output += '</div>';
        output += '</div>';
    }
    output += '</div>';
    document.getElementById('content').innerHTML = output;
}
