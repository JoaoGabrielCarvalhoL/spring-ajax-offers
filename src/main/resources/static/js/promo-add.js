$("#form-add-promo").submit(function(evt) {
	evt.preventDefault();
	var promo = {};
	promo.urlPromotion = $("#linkPromocao").val();
	promo.description= $("#descricao").val();
	promo.promotionPrice= $("#preco").val();
	promo.title = $("#titulo").val();
	promo.category = $("#categoria").val();
	promo.urlImage = $("#linkImagem").attr("src");
	promo.site = $("#site").text();

	console.log('promo > ', promo);

	$.ajax({
		method: "POST",
		url: "/promotions/save",
		data: promo,
		beforeSend: function() {
			$("#form-add-promo").hide();
			$("#loader-form").addClass("loader").show();
		},
		success: function() {
			$("#form-add-promo").each(function() {
				this.reset();
			});
			$("#linkImagem").attr("src", "/images/promo-dark.png");
			$("#site").text("");
			$("#alert").addClass("alert alert-success").text("Promoção cadastrada com sucesso.");
		},
		error: function(xhr) {
			console.log("> error: ", xhr.responseText);
			$("#alert").addClass("alert alert-danger").text("Não foi possível salvar esta promoção.");
		},
		complete: function() {
			$("#loader-form").fadeOut(800, function() {
				$("#form-add-promo").fadeIn(250);
				$("#loader-form").removeClass("loader");
			});
		}
	});
});

$("#linkPromocao").on('change', function() {

	var url = $(this).val();

	if (url.length > 7) {

		$.ajax({
			method:"POST",
			url: "/meta/info?url=" + url,
			cache: false,
			beforeSend: function() {
				$("#alert").removeClass("alert alert-danger").text('');
				$("#titulo").val("");
				$("#site").text("");
				$("#linkImagem").attr("src", "");
				$("#loader-img").addClass("loader");
			},
			success: function( data ) {
				console.log(data);
				$("#titulo").val(data.title);
				$("#site").text(data.site.replace("@", ""));
				$("#linkImagem").attr("src", data.urlImage);
			},
			statusCode: {
				404: function() {
					$("#alert").addClass("alert alert-danger").text("Nenhuma informação pode ser recuperada dessa url.");
					$("#linkImagem").attr("src", "/images/promo-dark.png");
				}
			},
			error: function() {
				$("#alert").addClass("alert alert-danger").text("Algo deu errado... Tente novamente mais tarde.");
				$("#linkImagem").attr("src", "/images/promo-dark.png");
			},
			complete: function() {
				$("#loader-img").removeClass("loader");
			}
		});
	}
});

