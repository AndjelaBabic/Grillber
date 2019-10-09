	$(".remove").on('click', function(event) {
		var id = $(this).closest('tr').find('.orderid').text();
		$(this).closest('tr').remove();
        $.ajax({
            type: "POST",
            url: "/delete",
            data: {
                id: id
            },
            success: function (data) {
                window.location = "/update";
            }
        });
	});

	$(".edit").on('click', function(event) {
		var id = $(this).closest('tr').find('.orderid').text();
		var addressId = $(this).closest('tr').find('.addressid').text();
		var modal = document.getElementById("myModal");
		var mainAddress = $(this).closest('tr').find('.main-address').text();
		var bbq = $(this).closest('tr').find('.bbq').text();
		var date = $(this).closest('tr').find('.delivery-date').text();
		$('#Address').val(mainAddress);
		$('#rowId').val(id);
		$('#addressId').val(addressId);
		//$("#bbq-select").val(bbq);
		//$("#date-modal").val(date);
		modal.style.display = "block";

	});

	$("#SaveChanges").on('click', function(event) {
		var street_name = $('#Address').val();
		var bbq = $('#bbq-select').val();
		var rowId = $('#rowId').val();
		var addressId = $('#addressId').val(addressId);
		alert(addressId); 
		alert(bbq); 
		alert(street_name);
		$.ajax({
            type: "POST",
            url: "/updateorder",
            data: {
            	addressid: addressId,
            	street_name: street_name,
            	bbqid: bbq
            },
            success: function (data) {
                window.location = "/update";
            }
        });
	});

	function updateFields(address, bbq, rowId) {
		//alert($('#id1').find("td").eq(1).text());
		$('#id1').find("td").eq(1).html("aa");
		//alert($('#id1').find("td").eq(1).text());
		var myTable = $('#orders').DataTable();
		myTable.clear().rows.add(myTable.data).draw();
	}

	var modal = document.getElementById("myModal");
	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];
	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
		modal.style.display = "none";
	}

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}