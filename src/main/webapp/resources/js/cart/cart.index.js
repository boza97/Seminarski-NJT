function removeFromCart(pid) {
    $.ajax({
        url: '/BTechnology/cart/' + pid,
        method: 'POST',
        headers: {
            Accept: "application/json"
        },
        data: {
            _method: "DELETE"
        },
        success: function (data) {
            $('#prod' + pid).remove();
            if (data.code === "EMPTY_CART") {
                $('#orderCart').remove();
                $('#tableBody').html('<tr><td class="text-center" colspan="5">Vaša korpa je prazna.</td></tr>');
            }
        }
    });
}

function calculateTotal(el, pid) {
    let quantitiy = el.value;
    let price = $('#price' + pid).text();
    let total = quantitiy * price;
    $('#total' + pid).html(total);
}