function addToCart(id)
{
    var data = {
        productId: +id
    };
    $.ajax({
        url: '/BTechnology/cart',
        method: 'POST',
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json"
        },
        data: JSON.stringify(data),
        success: function (data) {
            $('#modalMsg').html(data.message);
            $('#messageModal').modal();
        },
        error: function (data) {
            if (data.message) {
                $('#modalMsg').html(data.message);
            } else {
                $('#modalMsg').html('Došlo je do greške');
            }
            $('#messageModal').modal();
        }
    }
    );
}