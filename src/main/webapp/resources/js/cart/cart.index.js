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
                $('#tableBody').html('<tr><td class="text-center" colspan="5">Vaša korpa je prazna.</td></tr>');
                $('#btn-checkout').hide();
            }
        }
    });
}

function calculateTotal(el, pid) {
    let quantitiy = el.value;
    
    let price = $('#price' + pid).text().trim().split(" ")[1];
    price = parseFloat(price.replace(",", ""));
    console.log('PRICE');
    console.log(price);

    let total = quantitiy * price;
    total = 'RSD ' + total.toFixed(2);
    $('#total' + pid).html(total);
}