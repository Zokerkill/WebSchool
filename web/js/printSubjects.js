import {http} from './http.js';
export {getSubjects};
export {printListSubjects};

function getSubjects() {
    http({url:'listSubjectsJson',options:{method:'GET'}})
        .then(function(response) {  
            printListSubjects(response.dataJson);
            console.log('Request succeeded with JSON response', response);  
        });
}

function printListSubjects(dataJson) {
    console.log(dataJson);
    //let data = JSON.parse(dataJson);
    let data = dataJson;
    let output = '<div class="w-100 d-flex justify-content-center" style="float:left">';
    for(let i=0; i<data.length; i++) {
        output += '<div class="card border-primary m-3" style="max-width:10rem;">';
        output += '<div class="card-header">';
        output += data[i].id;
        output += '</div>';
        output += '<div class="card-body">';
        output += '<h4 class="card-title">';
        output += data[i].name;
        output += '</h4>';
        output += '<p class="card-text">';
        output += data[i].teacher;
        output += '</p>';
        output += '</div>';
        output += '</div>';
    };
    output += '</div>';
    document.getElementById('content').innerHTML = output;
}


