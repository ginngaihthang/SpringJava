$(document).ready(function() {
    initializeLocalStorage();
    showCartCount();
    showCart();
    // update quantity
    $('tbody.cart-items').on('change', '.update-qty', function() {
        let id_val = $(this).data('id');
        let qty_val = $(this).val();
        let cartItemList = localStorage.getItem('cart-item-data');
        let cartItemList_obj = JSON.parse(cartItemList);

        let update_index = -1;
        let update_product = '';
        $.each(cartItemList_obj, function(i, prod) {
            if (prod.productId == id_val) {
                update_index = i;
                update_product = prod;
            }
        });
        // update
        update_product.qty = qty_val;
        cartItemList_obj[update_index] = update_product;
        localStorage.setItem('cart-item-data', JSON.stringify(cartItemList_obj));
        showCart();
    });
    // remove product from cart
    $('tbody.cart-items').on('click', '.btn-remove', function(evt) {
        evt.preventDefault();
        let id_val = $(this).data('id');
        let cartItemList = localStorage.getItem('cart-item-data');
        let cartItemList_obj = JSON.parse(cartItemList);

        let delete_index = -1;
        $.each(cartItemList_obj, function(i, prod) {
            if (prod.productId == id_val) {
                delete_index = i;
            }
        });
        cartItemList_obj.splice(delete_index, 1);
        localStorage.setItem('cart-item-data', JSON.stringify(cartItemList_obj));
        showCart();
        showCartCount();
    });

    // add product to cart
    $('div.product').on('click', 'a.add-to-cart', function(evt) {
        evt.preventDefault();
        let id_val = $(this).data('id');
        let name_val = $(this).data('name');
        let price_val = $(this).data('price');

        let cartItemList = localStorage.getItem('cart-item-data');
        let cartItemList_obj = JSON.parse(cartItemList);

        let flag = false;
        let found_index = -1;
        let old_product = '';
        $.each(cartItemList_obj, function(i, product) {
            if (id_val == product.productId) {
                flag = true;
                found_index = i;
                old_product = product;
            }
        });

        if (flag) { // old product
            old_product.qty = old_product.qty + 1;
            cartItemList_obj[found_index] = old_product;
        } else { // new product
            let new_prod = {
                productId: id_val,
                name: name_val,
                price: price_val,
                qty: 1
            };
            cartItemList_obj.push(new_prod);
        }

        // store array obj to localStorage
        localStorage.setItem('cart-item-data', JSON.stringify(cartItemList_obj));

        showCartCount();
    });

    function showCart() {
        let cartItemList = localStorage.getItem('cart-item-data');
        let cartItemList_obj = JSON.parse(cartItemList); // json to obj
        //console.log(cartItemList_obj);
        let html = '';
        let total = 0;
        $.each(cartItemList_obj, function(i, prod) {
            let sub_total = prod.price * prod.qty;
            total += sub_total;
            let tr = `
			<tr class="text-center">
						        <td class="product-remove"><a href="#" data-id="${prod.productId}" class="btn-remove"><span class="ion-ios-close"></span></a></td>
						        
						        <td class="product-name">
						        	<h3>${prod.name}</h3>
						        </td>
						        
						        <td class="price">${add(prod.price)} ks.</td>
						        
						        <td class="quantity">
						        	<div class="input-group mb-3">
					             	<input type="number" name="quantity" class="quantity form-control input-number update-qty" data-id="${prod.productId}" value="${prod.qty}" min="1" max="100">
					          	</div>
					          	</td>
						        
						        <td>${add(sub_total)} ks.</td>
			</tr>`;
            html += tr;
        });

        $('tbody.cart-items').html(html);
        $('.total').text(add(total) + " ks.");
        $('.grand-total').text(add(total) + " ks.");

        if (cartItemList_obj.length == 0) {
            $('a.btn-checkout').removeAttr('href');
        }
    };
    
     function add(price){
          return (price).toLocaleString()
        }

    function showCartCount() {
        let cartItemList = localStorage.getItem('cart-item-data');
        let cartItemList_obj = JSON.parse(cartItemList); // json to obj
        $('.cart-count').text(cartItemList_obj.length);
    };

    function initializeLocalStorage() {
        let cartItemList = localStorage.getItem('cart-item-data');
        if (!cartItemList) {
            cartItemList = [];
            localStorage.setItem('cart-item-data', JSON.stringify(cartItemList))
        }

        // {"id" : 1, "name" : product1},
        // {"id" : 1, "name" : product2}
        // productObj = {id: 1, name: product1} => {"id" : 1, "name" : product1}
    };

});