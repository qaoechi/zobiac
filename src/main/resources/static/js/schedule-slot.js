const tbody = document.querySelector("#time-table tbody");
const week = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];

for (let hour = 9; hour < 22; hour++) {
    for (let min of [0, 30]) {
        const tr = document.createElement("tr");
        const timecell = document.createElement("td");

        timecell.innerText = `${hour.toString().padStart(2, '0')}:${min === 0 ? "00" : "30"}`;
        tr.appendChild(timecell);

        week.forEach(element => {
            const td = document.createElement("td");
            td.dataset.week = element;
            td.dataset.time = timecell.innerText;
            td.classList.add("time-slot");
            tr.appendChild(td);
        });

        tbody.appendChild(tr);
    }
}