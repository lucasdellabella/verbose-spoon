var MajorsToDepts = {}

function addMajorDeptEntry(k, v) {
    MajorsToDepts[k] = v;
}

function getDepartment(weirdArg) {
    var major = weirdArg.options[weirdArg.selectedIndex].value;
    var dept = MajorsToDepts[major];
    var element = document.getElementById('dept-id')
    element.innerHTML = dept;
}
