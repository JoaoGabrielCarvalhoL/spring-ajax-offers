var pageNumber = 0;

$(document).ready(function(){
	$("#loader-img").hide();
	$("#fim-btn").hide();
});

$(window).scroll(function() {

	var scrollTop = $(this).scrollTop();
	var conteudo = $(document).height() - $(window).height();

	if (scrollTop >= conteudo) {
		pageNumber++;
		setTimeout(function(){
			loadByScrollBar(pageNumber);
		}, 200);
	}

});

function loadByScrollBar(pageNumber) {

	$.ajax({
		method: "GET",
		url: "/promotions/list/ajax",
		data: {
			page: pageNumber
		},
		beforeSend: function() {
			$("#loader-img").show();
		},
		success: function( response ) {

			console.log("lista > ", response.length);

			if (response.length > 150) {

				$(".row").fadeIn(250, function() {
					$(this).append(response);
				});

			} else {
				$("#fim-btn").show();
				$("#loader-img").removeClass("loader");
			}
		},
		error: function(xhr) {
			alert("Ops, ocorreu um erro: " + xhr.status + " - " + xhr.statusText);
		},
		complete: function() {
			$("#loader-img").hide();
		}
	})
}

$("button[id*='likes-btn-']").on("click", function() {
    var id = $(this).attr("id").split("-")[2];
    console.log("id: ", id);

});