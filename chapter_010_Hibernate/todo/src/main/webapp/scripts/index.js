function call() {
    var msg = $('#formx').serialize();
    $.ajax({
        type: 'POST',
        url: 'getandput',
        data: msg
    });
}

function getAllTasks() {
    var request = new XMLHttpRequest();
    request.open("GET", "getandput", true);

    function process() {
        if (request.readyState === 4 && request.status === 200) {
            var tasks = JSON.parse(request.responseText);
            var table = document.getElementById("tasklist");
            for (var j = table.rows.length - 1; j > 0; j--) {
                table.deleteRow(j);
            }
            for (var i = 0; i < tasks.length; i++) {
                var row = table.insertRow(i + 1);
                var idCell = row.insertCell(0);
                idCell.innerHTML = tasks[i].id;
                var descriptionCell = row.insertCell(1);
                descriptionCell.innerHTML = tasks[i].description;
                var createCell = row.insertCell(2);
                createCell.innerHTML = tasks[i].created;
                var doneCell = row.insertCell(3);
                var form = document.createElement("form");
                var check = document.createElement("input");
                check.type = "checkbox";
                check.name = "doneCheck";
                check.id = tasks[i].id;
                check.value = "isDone";
                check.checked = tasks[i].done;

                function setDone(eventObj) {
                    var item = {id: eventObj.target.id, done: eventObj.target.checked};
                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', 'setdone');
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    xhr.send('param=' + JSON.stringify(item));
                }

                check.onchange = setDone;
                form.appendChild(check);
                doneCell.appendChild(form);
            }
        }
    }

    request.onreadystatechange = process;
    request.send(null);
}

function getUnDoneTasks() {
    var request = new XMLHttpRequest();
    request.open("GET", "setdone", true);

    function process() {
        if (request.readyState === 4 && request.status === 200) {
            var tasks = JSON.parse(request.responseText);
            var table = document.getElementById("tasklist");
            for (var j = table.rows.length - 1; j > 0; j--) {
                table.deleteRow(j);
            }
            for (var i = 0; i < tasks.length; i++) {
                var row = table.insertRow(i + 1);
                var idCell = row.insertCell(0);
                idCell.innerHTML = tasks[i].id;
                var descriptionCell = row.insertCell(1);
                descriptionCell.innerHTML = tasks[i].description;
                var createCell = row.insertCell(2);
                createCell.innerHTML = tasks[i].created;
                var doneCell = row.insertCell(3);
                var form = document.createElement("form");
                var check = document.createElement("input");
                check.type = "checkbox";
                check.name = "doneCheck";
                check.id = tasks[i].id;
                check.value = "isDone";
                check.checked = tasks[i].done;

                function setDone(eventObj) {
                    var item = {id: eventObj.target.id, done: eventObj.target.checked};
                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', 'setdone');
                    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                    xhr.send('param=' + JSON.stringify(item));
                }

                check.onchange = setDone;
                form.appendChild(check);
                doneCell.appendChild(form);
            }
        }
    }

    request.onreadystatechange = process;
    request.send(null);
}

function show(eventObj) {
    if (eventObj.target.checked) {
        getAllTasks();
    } else {
        getUnDoneTasks();
    }
}

window.onload = getAllTasks;

var showall = document.getElementById("showall");
showall.onchange = show;
