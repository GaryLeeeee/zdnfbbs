$(function() {

  //download
  var downLink;
  $('#android-down,#taptap-down').click(function(){
    $('#download-tip').fadeIn(500);
    downLink = $(this).data('link');
    $('#downbutton').data('link', downLink).text($(this).data('btn-text'));
    var text = $(this).data('text');
    $('#download-text').html(text)

    istaptap = $(this).prop('id') == 'taptap-down';
        downdata = {media_from: getParameterByName('media_from'), detail: ''};
        if (istaptap) {
            downclick = 'android_taptap_click';
        } else {
            downclick = 'android_offical_click';
        }
        downdata.event = downclick;
        recordScore(downdata);
  });
  $('#download-close').click(function(){
    $('#download-tip').fadeOut(500);
  });
    $('#downbutton').click(function() {
        
        if (istaptap) {
            downclick = 'android_taptap_download';
        } else {
            downclick = 'android_offical_download';
        }
        downdata = {event: downclick,media_from: getParameterByName('media_from')};
        recordScore(downdata);
        window.open(downLink);
    });

    function recordScore(downdata) {
        $.get('/track', downdata, function(res) {
        });
    };

})
function getParameterByName(name, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}