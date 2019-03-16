function updateCountdown() {
    // 140 is the max message length
    var remaining = 50 - jQuery('.title-field').val().length;
    $('.countdown').text(remaining + ' characters remaining.');
}

$(document).ready(function($) {
    updateCountdown();
    $('.title-field').change(updateCountdown);
    $('.title-field').keyup(updateCountdown);
});