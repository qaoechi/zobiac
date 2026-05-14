const editForm = document.getElementById("editorForm");
const classrooms = JSON.parse(document.getElementById("editForm").textContent).classrooms;
const dropZone = document.getElementById("dropZone");
let selectedRoom = null;