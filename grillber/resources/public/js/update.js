$(".remove").on('click', function(event) {
		//alert($(this).closest('tr')[0].sectionRowIndex);
		$(this).closest('tr').remove();
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