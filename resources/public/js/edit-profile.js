function setMajor(user_major) {
  var ret = user_major.substring(10);
  ret = ret.replace('"})', "");
  setSelectedIndex(document.getElementById('major-dropdown'),ret);
}

function setDepartment(dept) {
  var ret = dept.substring(14);
  ret = ret.replace('"})', "");
  document.getElementById('dept-id').innerHTML = ret;
}

String.prototype.capitalize = function() {
    return this.charAt(0).toUpperCase() + this.slice(1);
}

function setYear(user_year) {
  var ret = user_year.substring(9);
  ret = ret.replace('"})', "");
  ret = ret.capitalize();
  setSelectedIndex(document.getElementById('year-dropdown'),ret);
}

function printUser(user) {
  var ret = user.substring(9);
  ret = ret.replace("})", "");
  console.log(ret);
}
function setSelectedIndex(s, v) {
  for ( var i = 0; i < s.options.length; i++ ) {
    if ( s.options[i].text == v ) {
      s.options[i].selected = true;
      return;
    }
  }
}
