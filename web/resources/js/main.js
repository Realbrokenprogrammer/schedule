/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
  
  //IF Frontpage: 
  $('.grabber-carousel').slick({
    accessibility: true,
    arrows: true,
    mobileFirst: true,
    dots: true
  });

  //IF Login Page: 
  var fnameField = document.getElementById('registerForm:fname');

  fnameField.onkeyup = function() {
  	document.getElementById('fnamePrev').innerHTML = fnameField.value;
  }
});