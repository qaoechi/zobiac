function mapToEditForm(data) {
    return {
        id: data.id,
        building: data.room.building.id,
        number: data.room.number,
        basement: data.room.floor < 0,
        name: data.name,
        direction: data.direction,
        doorType: data.type,
        count: data.count,
        parentId: data.parent?.id,
        memo: data.memo
    }
}
document.querySelectorAll(".room-item").forEach(div => {
    div.addEventListener("click", () => {
        if (div !== selectedRoom) {
            div.classList.add("selected");
            selectedRoom = div;
            
            const data = mapToEditForm(classrooms.find(d => d.id == div.dataset.id));
            Object.entries(data)
                .forEach(([key, value]) => {
                    const input = editForm.elements.namedItem(key);
                    if (input) {
                        if (input.type === "checkbox") input.checked = value;
                        input.value = value ?? "";
                        
                        if (key == "parentId") dropZone.textContent = value ? input.options[input.selectedIndex].text : "부모 강의실";
                    }
                })
            return;
        }
        selectedRoom.classList.remove("selected");
        selectedRoom = null;
        editForm.reset();
        dropZone.textContent = "부모 강의실";
    })
})