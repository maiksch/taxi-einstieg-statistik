$(document).ready(function() {
  $.ajax({
    type: 'POST',
    url: '/form/',
    data: '{"name":"jonas"}', // or JSON.stringify ({name: 'jonas'}),
    success: function(data) { alert('data: ' + data); },
    contentType: "application/json",
    dataType: 'json'
  });
  
  $('#absatz1').click(function(){
    	alert('Es wurde auf #absatz1 geklickt');
    });
  
});