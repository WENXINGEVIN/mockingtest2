/**
 * Countdown timer
 */

// Set the exam time
var examTime = 1; // mins
examTime = examTime * 60000;
var startTime = new Date().getTime();
var endTime = startTime + examTime;

// Update the count down every 1 second
var timer = setInterval(function() {

    // Get todays date and time
    var now = new Date().getTime();
    
    // Find the distance between now an the count down date
    var distance = endTime - now;
    
    // Time calculations for days, hours, minutes and seconds
    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    var seconds = Math.floor((distance % (1000 * 60)) / 1000);
    
    // Output the result in an element with id="demo"
    document.getElementById("timer").innerHTML = days + "d " + hours + "h "
    + minutes + "m " + seconds + "s ";
    
    // If the count down is over, disable the text editor, and display 'timeout'
    if (distance < 0) {
        clearInterval(timer);
        document.getElementById("timer").value  = 'Timeout';
        document.getElementById("code_editor").readOnly  = true;
    }
}, 1000);


