document.querySelectorAll(".room-item").forEach(div => {
    div.addEventListener("dragstart", event => {
        event.dataTransfer.setData("text/plain", event.target.dataset.id);
    })
})

document.getElementById("dropZone").addEventListener("dragover", event => {
    event.preventDefault();
})

document.getElementById("dropZone").addEventListener("drop", function(event) {
    event.preventDefault();
    const id = event.dataTransfer.getData("text/plain");
    const classroom = classrooms.find(x => x.id == id);
    this.textContent = (classroom.room.floor < 0 ? "B" : "") + classroom.room.number + " " + classroom.name;
    document.getElementById("parentId").value = id;
})

document.getElementById("dropZone").addEventListener("click", function() {
    if (this.textContent != null) {
        this.textContent = "부모 강의실";
        document.getElementById("parentId").value = "";
    }
})