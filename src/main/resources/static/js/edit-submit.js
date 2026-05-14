const required = (msg) => (v) => v ? true : msg;
const maxLen = (len, msg) => (v) => v.length <= len ? true : msg;
const minLen = (len, msg) => (v) => v.length >= len ? true : msg;
const typeValid = (bool, msg) => (v) => bool ? true : msg;

function recursionParent(parentId, classroomId) {
    if (!parentId) return false;

    if (parentId == classroomId) return true;

    const parent = classrooms.find(x => x.id == parentId);
    if (!parent || !parent.parent) return false;

    return recursionParent(parent.parent.id, classroomId);
}
const circularParent = (classrooms, classroomId, msg) => {
    return (parentId) => {
        if (!parentId) return true;

        return recursionParent(parentId, classroomId) ? msg : true;
    }
}

const validators = {
    building: [required("건물을 선택하세요")],
    classroomId: [],
    number: [
        required("호수를 입력하세요"),
        maxLen(6, "호수가 너무 깁니다 ex) 101-1"),
        (v) => /^[0-9-]+$/.test(v) || "숫자와 붙임표(하이픈)만 입력하세요",
        (v) => v[v.length - 1] != "-" || "붙임표로 끝내지 마세요"
    ],
    name: [maxLen(100, "이름이 너무 깁니다")],
    direction: [required("방향을 입력하세요")],
    doorType: [required("문 타입을 입력하세요")],
    count: [
        required("문 개수를 입력하세요"),
        (v) => Number.isInteger(Number(v)) || "문 개수는 정수입니다",
        (v) => Number(v) >= 0 || "0이상 정수를 입력하세요"
    ],
    parentId: [],
    memo: []
}

document.getElementById("editorForm").addEventListener("submit", function(e) {
    const form = new FormData(this);
    
    validators.parentId = [circularParent(classrooms, form.get("id"), "순환참조")];
    
    for (const [field, value] of form.entries()) {
        const rules = validators[field];
        if (!rules) continue;

        for (const rule of rules) {
            const result = rule(value);
            if (result != true) {
                e.preventDefault();
                this.elements[field].focus();
                alert(result);
                return;
            }
        }
    }
})