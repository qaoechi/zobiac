const data = JSON.parse(document.getElementById("schedule-manager").textContent);
let selected = null;

function fillForm(id) {
    const meeting = data.meetings.find(m => m.id == id);
    if (!meeting) return;

    selected = id;
    document.querySelector('[name="id"]').value = meeting.id;
    document.querySelector('[name="title"]').value = meeting.title;
    document.querySelector('[name="description"]').value = meeting.description;
    document.querySelector('[name="open"]').checked = meeting.open;
}

function toggleForm(id) {
    if (selected == id) {
        clearForm();
        return;
    }
    fillForm(id);
}

function clearForm() {
    selected = null;
    document.querySelector('[name="id"]').value = "";
    document.querySelector('[name="title"]').value = "";
    document.querySelector('[name="description"]').value = "";
    document.querySelector('[name="open"]').checked = false;
}