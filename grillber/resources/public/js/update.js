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
            },
    /*        error: function (error) {
                error.responseJSON.map(function (item) {
                    console.log(item[0], item[1]);
                    $("#error_" + item[0]).text(item[1]);
                });
            }*/
        });
	});

	$(".edit").on('click', function(event) {
		var rowId = $(this).closest('tr')[0].sectionRowIndex;
		var modal = document.getElementById("myModal");
		var mainAddress = $(this).closest('tr').find('.main-address').text();
		var bbq = $(this).closest('tr').find('.bbq').text();
		var date = $(this).closest('tr').find('.delivery-date').text();
		$('#Address').val(mainAddress);
		$('#rowId').val(rowId);
		$("#bbq-select").val(bbq);
		$("#date-modal").val(date);
		modal.style.display = "block";

	});

	$("#SaveChanges").on('click', function(event) {
		var address = $('#Address').val();
		var bbq = $('#bbq-select').val();
		var rowId = $('#rowId').val();
		updateFields(address, bbq, rowId);
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