export {status, json, http, fetchOpts};

function status(response) {
    if (response.status >= 200 && response.status < 300) {
        return Promise.resolve(response);
    } 
    else {
        return Promise.reject(new Error(response.statusText))
    }
}

function json(response) {
    return response.json();
}

function fetchOpts(opt) {
    if (opt.method === 'POST') {
        return {
        method: opt.method,
                headers: {
                'Content-Type': 'application/json;charset=utf-8'
                },
                credentials: 'include',
                body: JSON.stringify(opt.data)
        };
    }
    else {
        return {
            method: opt.method,
                headers: {
                'Content-Type': 'application/json;charset=utf-8'
                },
                credentials: 'include'
        };
    }
};

function http(httpOptions) {
    if (httpOptions.params != null) {
        httpOptions.url = httpOptions.url + '?' + queryParams(httpOptions.params);
    }
    let options = fetchOpts(httpOptions.options);
    return fetch(httpOptions.url, options)
        .then(status)
        .then(json)
        .catch((ex) => console.log("Fetch Exception", ex));
};
                
function queryParams(params) {
    return Object.keys(params)
        .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(params[k]))
        .join('&');
}

