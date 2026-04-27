function mapToEditForm(data) {
    return {
        id: data.id,
        building: data.room.building,
        number: data.room.number,
        basement: data.room.floor[0] == "-" ? true : false,
        name: data.name,
        direction: data.direction,
        doorType: data.doorType,
        count: data.count,
        parentId: data.parentId,
        memo: data.memo
    }
}
document.querySelectorAll(".room-item").forEach(div => {
    div.addEventListener("click", () => {
        if (div !== selectedRoom) {
            div.classList.add("selected");
            selectedRoom = div;
            
            Object.entries(classrooms.find(d => d.id == div.dataset.id))
                .forEach(([key, value]) => {
                    const input = editForm.elements.namedItem(key);
                    if (input) input.value = value;
                })
            return;
        }
        selectedRoom.classList.remove("selected");
        selectedRoom = null;
        editForm.reset();
    })
})