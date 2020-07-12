$(document).ready(function () {    
    $('#combo-category').val('*');
    $('#combo-category').change(function(){
      var category = $('#combo-category').val();
      $.get(
        '/BTechnology/products/category/'+category,
        function(data){
          $('#products').html(data);
      });
    });
  });