function showMenu() {
  document.getElementById("user-options-menu").classList.toggle("show");
}

window.onclick = function(event) {
  if (!event.target.matches('.dropbtn')) {
    var dropdowns = document.getElementsByClassName("dropdown-content");
    var i;
    for (i = 0; i < dropdowns.length; i++) {
      var openDropdown = dropdowns[i];
      if (openDropdown.classList.contains('show')) {
        openDropdown.classList.remove('show');
      }
    }
  }
}

function responsiveBtns(){
  if(screen.width < 500){  
    let title = document.getElementsByClassName("title");
    title[0].style = "display:none";
  }
}

window.onload = responsiveBtns;