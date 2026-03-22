function submitSchedule() {
    const selected = document.querySelectorAll(".time-slot.selected");
    const data = [];
    const token = document.querySelector('meta[name="_csrf"]').content;
    const header = document.querySelector('meta[name="_csrf_header"]').content;

    selected.forEach(cell => {
        data.push({
            week: cell.dataset.week,
            slot: cell.dataset.time
        });
    });

    fetch(`/client/schedule/${scheduleData.meetingId}/update`, {
        method: "POST",
        headers: {
            "Content-Type" : "application/json",
            [header]: token
        },
        body: JSON.stringify(data)
    }).then(response => {
        if (!response.ok) throw new Error("저장 실패");
        alert("저장 완료")
    });
}