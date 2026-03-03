function submitSchedule() {
    const selected = document.querySelectorAll(".time-slot.selected");
    const data = [];

    selected.forEach(cell => {
        data.push({
            week: cell.dataset.week,
            slot: cell.dataset.time
        });
    });

    fetch(`/client/schedule/${meetingId}/update`, {
        method: "POST",
        headers: {
            "Content-Type" : "application/json"
        },
        body: JSON.stringify(data)
    }).then(() => alert("저장 완료"));
}