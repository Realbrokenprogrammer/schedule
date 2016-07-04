/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function(){
  
    if ($('body').hasClass('frontPage')) {
        $('.grabber-carousel').slick({
        accessibility: true,
        arrows: true,
        mobileFirst: true,
        dots: true
        });
    };

    if ($('body').hasClass('registerPage')) {
        var fnameField = document.getElementById('registerForm:fname');
        var dnameField = document.getElementById('registerForm:dname');
        var emailField = document.getElementById('registerForm:email');


        fnameField.onkeyup = function() {
            document.getElementById('fnamePrev').innerHTML = fnameField.value;
        }

        dnameField.onkeyup = function() {
            document.getElementById('dnamePrev').innerHTML = dnameField.value;
        }

        emailField.onkeyup = function() {
            document.getElementById('emailPrev').innerHTML = emailField.value;
        }
    };
});