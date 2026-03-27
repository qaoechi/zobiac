function toggleBoard(button) {
    const type = button.dataset.key;
    const board = document.getElementById("list-" + type);
    if (!board) return;

    board.classList.toggle("hidden");

    if (board.classList.contains("hidden")) {
        button.textContent = "펼치기"
    } else {
        button.textContent = "접기"
    }
}