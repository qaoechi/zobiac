const tbody = document.querySelector("#time-table tbody");
const week = ["SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"];
let timeSlotCount = 0;
const availsSet = new Set(avails.map(a => `${a.week},${a.slot}`));

for (let hour = 9; hour < 22; hour++) {
    for (let min of [0, 30]) {
        const tr = document.createElement("tr");
        const timecell = document.createElement("td");

        const startHour = hour.toString().padStart(2, '0');
        const startMin = min === 0 ? "00" : "30";
        const endMin = min === 0 ? "29" : "59";
        
        timecell.textContent = `${startHour}:${startMin}~${startHour}:${endMin}`;
        tr.appendChild(timecell);

        week.forEach(element => {
            const td = document.createElement("td");
            td.dataset.week = element;
            td.dataset.time = timeSlotCount;
            td.classList.add("time-slot");

            if (availsSet.has(`${element},${timeSlotCount}`)) td.classList.add("selected");

            tr.appendChild(td);
        });
        timeSlotCount++;

        tbody.appendChild(tr);
    }
}

let isMouseDown = false;
let dragWeek = null;

document.querySelectorAll(".time-slot").forEach(cell => {
    cell.addEventListener("mousedown", e => {
        if (e.target.classList.contains("time-slot")) {
            isMouseDown = true;
            dragWeek = cell.dataset.week;
            cell.classList.toggle("selected");
            e.preventDefault();
        }
    })
    cell.addEventListener("mouseover", () => {
        if (isMouseDown && cell.dataset.week === dragWeek) {
            cell.classList.toggle("selected");
        }
    })
})
document.addEventListener("mouseup", () => {
    isMouseDown = false;
    dragWeek = null;
})